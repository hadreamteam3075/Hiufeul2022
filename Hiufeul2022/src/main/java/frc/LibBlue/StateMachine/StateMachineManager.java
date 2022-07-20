package frc.LibBlue.StateMachine;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class StateMachineManager {
    private StateMachineLink currentLink;
    private Subsystem subsystem;
    private boolean enable;

    public StateMachineManager(StateMachineLink initialLink) {
        this.currentLink = initialLink;
        
        this.subsystem = null;
        this.enable = true;
    }

    public StateMachineManager(StateMachineLink initialLink, Subsystem subsystem) {
        this(initialLink);
        
        this.subsystem = subsystem;
    }

    public void update() {
        for (StateMachineLink statemachine: this.currentLink.getConnections()) {
            if (statemachine.getState().isTransitionalable(currentLink)) {
                this.transition(statemachine);
            }
        }
    }

    private void transition(StateMachineLink statemachine) {
        this.currentLink.getState().end();
        statemachine.getState().initialize();
        this.currentLink = statemachine;
    }

    public void start() {
        this.enable = true;

        this.currentLink.getState().initialize();
    }

    public void run() {
        if (!this.enable) return;
        
        this.currentLink.getState().run();
    }

    public Subsystem getSubsystem() {
        return this.subsystem;
    }

    public void stop() {
        this.enable = false;
        this.currentLink.getState().end();
    }
}
