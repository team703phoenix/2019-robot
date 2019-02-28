/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimbingMechanism extends Subsystem {
  // Constants
  private static final double FRONT_MOVE_SPEED = 0.60;
  private static final double BACK_MOVE_SPEED = 0.60;
  private static final double WHEEL_MOVE_SPEED = 0.60;

  // Motors
  private WPI_VictorSPX frontLeftLift = new WPI_VictorSPX(RobotMap.CLIMBER_FRONT_LEFT),
    frontRightLift = new WPI_VictorSPX(RobotMap.CLIMBER_FRONT_RIGHT), 
    backLift = new WPI_VictorSPX(RobotMap.CLIMBER_BACK),
    wheels = new WPI_VictorSPX(RobotMap.CLIMBER_WHEELS);

  public ClimbingMechanism() {
    frontRightLift.follow(frontLeftLift);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void moveFront(double axis) {
    frontLeftLift.set(axis);
  }

  public void moveFrontUp() {
    moveFront(FRONT_MOVE_SPEED);
  }

  public void moveFrontDown() {
    moveFront(-FRONT_MOVE_SPEED);
  }

  public void stopFront() {
    moveFront(0.0);
  }

  public void moveBack(double axis) {
    backLift.set(axis);
  }

  public void moveBackUp() {
    moveBack(BACK_MOVE_SPEED);
  }

  public void moveBackDown() {
    moveBack(-BACK_MOVE_SPEED);
  }

  public void stopBack() {
    moveBack(0.0);
  }
}
