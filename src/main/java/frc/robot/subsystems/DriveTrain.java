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
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoysticks;

public class DriveTrain extends Subsystem {
  // Constants
  public static final double WHEEL_DIAMETER = 6.0; // Dummy value

  // Drive motors
  private CANSparkMax frontLeft1 = new CANSparkMax(RobotMap.FL_DRIVE_1, MotorType.kBrushless),
  rearLeft1 = new CANSparkMax(RobotMap.RL_DRIVE_1, MotorType.kBrushless),
  frontRight1 = new CANSparkMax(RobotMap.FR_DRIVE_1, MotorType.kBrushless),
  rearRight1 = new CANSparkMax(RobotMap.RR_DRIVE_1, MotorType.kBrushless);

  // Drive train
  private MecanumDrive drive;

  // Encoders
  public Encoder frontLeftEnc = new Encoder(frontLeft1), rearLeftEnc = new Encoder(rearLeft1),
  frontRightEnc = new Encoder(frontRight1), rearRightEnc = new Encoder(rearRight1);

  // Gyro
  //TODO: Change this to whatever the pigeon is plugged into and test to see if it works
  public Gyro gyro = new Gyro(RobotMap.GYRO_ID);

  public DriveTrain() {
    frontLeft1.setInverted(true);
    rearLeft1.setInverted(true);
    frontRight1.setInverted(false);
    rearRight1.setInverted(false);

    drive = new MecanumDrive(frontLeft1, rearLeft1, frontRight1, rearRight1);
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

  /** Drives the robot using mecanum drive */
  public void mecanumDrive(double x, double y, double turn) {
    drive.driveCartesian(y, x, turn);
  }

  /** Drives the robot using mecanum drive in a field-oriented manner */
  public void mecanumDrive_fieldOriented(double x, double y, double turn) {
    drive.driveCartesian(y, x, turn, gyro.getFieldAngle());
  }

  /** TODO: Test tank drive on a mecanum chassis to see how well it works */
  /** Drives the robot using tank drive */
  public void tankDrive(double left, double right) {
    double forward = (left + right) / 2;
    double turn = (left - right) / 2;

    mecanumDrive(0, forward, turn);
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
}
