/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

public abstract class Encoder { 
  // Control variables
  protected double zeroedPosition = 0.0;
  protected boolean inverted;

  public void initDefaultCommand() {
  }

  /** Creates a new encoder */
  public Encoder(boolean inverted) {
    this.inverted = inverted;
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
}
