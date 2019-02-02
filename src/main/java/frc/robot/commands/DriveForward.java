package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {
	// Constants
	private final double MIN_SPEED = 0.4;
	private final double MAX_SPEED = 0.75;
	private final double kP = 0.0003; //0.0002 for high gear
	
	// Control variable
	private double distanceInTicks;

    public DriveForward(double distanceInInches) {
        requires(Robot.driveTrain);
        distanceInTicks = Encoder.driveInchesToTicks(distanceInInches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = (distanceInTicks - Robot.driveTrain.getLeftEncPosition()) * kP;
    	
    	if (error >= MIN_SPEED && error <= MAX_SPEED)
			Robot.driveTrain.gyroAssistedDrive(error);
		else if (error < MIN_SPEED)
			Robot.driveTrain.gyroAssistedDrive(MIN_SPEED);
		else
			Robot.driveTrain.gyroAssistedDrive(MAX_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.getLeftEncPosition() >= distanceInTicks ||
          Robot.driveTrain.getRightEncPosition() >= distanceInTicks;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.forcedMecanumDrive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}