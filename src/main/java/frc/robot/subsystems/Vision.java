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
  private final int TARGET_PIPELINE = 0;
  private final int CARGO_PIPELINE = 1;

  // Limelight network table
  private NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

  // Variables
  private double latestErrorX = 0.0;

  // Timer
  private Timer timer = new Timer();

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

  public void setPipelineLED() {
    setNumber("ledMode", 0);
  }

  public void setLEDOn() {
    setNumber("ledMode", 3);
  }

  public void setLEDOff() {
    setNumber("ledMode", 1);
  }

  public void setLEDBlink() {
    setNumber("ledMode", 2);
  }

  public void setVisionProcessingOn() {
    setNumber("camMode", 0);
  }

  public void setVisionProcessingOff() {
    setNumber("camMode", 1);
  }

  public void setTargetPipeline(int timeoutMs) {
    setPipeline(TARGET_PIPELINE);
    sleep(timeoutMs);
  }

  public void setCargoPipeline(int timeoutMs) {
    setPipeline(CARGO_PIPELINE);
    sleep(timeoutMs);
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
  
  private void sleep(int timeoutMs) {
    timer.reset();
    while(timer.get() <= timeoutMs / 1000.0);
  }
}
