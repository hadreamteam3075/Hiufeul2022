package frc.LibBlue.Utils;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.LibBlue.FSM.SubsystemManager;

public class BlueSubsystem extends SubsystemBase {
    private SubsystemManager subsystemManager;

    public BlueSubsystem(SubsystemManager subsystemManager) {
        this.subsystemManager = subsystemManager;
    }

    public SubsystemManager getSubsystemManager() {
        return this.subsystemManager;
    }
}
