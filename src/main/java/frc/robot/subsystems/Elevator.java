/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Elevator extends Subsystem {
  // Constants
  public static final double BOTTOM_HEIGHT = elevatorInchesToTicks(0.0);
  public static final double HATCH_HEIGHT_OFFSET = elevatorInchesToTicks(0.0);
  public static final int TICKS_PER_ROTATION = TalonEncoder.TICKS_PER_ROTATION;
  public static final int ACCEPTED_RANGE = 10 / 2;

  // Motors
  private WPI_TalonSRX motor1 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_1),
  motor2 = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR_2);

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

  public void move(double speed) {
    motor1.set(speed);
  }

  public static double elevatorTicksToInches(double ticks) {
    return 0.0;
  }

  public static double elevatorInchesToTicks(double inches) {
    return 0.0;
  }

  public boolean inAcceptedRange(double desiredHeightInTicks) {
    return heightInTicks() >= desiredHeightInTicks - ACCEPTED_RANGE &&
    heightInTicks() <= desiredHeightInTicks + ACCEPTED_RANGE;
  }

  public double heightInTicks() {
    return encoder.getPosition() + BOTTOM_HEIGHT;
  }
}
