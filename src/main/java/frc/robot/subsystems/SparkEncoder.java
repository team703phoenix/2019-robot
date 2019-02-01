/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class SparkEncoder extends Encoder {
  // Constants
  public static final int TICKS_PER_ROTATION = 42;

  // Encoder
  private CANEncoder encoder;

  public SparkEncoder(CANSparkMax attachedMotor) {
    super(TICKS_PER_ROTATION);

    encoder = new CANEncoder(attachedMotor);
  }

  @Override
  public double getRawPosition() {
    return encoder.getPosition();
  }

  @Override
  public double getVelocity() {
    return encoder.getVelocity();
  }
}
