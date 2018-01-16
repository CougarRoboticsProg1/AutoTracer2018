package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.commands.JoyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public AnalogGyro gyro;
	public TalonSRX m6, m2;
//	public TalonSRX m3; 
//	public TalonSRX m4; 
	
	public DriveTrain()
	{
		m6 = new TalonSRX(6);
	//	m2 = new TalonSRX(1);
		m2 = new TalonSRX(2);
	//	m4 = new TalonSRX(3);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoyDrive()); 
    }
    public void drive()
    {
    	m6.set(ControlMode.PercentOutput, Robot.m_oi.stick.getRawAxis(1));
  //  	m2.set(ControlMode.PercentOutput, Robot.m_oi.stick.getRawAxis(1));
    	m2.set(ControlMode.PercentOutput, -Robot.m_oi.stick.getRawAxis(5));
  //  	m4.set(ControlMode.PercentOutput, Robot.m_oi.stick.getRawAxis(5));
    }
    public static void setSpeed(TalonSRX talon, double speed) {
    	talon.set(ControlMode.PercentOutput, speed);
    }
}