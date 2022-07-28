package frc.LibBlue.StateMachine;

import edu.wpi.first.wpilibj.Timer;

public abstract class State {
    private double initial_time;
      
    public void initialize() {
        this.initial_time = Timer.getFPGATimestamp();
    }

    public abstract void run();

    public abstract void end();

    public abstract State getNextState();

    public double getTimePassed() {
        return Timer.getFPGATimestamp() - this.initial_time;
    }
}
