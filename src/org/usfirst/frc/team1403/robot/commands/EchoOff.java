package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1403.robot.Robot;

/**
 *
 */
public class EchoOff extends Command {
	
	public EchoOff() {
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.record = (Robot.record) ? false : true;
		Robot.flag = true;
		System.out.println(Robot.record);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		return;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
