/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class LineFollower extends Subsystem {
  // Photoelectric sensors
  private DigitalInput leftSensor = new DigitalInput(RobotMap.LINE_FOLLOWER_LEFT);
  private DigitalInput rightSensor = new DigitalInput(RobotMap.LINE_FOLLOWER_RIGHT);

  @Override
  public void initDefaultCommand() {
  }

  public boolean getLeftSensor() {
    return leftSensor.get();
  }

  public boolean getRightSensor() {
    return rightSensor.get();
  }
}
