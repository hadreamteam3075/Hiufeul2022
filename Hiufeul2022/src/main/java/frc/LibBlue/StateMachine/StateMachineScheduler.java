package frc.LibBlue.StateMachine;

public class StateMachineScheduler {
    private StateMachineManager[] stateMachineManagers;

    public StateMachineScheduler(StateMachineManager[] stateMachineManagers) {
        this.stateMachineManagers = stateMachineManagers;
    }

    public void update() {
        for (StateMachineManager stateMachineManager: this.stateMachineManagers) {
            stateMachineManager.isTransitionable();
            stateMachineManager.run();
        }
    }
}
