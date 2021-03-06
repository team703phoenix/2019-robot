/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveElevatorToPosition extends Command {
  // Control variables
  private double absoluteHeight;

  public MoveElevatorToPosition(double absoluteHeightInInches) {
    requires(Robot.elevator);

    this.absoluteHeight = absoluteHeightInInches;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.releaseBrake();
    Robot.elevator.enable(); // Enables the PID controller
    Robot.elevator.setSetpoint(absoluteHeight);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator.onTarget() || Robot.elevator.getLowerLimitSwitch();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.disable(); // Disables the PID controller
    Robot.elevator.move(0.0);
    Robot.elevator.engageBrake();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
