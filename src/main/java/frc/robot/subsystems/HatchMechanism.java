/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchMechanism extends Subsystem {
  // Constants
  private static final boolean HATCH_CLAMP = false;
  private static final boolean HATCH_RELEASE = !HATCH_CLAMP;
  private static final boolean HATCH_EXTEND = true;
  private static final boolean HATCH_RETRACT = !HATCH_EXTEND;

  // Pistons
  private Solenoid clampPiston = new Solenoid(RobotMap.HATCH_CLAMP_PISTON),
    extendPiston = new Solenoid(RobotMap.HATCH_EXTEND_PISTON);

  // Control variables
  private boolean isClamped = false, isExtended = false;

  @Override
  public void initDefaultCommand() {
  }

  public void clamp() {
    isClamped = true;
    clampPiston.set(HATCH_CLAMP);
  }

  public void release() {
    isClamped = false;
    clampPiston.set(HATCH_RELEASE);
  }

  public boolean isClamped() {
    return isClamped;
  }

  public void extend() {
    isExtended = true;
    extendPiston.set(HATCH_EXTEND);
  }

  public void retract() {
    isExtended = false;
    extendPiston.set(HATCH_RETRACT);
  }

  public boolean isExtended() {
    return isExtended;
  }
}
