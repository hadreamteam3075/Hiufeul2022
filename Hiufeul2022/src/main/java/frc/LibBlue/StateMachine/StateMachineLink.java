package frc.LibBlue.StateMachine;

/**
 * This method represents one link in the state machine
 */
public class StateMachineLink {
    private StateMachineLink[] connections; // an array of the current state's connection.   
    private State state;

    public StateMachineLink(State state, StateMachineLink[] connections) {
        this.state = state;        
        this.connections = connections;
    }

    public StateMachineLink[] getConnections() {
        return this.connections;
    }
    
    public State getState() {
        return this.state;
    }
}
