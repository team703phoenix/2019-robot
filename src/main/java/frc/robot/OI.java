/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

public class OI {
  // Joysticks & controllers
  Joystick driverController = new Joystick(0);
  Joystick operatorController = new Joystick(1);

  // Driver controller axis mappings
  private final int X_DRIVE_AXIS = 0;
  private final int Y_DRIVE_AXIS = 1;
  private final int TURN_DRIVE_AXIS = 2;
  private final int LEFT_DRIVE_AXIS = 1;
  private final int RIGHT_DRIVE_AXIS = 3;

  // Driver controller button mappings
  private final int FRONT_CLIMBER_UP = 5;
  private final int FRONT_CLIMBER_DOWN = 3;
  private final int BACK_CLIMBER_UP = 6;
  private final int BACK_CLIMBER_DOWN = 4;



  // Operator controller axis mappings
  private final int ELEVATOR_DRIVE_AXIS = 1;
  private final int CARGO_INTAKE_AXIS = 3;

  // Operator controller button mappings
  private final int RESET_SENSORS_BTN = 1; // x
  private final int TOGGLE_LED_BTN = 3; // b
  private final int DRIVE_TOWARD_TARGET_BTN = 4; // y
  private final int TEST_ELEVATOR_BTN = 2; // a
  private final int TOGGLE_INTAKE_LIFT_BTN = 11; // Left thumb click
  private final int INTAKE_CARGO_BTN = 7; // Left trigger
  private final int SHOOT_CARGO_BTN = 8; // Right trigger

  public OI() {
    // Driver controller button initializations
    JoystickButton frontClimberUp = new JoystickButton(driverController, FRONT_CLIMBER_UP),
    frontClimberDown = new JoystickButton(driverController, FRONT_CLIMBER_DOWN),
    backClimberUp = new JoystickButton(driverController, BACK_CLIMBER_UP),
    backClimberDown = new JoystickButton(driverController, BACK_CLIMBER_DOWN);

    // Operator controller button initializations
    JoystickButton toggleLED = new JoystickButton(operatorController, TOGGLE_LED_BTN),
    driveTowardTarget = new JoystickButton(operatorController, DRIVE_TOWARD_TARGET_BTN),
    resetSensors = new JoystickButton(operatorController, RESET_SENSORS_BTN),
    testElevator = new JoystickButton(operatorController, TEST_ELEVATOR_BTN),
    toggleIntakeLift = new JoystickButton(operatorController, TOGGLE_INTAKE_LIFT_BTN),
    intakeCargo = new JoystickButton(operatorController, INTAKE_CARGO_BTN),
    shootCargo = new JoystickButton(operatorController, SHOOT_CARGO_BTN);



    // Driver controller button functions
    frontClimberUp.whileHeld(new MoveFrontClimberUp());
    frontClimberDown.whileHeld(new MoveFrontClimberDown());
    backClimberUp.whileHeld(new MoveBackClimberUp());
    backClimberDown.whileHeld(new MoveBackClimberDown());

    // Operator controller button functions
    toggleLED.whenPressed(new ToggleVisionLight());
    driveTowardTarget.toggleWhenPressed(new DriveTowardTarget(false));
    resetSensors.whenPressed(new ResetSensors());
    testElevator.toggleWhenPressed(new CargoMoveToCargoShip());
    toggleIntakeLift.whenPressed(new ToggleCargoIntakeLift());
    intakeCargo.whileHeld(new ControlCargoIntake());
    shootCargo.whileHeld(new ControlCargoShoot());

  }

  public double getXDrive() {
    return driverController.getRawAxis(X_DRIVE_AXIS);
  }

  public double getYDrive() {
    return -driverController.getRawAxis(Y_DRIVE_AXIS);
  }

  public double getTurnDrive() {
    return driverController.getRawAxis(TURN_DRIVE_AXIS);
  }

  public double getLeftDrive() {
    return -driverController.getRawAxis(LEFT_DRIVE_AXIS);
  }

  public double getRightDrive() {
    return -driverController.getRawAxis(RIGHT_DRIVE_AXIS);
  }

  public double getElevatorDrive() {
    return -operatorController.getRawAxis(ELEVATOR_DRIVE_AXIS);
  }

  public double getIntakeDrive() {
    return -operatorController.getRawAxis(CARGO_INTAKE_AXIS);
  }
}
