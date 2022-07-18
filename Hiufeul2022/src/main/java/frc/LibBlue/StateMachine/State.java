package frc.LibBlue.StateMachine;

public abstract class State {
    public abstract void initialize();

    public abstract void run();

    public abstract void end();

    public abstract boolean isTransitionalable();
}
