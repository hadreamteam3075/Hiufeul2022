package frc.LibBlue.StateMachine;

public class StateMachineManager {
    private StateMachine currentStateMachine;

    public StateMachineManager(StateMachine defaultStateMachine) {
        this.currentStateMachine = defaultStateMachine;
    }

    public void isTransitionable() {
        for (StateMachine statemachine: this.currentStateMachine.getConnections()) {
            if (statemachine.getCurrentState().isTransitionalable()) {
                this.transition(statemachine);
            }
        }
    }

    private void transition(StateMachine statemachine) {
        this.currentStateMachine.getCurrentState().end();
        statemachine.getCurrentState().initialize();
        this.currentStateMachine = statemachine;
    }

    public void run() {
        this.currentStateMachine.getCurrentState().run();
    }
}
