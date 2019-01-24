/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

public class DriveTowardObjectWithoutStopping extends Command {
  // Constants
	private static final double STRAFING_SCALER = 0.015;
	
	// Control variables
	private double finalPercentage;
	private boolean scanLeft;

  public DriveTowardObjectWithoutStopping(double finalPercentage, boolean scanLeft) {
    requires(Robot.vision);
    requires(Robot.driveTrain);
    
    this.finalPercentage = finalPercentage;
    this.scanLeft = scanLeft;
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    if (!Robot.vision.hasValidTarget()) // Scan for object
      Robot.driveTrain.mecanumDrive(0, 0, (scanLeft) ? -0.5 : 0.5);
    else // Drive toward object
      Robot.driveTrain.mecanumDrive(0.6, Robot.vision.getErrorX() * STRAFING_SCALER, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return Robot.vision.getArea() >= finalPercentage;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    end();
  }
}
