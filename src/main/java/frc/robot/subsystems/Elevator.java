/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ControlElevator;

public class Elevator extends PIDSubsystem {
  //*******************************************************************************
  //
  // NOTE: The measured elevator height is relative to the middle of the cargo
  //
  //*******************************************************************************

  // Constants
  public static final double kP = 0.05;
  public static final double kI = 0.002;
  public static final double kD = 0.002;

  public static final double ELEVATOR_DEADBAND = 0.10;
  public static final boolean BRAKE_ENGAGE = true;
  public static final boolean BRAKE_RELEASE = !BRAKE_ENGAGE;

  public static final double ACCEPTED_RANGE = 0.1; // 0.1 inches

  public static final double CARGO_BOTTOM_HEIGHT = 7.5; // 7.5 inches (center of the cargo)
  public static final double HATCH_HEIGHT_OFFSET = 0.0; // 0 inches (dummy value)

  public static final int TICKS_PER_ROTATION = TalonEncoder.TICKS_PER_ROTATION;
  public static final double SHAFT_OUTPUT_RATIO = 0.75; // 3 : 4
  public static final double PULLEY_RADIUS = 1.375; // 1.375 inches
  public static final double CABLE_TO_ELEVATOR_RATIO = 1.065; // 1.065 : 1
  public static final double ENCODER_TO_HEIGHT_RATIO = 2 * Math.PI * SHAFT_OUTPUT_RATIO *
    PULLEY_RADIUS * CABLE_TO_ELEVATOR_RATIO / TICKS_PER_ROTATION;

  // Motors
  private WPI_TalonSRX motor1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_1),
    motor2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_2);

  // Pistons
  private Solenoid brake = new Solenoid(RobotMap.ELEVATOR_BRAKE);

  // Encoder
  public TalonEncoder encoder;

  // Limit switches
  private DigitalInput lowerLimitSwitch = new DigitalInput(RobotMap.ELEVATOR_LOWER_LIMIT_SWITCH);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ControlElevator());
  }

  public Elevator() {
    super(kP, kI, kD);
    setAbsoluteTolerance(ACCEPTED_RANGE);
    getPIDController().setContinuous(false);
    setOutputRange(-1.0, 1.0);

    motor2.follow(motor1);

    encoder = new TalonEncoder(motor1, true);
  }

  /** Moves the elevator at the given speed (positive speed moves the elevator up) */
  public void move(double speed) {
    if (Math.abs(speed) >= ELEVATOR_DEADBAND && (speed > 0.0 || !getLowerLimitSwitch())) {
      releaseBrake();
      motor1.set(speed);
    } else {
      engageBrake();
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

  @ Override
  protected double returnPIDInput() {
    return getAbsoluteHeight();
  }

  @ Override
  protected void usePIDOutput(double output) {
    if (!getLowerLimitSwitch()) {
      releaseBrake();
      motor1.set(output);
    } else {
      motor1.set(0.0);
      engageBrake();
    }
  }

  public void engageBrake() {
    brake.set(BRAKE_ENGAGE);
  }

  public void releaseBrake() {
    brake.set(BRAKE_RELEASE);
  }

  public boolean getLowerLimitSwitch() {
    if (lowerLimitSwitch.get() && encoder.getPosition() != 0.0)
      encoder.reset();

    return lowerLimitSwitch.get();
  }
}
