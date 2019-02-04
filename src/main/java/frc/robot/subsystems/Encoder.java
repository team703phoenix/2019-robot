/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public abstract class Encoder {
  // Control variables
  protected int ticksPerRotation;

  // Encoder
  private CANEncoder encoder;
    
  // Control variables
  protected double zeroedPosition = 0.0;

  public void initDefaultCommand() {
  }
  
  /** Creates a new encoder attached to the given Spark Max motor controller */
  public Encoder(int ticksPerRotation) {
    this.ticksPerRotation = ticksPerRotation;
  }
  
  /** Returns the raw, unzeroed position of the encoder in ticks */
  public abstract double getRawPosition();
  
  /** Returns the position of the encoder in ticks,
   * relative to the zero point set by the reset() function */
  public double getPosition() {
    return getRawPosition() - zeroedPosition;
  }
  
  /** Returns the velocity of the encoder in ticks */
  public abstract double getVelocity();
  
  /** Resets the encoder's position value to 0 */
  public void reset() {
    zeroedPosition = getRawPosition();
  }

  public double driveInchesToTicks(double inches) {
    return inches / (DriveTrain.WHEEL_DIAMETER * Math.PI) * ticksPerRotation;
  }

  public double driveTicksToInches(double ticks) {
    return (ticks * DriveTrain.WHEEL_DIAMETER * Math.PI) / ticksPerRotation;
  }
}
