/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static Vision vision;
  public static OI oi;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    elevator = new Elevator();
    vision = new Vision();
    oi = new OI();

    driveTrain.gyro.hardReset();
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Front left enc", driveTrain.frontLeftEnc.getPosition());
    SmartDashboard.putNumber("Rear left enc", driveTrain.rearLeftEnc.getPosition());
    SmartDashboard.putNumber("Front right enc", driveTrain.frontRightEnc.getPosition());
    SmartDashboard.putNumber("rear right enc", driveTrain.rearRightEnc.getPosition());
    SmartDashboard.putNumber("Gyro angle", driveTrain.gyro.getAngle());
  }

  @Override
  public void disabledInit() {
    Scheduler.getInstance().removeAll();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    driveTrain.gyro.hardReset();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    System.out.println("ta: " + vision.getArea());
  }

  @Override
  public void testPeriodic() {
  }
}

// ░░░░░░░░░░░░░░░░██████████████████
// ░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░████
// ░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██
// ░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██
// ░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██
// ░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██████░░░░██
// ░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██████░░░░██
// ░░░░░░░░██░░░░██████░░░░██░░░░██████░░░░██
// ░░░░░░░░░░██░░░░░░░░░░██████░░░░░░░░░░██
// ░░░░░░░░████░░██░░░░░░░░░░░░░░░░░░██░░████
// ░░░░░░░░██░░░░██████████████████████░░░░██
// ░░░░░░░░██░░░░░░██░░██░░██░░██░░██░░░░░░██
// ░░░░░░░░░░████░░░░██████████████░░░░████
// ░░░░░░░░██████████░░░░░░░░░░░░░░██████████
// ░░░░░░██░░██████████████████████████████░░██
// ░░░░████░░██░░░░██░░░░░░██░░░░░░██░░░░██░░████
// ░░░░██░░░░░░██░░░░██████░░██████░░░░██░░░░░░██
// ░░██░░░░████░░██████░░░░██░░░░██████░░████░░░░██
// ░░██░░░░░░░░██░░░░██░░░░░░░░░░██░░░░██░░░░░░░░██
// ░░██░░░░░░░░░░██░░██░░░░░░░░░░██░░██░░░░░░░░░░██
// ░░░░██░░░░░░██░░░░████░░░░░░████░░░░██░░░░░░██
// ░░░░░░████░░██░░░░██░░░░░░░░░░██░░░░██░░████
// ░░░░░░░░██████░░░░██████████████░░░░██████
// ░░░░░░░░░░████░░░░██████████████░░░░████
// ░░░░░░░░██████████████████████████████████
// ░░░░░░░░████████████████░░████████████████
// ░░░░░░░░░░████████████░░░░░░████████████
// ░░░░░░██████░░░░░░░░██░░░░░░██░░░░░░░░██████
// ░░░░░░██░░░░░░░░░░████░░░░░░████░░░░░░░░░░██
// ░░░░░░░░██████████░░░░░░░░░░░░░░██████████