/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntake extends Subsystem {
  // Constants
  private static double INTAKE_SPEED = -1.00;
  private static double SHOOT_SPEED = 1.00;

  private static boolean INTAKE_RAISE = true;
  private static boolean INTAKE_LOWER = !INTAKE_RAISE;

  // Motors
  private WPI_VictorSPX intakeMotor = new WPI_VictorSPX(RobotMap.CARGO_INTAKE_MOTOR);

  // Pistons
  private Solenoid intakeLift = new Solenoid(RobotMap.CARGO_INTAKE_LIFT);

  // Control variables
  private boolean isRaised = false;

  @Override
  public void initDefaultCommand() {
  }

  public void intake() {
    move(true);
  }

  public void shoot() {
    move(false);
  }

  public void stop() {
    move(0.0);
  }

  public void move(double axis) {
    intakeMotor.set(-axis);
  }

  public void move(boolean in) {
    move(in ? INTAKE_SPEED : SHOOT_SPEED);
  }

  public void raise() {
    isRaised = true;
    intakeLift.set(INTAKE_RAISE);
  }

  public void lower() {
    isRaised = false;
    intakeLift.set(INTAKE_LOWER);
  }

  public boolean isRaised() {
    return isRaised;
  }
}
