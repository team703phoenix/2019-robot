/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
  // Drive motor IDs
  public static final int FL_DRIVE_1 = 11, FL_DRIVE_2 = 12, RL_DRIVE_1 = 13, RL_DRIVE_2 = 14,
    FR_DRIVE_1 = 15, FR_DRIVE_2 = 16, RR_DRIVE_1 = 17, RR_DRIVE_2 = 18;

  // Gyro ID
  public static final int GYRO_ID = 19;

  // Elevator motor IDs
  public static final int ELEVATOR_MOTOR_1 = 21, ELEVATOR_MOTOR_2 = 22;

  // Elevator piston IDs
  public static final int ELEVATOR_BRAKE = 0;

  // Elevator limit switch IDs
  public static final int ELEVATOR_LOWER_LIMIT_SWITCH = 0;

  // Cargo intake motor IDs
  public static final int CARGO_INTAKE_MOTOR = 23;

  // Cargo intake piston IDs
  public static final int CARGO_INTAKE_LIFT = 1;

  // Hatch mechanism piston IDs
  public static final int HATCH_CLAMP_PISTON = 2, HATCH_EXTEND_PISTON = 3;

  // Climbing mechanism motor IDs
  public static final int CLIMBER_FRONT_LEFT = 24, CLIMBER_FRONT_RIGHT = 25, CLIMBER_BACK = 26, 
    CLIMBER_WHEELS = 27;

  // Line follower photoelectric sensor IDs
  public static final int LINE_FOLLOWER_LEFT = 1, LINE_FOLLOWER_RIGHT = 2;
}
