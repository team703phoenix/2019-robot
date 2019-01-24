/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;

public class DriveTowardTarget extends DriveTowardObject {
  // Constants
	private static final int PIPELINE_TIMEOUT = 250;
	private static final double FINAL_PERCENTAGE = 7.0;

  public DriveTowardTarget(boolean scanLeft) {
    super(FINAL_PERCENTAGE, scanLeft);
    // In super:
    // requires(Robot.vision);
    // requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    Robot.vision.setTargetPipeline(PIPELINE_TIMEOUT);
  }
  
  // Inherits the execute, isFinished, end, and interrupted methods from super
}
