package frc.LibBlue.FSM;

import java.util.Arrays;

/**
 * *** 3075 ***
 * This class manages the states of a subsystem
 */
public class SubsystemManager {
    private State[] states;
    private State currentState;
    private State defaultState;
    private boolean enable;

    public SubsystemManager(State defaultState, State... states) {
        this.states = Arrays.copyOf(states, states.length + 1);
        this.states[this.states.length - 1] = defaultState;

        this.currentState = defaultState;
        this.defaultState = defaultState;
    }

    public void start() {
        this.currentState = this.defaultState;
        this.enable = true;

        this.currentState.enable();
    }

    /**
     * This method checks the transitions
     * and transitions to a different state if needed
     */
    public void update() {
        if (!this.enable)
            return;

        for (State state : this.states) {
            if (state.isTransionable(this.currentState)) {
                this.transition(state);

                return;
            }
        }
    }

    private void transition(State nextState) {
        this.currentState.disable();
        nextState.enable();
        this.currentState = nextState;

    }

    public void stop() {
        this.currentState.disable();
        this.enable = false;
    }
}
