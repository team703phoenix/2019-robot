/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Vision;

public class ToggleVisionLight extends InstantCommand {
  public ToggleVisionLight() {
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    switch (Robot.vision.getLEDMode()) {
      case Vision.LED_OFF:
        Robot.vision.setLEDOn();
        break;
      case Vision.LED_ON: default:
        Robot.vision.setLEDOff();
        break;
    }
  }

}
