/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonEncoder extends Encoder {
  // Constants
  public static final int TICKS_PER_ROTATION = 4096;
  private static final int HARD_RESET_TIMEOUT = 100;

  // Encoder
  private WPI_TalonSRX attachedMotor;

  public TalonEncoder(WPI_TalonSRX attachedMotor) {
    super(TICKS_PER_ROTATION);

    this.attachedMotor = attachedMotor;

    this.attachedMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  @Override
  public double getRawPosition() {
    return attachedMotor.getSelectedSensorPosition();
  }

  public void hardReset() {
    attachedMotor.getSensorCollection().setQuadraturePosition(0, HARD_RESET_TIMEOUT);
    zeroedPosition = getRawPosition();
  }

  @Override
  public double getVelocity() {
    return attachedMotor.getSelectedSensorVelocity();
  }
}
