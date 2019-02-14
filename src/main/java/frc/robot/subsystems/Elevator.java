/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Elevator extends Subsystem {
  //*******************************************************************************
  //
  // NOTE: The measured elevator height is relative to the middle of the cargo
  //
  //*******************************************************************************

  // Constants
  public static final double ELEVATOR_DEADBAND = 0.10;
  public static final boolean BRAKE_ENGAGE = true;
  public static final boolean BRAKE_RELEASE = !BRAKE_ENGAGE;

  public static final double ACCEPTED_RANGE = 0.1 / 2; // 0.1 inches

  public static final double CARGO_BOTTOM_HEIGHT = 0.0; // 0 inches (dummy value)
  public static final double HATCH_HEIGHT_OFFSET = 0.0; // 0 inches (dummy value)

  public static final int TICKS_PER_ROTATION = TalonEncoder.TICKS_PER_ROTATION;
  public static final double SHAFT_OUTPUT_RATIO = 47.5; // 47.5 : 1
  public static final double PULLEY_RADIUS = 1.375; // 1.375 inches
  public static final double CABLE_TO_ELEVATOR_RATIO = 0.0; // 0 : 1 (dummy value)
  public static final double ENCODER_TO_HEIGHT_RATIO = SHAFT_OUTPUT_RATIO * PULLEY_RADIUS *
    CABLE_TO_ELEVATOR_RATIO / TICKS_PER_ROTATION;

  // Motors
  private WPI_TalonSRX motor1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_1),
  motor2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_2);

  // Pistons
  private Solenoid brake = new Solenoid(RobotMap.ELEVATOR_BRAKE);

  // Encoder
  public TalonEncoder encoder;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Elevator() {
    motor2.follow(motor1);

    encoder = new TalonEncoder(motor1);
  }

  /** Moves the elevator at the given speed (positive speed moves the elevator up) */
  public void move(double speed) {
    if (Math.abs(speed) >= ELEVATOR_DEADBAND) {
      brake.set(BRAKE_RELEASE);
      motor1.set(speed);
    } else {
      brake.set(BRAKE_ENGAGE);
      motor1.set(0.0);
    }
  }

  /** Returns whether the given height is within the accepted range for feedback control */
  public boolean inAcceptedRange(double desiredAbsoluteHeightInInches) {
    return getAbsoluteHeight() >= desiredAbsoluteHeightInInches - ACCEPTED_RANGE &&
    getAbsoluteHeight() <= desiredAbsoluteHeightInInches + ACCEPTED_RANGE;
  }

  /** Returns the elevator height in inches, relative to the bottom */
  public double getRelativeHeight() {
    return encoder.getPosition() * ENCODER_TO_HEIGHT_RATIO;
  }

  /** Returns the elevator height in inches, relative to the floor */
  public double getAbsoluteHeight() {
    return getRelativeHeight() + CARGO_BOTTOM_HEIGHT;
  }
}
