/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoysticks;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

public class DriveTrain extends Subsystem {
  // Motion profiling constants
  private final double POSITION_TO_MOTOR = 0.2;
  private final double LEFT_VELOCITY_TO_MOTOR = 0.00706;
  private final double RIGHT_VELOCITY_TO_MOTOR = 0.00685;

  // Drive motors
  private MecanumDrive drive;

  // Trajectories
  Trajectory leftTraj, rightTraj;
  int trajLength;
  int trajPosCount = 0;

  public DriveTrain() {
    drive = new MecanumDrive(RobotMap.frontLeftDrive, RobotMap.rearLeftDrive,
      RobotMap.frontRightDrive, RobotMap.rearRightDrive);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoysticks());
  }

  //************************************************
  //
  // DRIVING
  //
  //************************************************

  /** Drives the robot using mecanum drive*/
  public void mecanumDrive(double x, double y, double turn) {
    drive.driveCartesian(y, x, turn);
  }

  /** Drives the robot using mecanum drive in a field-oriented manner */
  public void mecanumDrive_fieldOriented(double x, double y, double turn) {
    drive.driveCartesian(y, x, turn, getFieldGyroAngle());
  }

  /** TODO: Find out how to implement tank drive on a mecanum drive system */
  public void tankDrive(double left, double right) {
    double forward = (left + right) / 2;
    double turn = (left - right) / 2;

    mecanumDrive(0, forward, turn);
  }

  //************************************************
  //
  // MOTION PROFILING
  //
  //************************************************

  public void setTrajectories(Trajectory leftTraj, Trajectory rightTraj) {
    this.leftTraj = leftTraj;
    this.rightTraj = rightTraj;
    trajLength = leftTraj.length();
  }

  public void runMotionProfile() {
    Segment leftSeg = leftTraj.get(trajPosCount);
    Segment rightSeg = rightTraj.get(trajPosCount);

    // Error calculation
    double leftError = leftSeg.position - getLeftEncPosition();
    double rightError = rightSeg.position - getRightEncPosition();

    // Term calculation
		double leftValue = LEFT_VELOCITY_TO_MOTOR * leftSeg.velocity + POSITION_TO_MOTOR * leftError;
    double rightValue = RIGHT_VELOCITY_TO_MOTOR * rightSeg.velocity +
      POSITION_TO_MOTOR * rightError;

		tankDrive(leftValue, rightValue);

		trajPosCount++;
  }

  public boolean motionProfileIsDone() {
    return trajPosCount >= trajLength;
  }

  //************************************************
  //
  // GYRO & ENCODERS
  //
  //************************************************

  /** TODO: Make getGyroAngle() function */
  public double getGyroAngle() {
    return 0.0;
  }

  /** Returns the current gyro angle, bounded from -180 to 180 degrees for field-orientation */
  public double getFieldGyroAngle() {
    return ((getGyroAngle() + 180) % 360) - 180;
  }

  /** TODO: Make getLeftEncPosition() function */
  public int getLeftEncPosition() {
    return 0;
  }

  /** TODO: Make getRightEncPosition() function */
  public int getRightEncPosition() {
    return 0;
  }

  /** TODO: Make resetEncoders() function */
  public void resetEncoders() {
    trajPosCount = 0;
  }

  /** TODO: Make getLeftEncVelocity() function */
  public int getLeftEncVelocity() {
    return 0;
  }

  /** TODO: Make getRightEncVelocity() function */
  public int getRightEncVelocity() {
    return 0;
  }
}
