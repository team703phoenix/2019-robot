/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Elevator;

public class HatchMoveToMiddle extends CommandGroup {
  public HatchMoveToMiddle() {
    addSequential(new MoveElevatorToPosition(47.0 - Elevator.HATCH_HEIGHT_OFFSET)); // 3 ft 11 in
  }
}
