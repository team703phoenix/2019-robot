/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyro extends Subsystem {
  // Constants
  private final double GYRO_SCALER = 1.00;

  // Gyro
  private ADXRS450_Gyro gyro; // Not sure which gyro we're using, but we can fix that later
  
  // Control variables
  private double zeroedPosition = 0.0;

  @Override
  public void initDefaultCommand() {
  }

  public double getRawAngle() {
    return gyro.getAngle() * GYRO_SCALER;
  }

  public double getAngle() {
    return getRawAngle() - zeroedPosition;
  }

  public double getFieldAngle() {
    return ((getRawAngle() + 180) % 360) - 180; 
  }

  public void reset() {
    zeroedPosition = getRawAngle();
  }
}
