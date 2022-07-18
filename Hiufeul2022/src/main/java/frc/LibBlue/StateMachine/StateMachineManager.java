package frc.LibBlue.StateMachine;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class StateMachineManager {
    private StateMachine currentStateMachine;
    private Subsystem subsystem;
    private boolean enable;

    public StateMachineManager(StateMachine defaultStateMachine) {
        this.currentStateMachine = defaultStateMachine;
        
        this.subsystem = null;
        this.enable = true;
    }

    public StateMachineManager(StateMachine defaultStateMachine, Subsystem subsystem) {
        this(defaultStateMachine);
        
        this.subsystem = subsystem;
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

    public void initialize() {
        this.enable = true;

        this.currentStateMachine.getCurrentState().initialize();
    }

    public void run() {
        if (!this.enable) return;
        
        this.currentStateMachine.getCurrentState().run();
    }

    public Subsystem getSubsystem() {
        return this.subsystem;
    }

    public void stop() {
        this.enable = false;
    }
}
