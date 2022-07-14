/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.LibBlue.Controllers.XboxController;
import frc.LibPurple.States;
import frc.LibPurple.DriveCommands.Swerve.HolonomicDriveCommand;
import frc.LibPurple.control.LazySparkMax;
import frc.LibPurple.control.LazyTalonFX;
import frc.LibPurple.control.PID.PIDController;
import frc.LibPurple.control.PID.PIDvalue;
import frc.LibPurple.math.Vector2D;
import frc.LibPurple.systems.Drivetrain.Swerve.SwerveDrivetrain;
import frc.LibPurple.systems.Drivetrain.Swerve.SwerveModule3075V3;
import frc.robot.Constants;
import frc.robot.RobotMap; 

public class Swerve extends SwerveDrivetrain {
    private static Swerve instance;

    public static Swerve getInstance(){
        if (instance == null)
            instance = new Swerve();

        return instance;
    }

    private PIDvalue snapRotationPIDValue = new PIDvalue(0.35, 0.01, 0.0);
    private PIDController snapRotationController = new PIDController(snapRotationPIDValue);
    private SwerveModule3075V3[] swerveModules;

    private SwerveModule3075V3 frontRightModule;
    private SwerveModule3075V3 rearRightModule;
    private SwerveModule3075V3 frontLeftModule;
    private SwerveModule3075V3 rearLeftModule;

    public Swerve() {
        frontRightModule = new SwerveModule3075V3(
                new Vector2D(Constants.TRACKWIDTH / 2.0, Constants.WHEELBASE / 2.0),
                Constants.FRONT_RIGHT_ANGLE_OFFSET_COMPETITION,
                new LazySparkMax(RobotMap.angleMotorFrontRight, MotorType.kBrushless),
                new LazyTalonFX(RobotMap.driveMotorFrontRight), new AnalogInput(RobotMap.encoderFrontRight), true,
                true, Constants.DISTANCE_PER_PULSE_FRONT_RIGHT, Constants.ANGLE_PID_VALUE_FRONT_RIGHT,
                Constants.DRIVE_PID_VALUE_FRONT_RIGHT, Constants.ADJUSTMENT_ANGLE);
        frontRightModule.setModuleName("FrontRight"); // yoda

        frontLeftModule = new SwerveModule3075V3(
                new Vector2D(Constants.TRACKWIDTH / 2.0, -Constants.WHEELBASE / 2.0),
                Constants.FRONT_LEFT_ANGLE_OFFSET_COMPETITION,
                new LazySparkMax(RobotMap.angleMotorFrontLeft, MotorType.kBrushless),
                new LazyTalonFX(RobotMap.driveMotorFrontLeft), new AnalogInput(RobotMap.encoderFrontLeft), true, true,
                Constants.DISTANCE_PER_PULSE_FRONT_LEFT, Constants.ANGLE_PID_VALUE_FRONT_LEFT, Constants.DRIVE_PID_VALUE_FRONT_LEFT,
                Constants.ADJUSTMENT_ANGLE);
        frontLeftModule.setModuleName("FrontLeft"); // bobross

        rearRightModule = new SwerveModule3075V3(
                new Vector2D(-Constants.TRACKWIDTH / 2.0, Constants.WHEELBASE / 2.0),
                Constants.BACK_RIGHT_ANGLE_OFFSET_COMPETITION,
                new LazySparkMax(RobotMap.angleMotorRearRight, MotorType.kBrushless),
                new LazyTalonFX(RobotMap.driveMotorRearRight), new AnalogInput(RobotMap.encoderRearRight), true, false,
                Constants.DISTANCE_PER_PULSE_REAR_RIGHT, Constants.ANGLE_PID_VALUE_REAR_RIGHT, Constants.DRIVE_PID_VALUE_REAR_RIGHT,
                Constants.ADJUSTMENT_ANGLE);
        rearRightModule.setModuleName("RearRight"); // shrek

        rearLeftModule = new SwerveModule3075V3(
                new Vector2D(-Constants.TRACKWIDTH / 2.0, -Constants.WHEELBASE / 2.0),
                Constants.BACK_LEFT_ANGLE_OFFSET_COMPETITION,
                new LazySparkMax(RobotMap.angleMotorRearLeft, MotorType.kBrushless),
                new LazyTalonFX(RobotMap.driveMotorRearLeft), new AnalogInput(RobotMap.encoderRearLeft), true, false,
                Constants.DISTANCE_PER_PULSE_REAR_LEFT, Constants.ANGLE_PID_VALUE_REAR_LEFT, Constants.DRIVE_PID_VALUE_REAR_LEFT,
                Constants.ADJUSTMENT_ANGLE);
        rearLeftModule.setModuleName("RearLeft"); // the rock

        // 0
        swerveModules = new SwerveModule3075V3[] {
            frontLeftModule,
            frontRightModule,
            rearRightModule,    
            rearLeftModule
        };

        snapRotationController.setInputRange(0.0, 2.0 * Math.PI);
        snapRotationController.setContinuous(true);

        CommandScheduler.getInstance().setDefaultCommand(this, new HolonomicDriveCommand(this, XboxController.getDriveInstance().getmController()));
    }

