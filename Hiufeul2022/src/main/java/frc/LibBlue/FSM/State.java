/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.LibBlue.FSM;

import frc.LibBlue.Utils.Loop;

/**
 * The action of the state should be written in a method named run, ovveride it.
 */
public abstract class State extends Loop {
    public State(double frequency) {
        super(frequency);
    }

    @Override
    public void run() {
        if (enabled)
            execute();
    }

    abstract public void execute();

    /**
        This function will return true if this state should start.
     */
    public abstract boolean isTransionable(State currentState);
}
