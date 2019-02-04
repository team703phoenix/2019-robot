/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTowardTarget extends Command {
  // Constants
	private static final int PIPELINE_TIMEOUT = 250;
	private static final double FINAL_PERCENTAGE = 4.5;
  private static final double STRAFING_SCALER = 0.015;
  private static final double APPROACH_SPEED = 0.40;
  private static final double SCAN_SPEED = 0.50;
	
	// Control variables
	private boolean scanLeft;

  public DriveTowardTarget(boolean scanLeft) {
    requires(Robot.vision);
    requires(Robot.driveTrain);
    
    this.scanLeft = scanLeft;
  }

  public DriveTowardTarget() {
    this(Robot.vision.getLatestErrorX() < 0.0);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    Robot.vision.setTargetPipeline(PIPELINE_TIMEOUT);
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    if (!Robot.vision.hasValidTarget()) // Scan for object
      Robot.driveTrain.mecanumDrive(0, 0, (scanLeft) ? -SCAN_SPEED : SCAN_SPEED);
    else // Drive toward object
      Robot.driveTrain.mecanumDrive(0, APPROACH_SPEED, Robot.vision.getErrorX() * STRAFING_SCALER);
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return Robot.vision.getArea() >= FINAL_PERCENTAGE;
  }

  // Called once after isFinished returns true
  protected void end() {
    Robot.driveTrain.forcedMecanumDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
