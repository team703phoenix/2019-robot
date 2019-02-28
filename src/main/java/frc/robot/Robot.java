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
  public static CargoIntake cargoIntake;
  public static HatchMechanism hatchMechanism;
  public static ClimbingMechanism climber;
  public static Vision vision;
  public static LineFollower lineFollower;
  public static OI oi;

  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    elevator = new Elevator();
    cargoIntake = new CargoIntake();
    hatchMechanism = new HatchMechanism();
    climber = new ClimbingMechanism();
    vision = new Vision();
    lineFollower = new LineFollower();
    oi = new OI();
  }

  @Override
  public void robotPeriodic() {
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
    vision.setVisionProcessingOff();
    cargoIntake.lower();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    vision.setVisionProcessingOn();
    vision.setLEDOn();
    cargoIntake.lower();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}