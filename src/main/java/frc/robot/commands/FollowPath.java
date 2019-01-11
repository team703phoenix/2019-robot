/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class FollowPath extends Command {
  // PID Constants
  private static final double kP = 1.0;
  private static final double kI = 0.0;
  private static final double kD = 0.0;
  private static final double ACCELERATION_GAIN = 0.0;

  // Trajectories
  private Trajectory leftTraj, rightTraj;
  private EncoderFollower left, right;

  public FollowPath(Trajectory leftTraj, Trajectory rightTraj) {
    requires(Robot.driveTrain);

    this.leftTraj = leftTraj;
    this.rightTraj = rightTraj;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    left = new EncoderFollower(leftTraj);
    left.configureEncoder(0, RobotMap.DRIVE_ENC_TICKS_PER_ROTATION, RobotMap.WHEEL_DIAMETER);
    left.configurePIDVA(kP, kI, kD, 1 / RobotMap.MAX_VELOCITY, ACCELERATION_GAIN);
    right = new EncoderFollower(rightTraj);
    right.configureEncoder(0, RobotMap.DRIVE_ENC_TICKS_PER_ROTATION, RobotMap.WHEEL_DIAMETER);
    right.configurePIDVA(kP, kI, kD, 1 / RobotMap.MAX_VELOCITY, ACCELERATION_GAIN);

    Robot.driveTrain.resetEncoders();
    Robot.driveTrain.resetGyro();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftOutput = left.calculate(Robot.driveTrain.getLeftEncPosition());
    double rightOutput = right.calculate(Robot.driveTrain.getRightEncPosition());

    double gyroHeading = Robot.driveTrain.getGyroAngle();
    double desiredHeading = Pathfinder.r2d(left.getHeading());
    double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    // I'm not sure how turn is calculated, I'm just copying code from the pathfinder documentation
    double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

    Robot.driveTrain.tankDrive(leftOutput + turn, rightOutput - turn);
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return left.isFinished() && right.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.mecanumDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
