/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ShootCargo extends Command {
  // Constants
  private static final double SHOOT_TIMEOUT = 1.0;
  public static final double INTAKE_DEADBAND = Math.pow(0.07, 3);

  // Timer
  private Timer timer = new Timer();

  public ShootCargo() {
    requires(Robot.cargoIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargoIntake.move((Math.abs(Robot.oi.getCargoIntakeDrive()) > INTAKE_DEADBAND));
    //Robot.cargoMechanism.run(-SHOOT_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.get() >= SHOOT_TIMEOUT;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.cargoMechanism.run(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
