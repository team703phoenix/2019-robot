/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleHatchClamp extends InstantCommand {
  public ToggleHatchClamp() {
    requires(Robot.hatchMechanism);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if (Robot.hatchMechanism.isClamped())
      Robot.hatchMechanism.release();
    else
      Robot.hatchMechanism.clamp();
  }

}
