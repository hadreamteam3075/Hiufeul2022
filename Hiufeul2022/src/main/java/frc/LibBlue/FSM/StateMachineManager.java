/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.LibBlue.FSM;

/**
 * *** 3075 ***
 * This class manages all subsystem managers in order to not have
 * too many repeating that would run each subsystem manager individually
 */
public class StateMachineManager {
    private SubsystemManager[] subsystemManagers;

    public StateMachineManager(SubsystemManager... subsystemManagers) {
        this.subsystemManagers = subsystemManagers;
    }

    public void start() {
        for (SubsystemManager subsystemManager : this.subsystemManagers) {
            subsystemManager.start();
        }
    }

    public void update() {
        for (SubsystemManager subsystemManager : this.subsystemManagers) {
            subsystemManager.update();
        }
    }

    public void stop() {
        for (SubsystemManager subsystemManager : this.subsystemManagers) {
            subsystemManager.stop();
        }
    }
}
