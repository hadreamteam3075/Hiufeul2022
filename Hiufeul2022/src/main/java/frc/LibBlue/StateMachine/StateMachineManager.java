package frc.LibBlue.StateMachine;

import java.util.Arrays;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class StateMachineManager {
    private State[] allStates;
    private State currentState;
    private State defaultState;

    private Subsystem subsystem;
    private boolean enable;

    public StateMachineManager(State defaultState, State... states) {
        this.currentState = defaultState;

        this.allStates = Arrays.copyOf(states, states.length+1);
        this.allStates[allStates.length-1] = defaultState;

        this.subsystem = null;
        this.enable = false;
    }

    public StateMachineManager(State initialState, Subsystem subsystem) {
        this(initialState);

        this.subsystem = subsystem;
    }

    public void update() {
        for (State state : this.allStates) {
            if (state.isTransitionalable(currentState)) {
                this.transition(state);
                return;
            }
        }
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
