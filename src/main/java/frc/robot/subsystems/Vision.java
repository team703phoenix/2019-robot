/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {
  // Constants
  private static final int TARGET_PIPELINE = 0;
  private static final int CARGO_PIPELINE = 1;

  public static final int LED_PIPELINE = 0;
  public static final int LED_ON = 3;
  public static final int LED_OFF = 1;
  public static final int LED_BLINK = 2;

  // Limelight network table
  private NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

  // Variables
  private double latestErrorX = 0.0;
  
  @Override
  public void initDefaultCommand() {
  }

  public double getErrorX() {
    return getDouble("tx");
  }
  
  public double getErrorY() {
    return getDouble("ty");
  }

  public double getLatestErrorX() {
    return latestErrorX;
  }

  public void updateLatestErrorX() {
    double errorX = getErrorX();

    if (errorX != 0.0)
      latestErrorX = errorX;
  }

  public double getLengthVertical() {
    return getDouble("tvert");
  }

  public double getLengthHorizontal() {
    return getDouble("thor");
  }

  public double getLengthLongest() {
    return getDouble("tlong");
  }

  public double getLengthShortest() {
    return getDouble("tshort");
  }

  /** i hate lev */
  public String getHelp() {
    return "aint no help coming ahahaha get fucked loser";
  }

  public double getArea() {
    return getDouble("ta");
  }

  public boolean hasValidTarget() {
    return getNumber("tv") == 1;
  }

  public double getSkew() {
    return getDouble("ts");
  }

  public void setPipeline(int pipelineID) {
    setNumber("pipeline", pipelineID);
  }

  public void setLED(int ledMode) {
    setNumber("ledMode", ledMode);
  }

  public void setPipelineLED() {
    setLED(LED_PIPELINE);
  }

  public void setLEDOn() {
    setLED(LED_ON);
  }

  public void setLEDOff() {
    setLED(LED_OFF);
  }

  public void setLEDBlink() {
    setLED(LED_BLINK);
  }

  public int getLEDMode() {
    return getNumber("ledMode");
  }

  public void setVisionProcessingOn() {
    setNumber("camMode", 0);
  }

  public void setVisionProcessingOff() {
    setNumber("camMode", 1);
  }

  public void setTargetPipeline() {
    setPipeline(TARGET_PIPELINE);
  }

  public void setCargoPipeline() {
    setPipeline(CARGO_PIPELINE);
  }

  /** Sets the given key to the given value */
	private void setNumber(String key, int value) {
		limelight.getEntry(key).setNumber(value);
	}
	
	/** Finds an integer from the limelight output using the given key */
	private int getNumber(String key, int defaultValue) {
		return limelight.getEntry(key).getNumber(defaultValue).intValue();
  }
  
  /** Finds an integer from the limelight output using the given key */
	private int getNumber(String key) {
		return getNumber(key, 0);
	}
	
	/** Finds a double from the limelight output using the given key */
	public double getDouble(String key, double defaultValue) {
		return limelight.getEntry(key).getDouble(defaultValue);
  }
  
  /** Finds a double from the limelight output using the given key */
	public double getDouble(String key) {
		return getDouble(key, 0.0);
  }
}
