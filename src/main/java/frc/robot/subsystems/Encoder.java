/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Encoder extends Subsystem {
  // Constants
  public static final int TICKS_PER_ROTATION = 0; // Dummy value

  // Encoder
  private CANEncoder encoder;
    
  // Control variables
  private double zeroedPosition = 0.0;

  public void initDefaultCommand() {
  }
  
  /** Creates a new encoder attached to the given Spark Max motor controller */
  public Encoder(CANSparkMax attachedMotor) {
    encoder = new CANEncoder(attachedMotor);
  }
  
  /** Returns the raw, unzeroed position of the encoder in ticks */
  public double getRawPosition() {
    return encoder.getPosition();
  }
  
  /** Returns the position of the encoder in ticks,
   * relative to the zero point set by the reset() function */
  public double getPosition() {
    return getRawPosition() - zeroedPosition;
  }
  
  /** Returns the velocity of the encoder in ticks */
  public double getVelocity() {
    return encoder.getVelocity();
  }
  
  /** Resets the encoder's position value to 0 */
  public void reset() {
    zeroedPosition = getRawPosition();
  }

  public static double inchesToTicks(double inches) {
    return inches / (DriveTrain.WHEEL_DIAMETER * Math.PI) * TICKS_PER_ROTATION;
  }

  public static double ticksToInches(double ticks) {
    return (ticks * DriveTrain.WHEEL_DIAMETER * Math.PI) / TICKS_PER_ROTATION;
  }
}
