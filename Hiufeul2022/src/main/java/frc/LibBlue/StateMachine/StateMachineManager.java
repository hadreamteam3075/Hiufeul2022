package frc.LibBlue.StateMachine;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class StateMachineManager {
    private State currentState;
    private State defaultState;

    private Subsystem subsystem;
    private boolean enable;

    public StateMachineManager(State defaultState, State... states) {
        this.currentState = defaultState;
        this.defaultState = defaultState;

        this.subsystem = null;
        this.enable = false;
    }

    public StateMachineManager(State initialState, Subsystem subsystem) {
        this(initialState);

        this.subsystem = subsystem;
    }

    public void update() {
        State nextState = currentState.getNextState();

        if (nextState == null)
            return;
        
        transition(nextState);
    }

    private void transition(State newState) {
        this.currentState.end();
        newState.initialize();
        this.currentState = newState;
    }

    public void start() {
        this.enable = true;

        this.currentState = this.defaultState;
        this.currentState.initialize();
    }

    public void run() {
        if (!this.enable)
            return;

        this.currentState.run();
    }

    public Subsystem getSubsystem() {
        return this.subsystem;
    }

    public void stop() {
        this.enable = false;
        this.currentState.end();
    }
}
