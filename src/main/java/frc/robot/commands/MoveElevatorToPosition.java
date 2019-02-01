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
  public static final double ELEVATOR_MOVE_SPEED = 0.50;

  // Control variables
  private double heightInTicks;

  public MoveElevatorToPosition(double heightInInches) {
    requires(Robot.elevator);

    heightInTicks = Elevator.elevatorTicksToInches(heightInInches);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.elevator.heightInTicks() < heightInTicks) // Move up
      Robot.elevator.move(ELEVATOR_MOVE_SPEED);
    else if (Robot.elevator.heightInTicks() > heightInTicks) // Move down
      Robot.elevator.move(-ELEVATOR_MOVE_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator.inAcceptedRange(heightInTicks);
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
