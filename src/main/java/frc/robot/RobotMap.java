/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  // This is just some dummy code so I can test some stuff

  // Drive motor IDs
  public static final int FL_DRIVE_1 = 1, FL_DRIVE_2 = 2, RL_DRIVE_1 = 3, RL_DRIVE_2 = 4,
                          FR_DRIVE_1 = 5, FR_DRIVE_2 = 6, RR_DRIVE_1 = 7, RR_DRIVE_2 = 8;

  public static final int DRIVE_ENC_TICKS_PER_ROTATION = 960;
  public static final double WHEEL_DIAMETER = 6 * 0.0254; // Wheel diameter in meters
  public static final double MAX_VELOCITY = 10.0 * 0.3048; // Max velocity in m/s
}
