package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleCargoIntakeLift extends InstantCommand {
    public ToggleCargoIntakeLift() {
        requires(Robot.cargoIntake);
    }

    @Override
    protected void initialize() {
        if (Robot.cargoIntake.isRaised()) {
            Robot.cargoIntake.lower();
        } else {
            Robot.cargoIntake.raise();
        }
    }
}