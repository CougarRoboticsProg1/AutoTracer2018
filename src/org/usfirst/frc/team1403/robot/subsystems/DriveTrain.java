package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public AnalogGyro gyro;
	public TalonSRX m1; 
	public TalonSRX m2;
	public TalonSRX m3; 
	public TalonSRX m4; 
	
	public DriveTrain()
	{
		m1 = new TalonSRX(0);
		m2 = new TalonSRX(1);
		m3 = new TalonSRX(2);
		m4 = new TalonSRX(3);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand()); 
    }
    public void drive()
    {
    	m1.set(ControlMode.PercentOutput, Robot.oi.stick.getRawAxis(1));
    	m2.set(ControlMode.PercentOutput, Robot.oi.stick.getRawAxis(1));
    	m3.set(ControlMode.PercentOutput, Robot.oi.stick.getRawAxis(5));
    	m4.set(ControlMode.PercentOutput, Robot.oi.stick.getRawAxis(5));
    }
    public static void setSpeed(TalonSRX talon, double speed) {
    	talon.set(ControlMode.PercentOutput, speed);
    }
}