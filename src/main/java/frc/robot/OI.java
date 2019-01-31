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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  // Joysticks
  Joystick controller = new Joystick(0);
  //Joystick leftJoy = new Joystick(0);
  //Joystick rightJoy = new Joystick(1);

  // Axis mappings
  private final int X_DRIVE_AXIS = 0;
  private final int Y_DRIVE_AXIS = 1;
  private final int TURN_DRIVE_AXIS = 2;
  private final int LEFT_DRIVE_AXIS = 1;
  private final int RIGHT_DRIVE_AXIS = 3;

  // Button mappings
  private final int TOGGLE_LED_BTN = 4;
  private final int DRIVE_TOWARD_TARGET_BTN = 1;

  public OI() {
    JoystickButton toggleLED = new JoystickButton(controller, TOGGLE_LED_BTN),
    driveTowardTarget = new JoystickButton(controller, DRIVE_TOWARD_TARGET_BTN);

    toggleLED.toggleWhenPressed(new ControlVisionLight());
    driveTowardTarget.toggleWhenPressed(new DriveTowardTarget(false));
  }

  public double getXDrive() {
    return controller.getRawAxis(X_DRIVE_AXIS);
  }

  public double getYDrive() {
    return -controller.getRawAxis(Y_DRIVE_AXIS);
  }

  public double getTurnDrive() {
    return controller.getRawAxis(TURN_DRIVE_AXIS);
  }

  public double getLeftDrive() {
    return -controller.getRawAxis(LEFT_DRIVE_AXIS);
  }

  public double getRightDrive() {
    return -controller.getRawAxis(RIGHT_DRIVE_AXIS);
  }
}
