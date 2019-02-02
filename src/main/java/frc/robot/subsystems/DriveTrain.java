/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithController;

public class DriveTrain extends Subsystem {
  // Constants
  public static final double ACCELERATION_RATE = 0.05;
  public static final double WHEEL_DIAMETER = 6.0; // Dummy value
  public static final double DRIVE_DEADBAND = 0.07;
  public static final double GYRO_CORRECTION_SCALER = 0.08;

  // Drive motors
  private CANSparkMax frontLeft1 = new CANSparkMax(RobotMap.FL_DRIVE_1, MotorType.kBrushless),
  frontLeft2 = new CANSparkMax(RobotMap.FL_DRIVE_2, MotorType.kBrushless),
  rearLeft1 = new CANSparkMax(RobotMap.RL_DRIVE_1, MotorType.kBrushless),
  rearLeft2 = new CANSparkMax(RobotMap.RL_DRIVE_2, MotorType.kBrushless),
  frontRight1 = new CANSparkMax(RobotMap.FR_DRIVE_1, MotorType.kBrushless),
  frontRight2 = new CANSparkMax(RobotMap.FR_DRIVE_2, MotorType.kBrushless),
  rearRight1 = new CANSparkMax(RobotMap.RR_DRIVE_1, MotorType.kBrushless),
  rearRight2 = new CANSparkMax(RobotMap.RR_DRIVE_2, MotorType.kBrushless);

  // Drive train
  private MecanumDrive drive;

  // Encoders
  public Encoder frontLeftEnc = new SparkEncoder(frontLeft1),
  rearLeftEnc = new SparkEncoder(rearLeft1),
  frontRightEnc = new SparkEncoder(frontRight1), 
  rearRightEnc = new SparkEncoder(rearRight1);

  // Gyro
  public Gyro gyro = new Gyro(RobotMap.GYRO_ID);

  // Control variables
  private double xDrive = 0.0, yDrive = 0.0, turnDrive = 0.0;

  public DriveTrain() {
    frontLeft1.setInverted(false);
    rearLeft1.setInverted(false);
    frontRight1.setInverted(false);
    rearRight1.setInverted(false);

    frontLeft2.follow(frontLeft1);
    rearLeft2.follow(rearLeft1);
    frontRight2.follow(frontRight1);
    rearRight2.follow(rearRight1);

    drive = new MecanumDrive(frontLeft1, rearLeft1, frontRight1, rearRight1);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithController());
  }

  //************************************************
  //
  // DRIVING
  //
  //************************************************

  /** Drives the robot using mecanum drive, without an acceleration curve */
  public void forcedMecanumDrive(double x, double y, double turn) {
    yDrive = y;
    xDrive = x;
    turnDrive = turn;

    drive.driveCartesian((Math.abs(xDrive) > DRIVE_DEADBAND) ? xDrive : 0.0, 
      (Math.abs(yDrive) > DRIVE_DEADBAND) ? yDrive : 0.0,
      (Math.abs(turnDrive) > DRIVE_DEADBAND) ? turnDrive : 0.0);
  }

  /** Drives the robot using mecanum drive, with an acceleration curve */
  public void mecanumDrive(double x, double y, double turn) {
    yDrive = accelDecel(y, yDrive);
    xDrive = accelDecel(x, xDrive);
    turnDrive = accelDecel(turn, turnDrive);

    drive.driveCartesian((Math.abs(xDrive) > DRIVE_DEADBAND) ? xDrive : 0.0,
      (Math.abs(yDrive) > DRIVE_DEADBAND) ? yDrive : 0.0, 
      (Math.abs(turnDrive) > DRIVE_DEADBAND) ? turnDrive : 0.0);
  }

  /** Drives the robot using mecanum drive, with an acceleration curve */
  public void mecanumDrive() {
    mecanumDrive(Robot.oi.getXDrive(), Robot.oi.getYDrive(), Robot.oi.getTurnDrive());
  }

  /** Drives the robot using mecanum drive in a field-oriented manner, with an acceleration curve */
  public void mecanumDrive_fieldOriented(double x, double y, double turn) {
    yDrive = accelDecel(y, yDrive);
    xDrive = accelDecel(x, xDrive);
    turnDrive = accelDecel(turn, turnDrive);

    drive.driveCartesian((Math.abs(xDrive) > DRIVE_DEADBAND) ? xDrive : 0.0, 
      (Math.abs(yDrive) > DRIVE_DEADBAND) ? yDrive : 0.0,
      (Math.abs(turnDrive) > DRIVE_DEADBAND) ? turnDrive : 0.0, gyro.getFieldAngle());
  }

  /** Drives the robot using mecanum drive in a field-oriented manner, with an acceleration curve */
  public void mecanumDrive_fieldOriented() {
    mecanumDrive_fieldOriented(Robot.oi.getXDrive(), Robot.oi.getYDrive(), Robot.oi.getTurnDrive());
  }

  /** Drives the robot using tank drive */
  public void tankDrive(double left, double right) {
    double forward = (left + right) / 2;
    double turn = (left - right) / 2;

    mecanumDrive(0, forward, turn);
  }


  public void gyroAssistedDrive(double speed) {
		mecanumDrive(0, speed, -getGyroCorrectionAngle());
	}

  public static double accelDecel(double desiredSpeed, double currentSpeed) {
    double acceptedRange = ACCELERATION_RATE / 2.0;

    if (desiredSpeed > currentSpeed + acceptedRange)
      currentSpeed += ACCELERATION_RATE;
    else if (desiredSpeed < currentSpeed - acceptedRange)
      currentSpeed -= ACCELERATION_RATE;
    else
      currentSpeed = desiredSpeed;

    return currentSpeed;
  }

  //************************************************
  //
  // ENCODERS
  //
  //************************************************

  /** Used for tank-style driving, where you only need one encoder per side.
   * Uses the front left encoder. */
  public double getLeftEncPosition() {
    return frontLeftEnc.getPosition();
  }

  /** Used for tank-style driving, where you only need one encoder per side.
   * Uses the front right encoder. */
  public double getRightEncPosition() {
    return frontRightEnc.getPosition();
  }

  /** Resets all the drivetrain encoders */
  public void resetEncoders() {
    frontLeftEnc.reset();
    rearLeftEnc.reset();
    frontRightEnc.reset();
    rearRightEnc.reset();
  }

  /** Used for tank-style driving, where you only need one encoder per side.
   * Uses the front left encoder. */
  public double getLeftEncVelocity() {
    return frontLeftEnc.getVelocity();
  }

  /** Used for tank-style driving, where you only need one encoder per side.
   * Uses the front right encoder. */
  public double getRightEncVelocity() {
    return frontRightEnc.getVelocity();
  }

  /** Converts a raw axis value to the curve used for the drive */
  public double calculateDrive(double axis) {
    return Math.pow(axis, 3);
  }

  public double getGyroCorrectionAngle() {
    return gyro.getAngle() * GYRO_CORRECTION_SCALER;
  }
}
