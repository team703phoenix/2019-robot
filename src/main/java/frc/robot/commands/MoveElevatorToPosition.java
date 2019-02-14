/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class MoveElevatorToPosition extends Command {
  // Constants
  private static final double kP = 0.001;
  private static final double MAX_SPEED = 0.60;
  private static final double MIN_SPEED = 0.20;

  // Control variables
  private double absoluteHeight;

  public MoveElevatorToPosition(double absoluteHeightInInches) {
    requires(Robot.elevator);

    this.absoluteHeight = absoluteHeightInInches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double error = (absoluteHeight - Robot.elevator.getAbsoluteHeight()) * kP;

    if (error >= 0.0) { // Move up
      if (error <= MIN_SPEED)
        Robot.elevator.move(MIN_SPEED);
      else if (error >= MAX_SPEED)
        Robot.elevator.move(MAX_SPEED);
      else
        Robot.elevator.move(error);
    } else { // Move down
      if (error >= -MIN_SPEED)
        Robot.elevator.move(-MIN_SPEED);
      else if (error <= -MAX_SPEED)
        Robot.elevator.move(-MAX_SPEED);
      else
        Robot.elevator.move(error);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator.inAcceptedRange(absoluteHeight);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
