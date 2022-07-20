package frc.LibBlue.StateMachine;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class StateMachineScheduler {
    private StateMachineManager[] stateMachineManagers;

    public StateMachineScheduler(StateMachineManager[] stateMachineManagers) {
        this.stateMachineManagers = stateMachineManagers;
    }

    public void run() {
        for (StateMachineManager stateMachineManager: this.stateMachineManagers) {
            stateMachineManager.update();
            stateMachineManager.run();
        }
    }

    public void stop() {
        for (StateMachineManager stateMachineManager: this.stateMachineManagers) {
            stateMachineManager.stop();
        }
    }

    public void start() {
        for (StateMachineManager stateMachineManager: this.stateMachineManagers) {
            stateMachineManager.start();
        }
    }

    public void stop(Subsystem subsystem) {
        for (StateMachineManager stateMachineManager: this.stateMachineManagers) {
            if (stateMachineManager.getSubsystem().equals(subsystem)) {
                stateMachineManager.stop();
            }
        }
    }

    public void start(Subsystem subsystem) {
       for (StateMachineManager stateMachineManager: this.stateMachineManagers) {
            if (stateMachineManager.getSubsystem().equals(subsystem)) {
                stateMachineManager.start();
            }
        }
    }
}
