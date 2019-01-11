/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RobotMap {
  // This is just some dummy code so I can test some stuff
  public static WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(1);
  public static WPI_TalonSRX rearLeftDrive = new WPI_TalonSRX(2);
  public static WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(3);
  public static WPI_TalonSRX rearRightDrive = new WPI_TalonSRX(4);

  public static final int DRIVE_ENC_TICKS_PER_ROTATION = 960;
  public static final double WHEEL_DIAMETER = 6 * 0.0254; // Wheel diameter in meters
  public static final double MAX_VELOCITY = 10.0 * 0.3048; // Max velocity in m/s
}