    @Override
    public void holonomicDrive(Vector2D translation, double rotation, boolean fieldOriented) {
        super.holonomicDrive(translation, rotation, fieldOriented);
    }

    /**
     * Sets the adjustment angle 180.
     */
    public void setModuleArray() {
        swerveModules = new SwerveModule3075V3[] {
            rearRightModule,
            rearLeftModule,
            frontLeftModule,
            frontRightModule
        };
    }

    @Override
    public SwerveModule3075V3[] getSwerveModules() {
        return swerveModules;
    }

    @Override
    public double getMaximumVelocity() {
        return 0;
    }

    @Override
    public double getMaximumAcceleration() {
        return 0;
    }

    public void setSnapRotation(double setpoint) {
        snapRotationController.setSetpoint(setpoint);
    }

    /**
     * Organize the swerve modules array by the adjusment angle.
     */
    public void arrangeSwerveModules() {
        if (Constants.ADJUSTMENT_ANGLE.toDegrees() == 90) {
            SwerveModule3075V3 module = swerveModules[swerveModules.length-1];
            for (int i = swerveModules.length-2; i > 0; i--) {
                swerveModules[i] = swerveModules[i+1];
            }
            swerveModules[0] = module;

        } else if (Constants.ADJUSTMENT_ANGLE.toDegrees() == 0) {
            return;
        } else if (Constants.ADJUSTMENT_ANGLE.toDegrees() == -90) {
            SwerveModule3075V3 module = swerveModules[0];
            for (int i = 1; i < swerveModules.length-1; i++) {
                swerveModules[i] = swerveModules[i-1];
            }
            swerveModules[swerveModules.length-1] = module;
        } else if (Constants.ADJUSTMENT_ANGLE.toDegrees() == 180) {
            for (int j = 0; j < 2; j++) {
                SwerveModule3075V3 module = swerveModules[swerveModules.length-1];
                for (int i = swerveModules.length-2; i > 0; i--) {
                    swerveModules[i] = swerveModules[i+1];
                }
                swerveModules[0] = module;

            }
        }
    }

    public void outputSmartDashBoard() {

        SmartDashboard.putNumber("x local", getKinematicPosition().x);
        SmartDashboard.putNumber("y local", getKinematicPosition().y);
        SmartDashboard.putNumber("X Velocity", getKinematicVelocity().x);
        SmartDashboard.putNumber("Y Velocity", getKinematicVelocity().y);
        for (SwerveModule3075V3 module : getSwerveModules()) {
            SmartDashboard.putNumber(module.getModuleName() + "Angle360", Math.toDegrees(module.getAngle()) % 360);
            SmartDashboard.putNumber(module.getModuleName() + "Angle360SetPoint", Math.toDegrees(module.getTargetAngle()) % 360);
            SmartDashboard.putNumber(module.getModuleName() + "CurrentAnlge360", Math.toDegrees(module.getCurrentAngle()) % 360);
        }

        SmartDashboard.putNumber("navX", navX.getYaw());
        SmartDashboard.putBoolean("Field Orientation", States.fieldOriented);
    }

    public void setAngle(double angle) {
        for (int i = 0; i < swerveModules.length; i++) {
            swerveModules[i].setTargetAngle(angle);
        }
    }

    @Override
    public void resetEncoders() {
        for (SwerveModule3075V3 module : getSwerveModules()) {
            module.reset();
            module.reset();
            module.reset();
        }
    }
}