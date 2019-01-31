/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;


import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyro extends Subsystem {
  // Constants
  private final double GYRO_SCALER = 1.00;

  // IMU
  private PigeonIMU pigeon;
  
  // Control variables
  private double zeroedPosition = 0.0;

  public Gyro(int deviceID) {
    pigeon = new PigeonIMU(deviceID);
  }

  @Override
  public void initDefaultCommand() {
  }

  public double getRawAngle() {
    double[] angles = {0.0, 0.0, 0.0};
    pigeon.getAccumGyro(angles);
    return  -angles[2] * GYRO_SCALER;
  }

  public double getAngle() {
    return getRawAngle() - zeroedPosition;
  }

  public double getFieldAngle() {
    return -(((getRawAngle() + 180) % 360) - 180); 
  }

  public void reset() {
    zeroedPosition = getRawAngle();
  }

  public void hardReset() {
    pigeon.setAccumZAngle(0.0, 0);
    zeroedPosition = 0.0;
		while (Math.abs(getRawAngle()) > 3);
  }
}
