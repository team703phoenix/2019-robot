/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class FollowPath extends Command {
  // PID Constants
  private static final double kP = 0.07;
  private static final double kI = 0.0;
  private static final double kD = 0.0;
  private static final double ACCELERATION_GAIN = 0;

  // Constants
  private final double DRIVETRAIN_WHEEL_DIAMETER = 4.25 / 12.0; // in feet
  private final double MAX_VELOCITY = 5.0; // in feet
  private final int ENCODER_TICKS_PER_ROTATION = 2259;

  // Trajectories
  String pathName;
  Trajectory leftTraj, rightTraj;
  EncoderFollower left, right;
  Notifier notifier;

  public FollowPath(String pathName) {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftTraj = PathfinderFRC.getTrajectory(pathName + ".left");
    rightTraj = PathfinderFRC.getTrajectory(pathName + ".right");

    Robot.driveTrain.resetEncoders();
    Robot.driveTrain.gyro.reset();

    EncoderFollower left = new EncoderFollower(leftTraj);
    left.configureEncoder((int)Robot.driveTrain.frontLeftEnc.getPosition(),
      ENCODER_TICKS_PER_ROTATION, DRIVETRAIN_WHEEL_DIAMETER);
    left.configurePIDVA(kP, kI, kD, 1 / MAX_VELOCITY, ACCELERATION_GAIN);
    EncoderFollower right = new EncoderFollower(rightTraj);
    right.configureEncoder((int)Robot.driveTrain.frontRightEnc.getPosition(),
      ENCODER_TICKS_PER_ROTATION, DRIVETRAIN_WHEEL_DIAMETER);
    right.configurePIDVA(kP, kI, kD, 1 / MAX_VELOCITY, ACCELERATION_GAIN);

    notifier = new Notifier(this::followPath);
    notifier.startPeriodic(leftTraj.get(0).dt);
  }

  protected void followPath() {
    if (left.isFinished() || right.isFinished()) {
      notifier.stop();
    } else {
      double leftOutput = left.calculate((int)Robot.driveTrain.frontRightEnc.getPosition());
      double rightOutput = right.calculate((int)Robot.driveTrain.frontLeftEnc.getPosition());

      double gyroHeading = Robot.driveTrain.gyro.getAngle();
      double desiredHeading = Pathfinder.r2d(left.getHeading());
      double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
      // I'm not sure how turn is calculated, I'm just copying code from the pathfinder documentation
      double turn = 0.8 * (1.0 / 80.0) * angleDifference;

      SmartDashboard.putNumber("Left output", leftOutput);
      SmartDashboard.putNumber("Right output", rightOutput);
      SmartDashboard.putNumber("Desired angle", desiredHeading);
      SmartDashboard.putNumber("Gyro angle", gyroHeading);

      Robot.driveTrain.tankDrive(leftOutput + turn, rightOutput - turn);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return left.isFinished() || right.isFinished();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    notifier.stop();
    Robot.driveTrain.forcedMecanumDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
