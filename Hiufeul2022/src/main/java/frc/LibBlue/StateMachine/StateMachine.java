package frc.LibBlue.StateMachine;

public class StateMachine {
    private StateMachine[] connections; // an array of the current state's connection.   
    private State currentState;

    public StateMachine(State currentState, StateMachine[] connections) {
        this.currentState = currentState;        
        this.connections = connections;
    }

    public StateMachine[] getConnections() {
        return this.connections;
    }
    
    public State getCurrentState() {
        return this.currentState;
    }
}
