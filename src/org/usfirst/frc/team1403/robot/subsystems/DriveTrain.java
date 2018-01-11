package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

    AnalogGyro gyro = new AnalogGyro(0); //gyro

    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
}

