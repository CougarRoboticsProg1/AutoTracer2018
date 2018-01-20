package org.usfirst.frc.team1403.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team1403.robot.commands.EchoOn;
import org.usfirst.frc.team1403.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1403.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	public EchoOn echotrigger = new EchoOn();
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI m_oi;
	public static DriveTrain drivetrain;
	public static boolean record;
	public int echoiter;
	public static boolean flag;
	public Recording currRecord;
	private static Recorder recorder;
	private static final String readPath = "/home/lvuser/demor.txt"; //readPath.csv
	private static final String writePath = "/home/lvuser/demof.txt"; //writePath.csv

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		recorder = new Recorder(writePath, readPath);
		currRecord = new Recording(0, 0, 0);
		record = false;
		flag = false;
		echoiter = 0;
		drivetrain = new DriveTrain();
		m_oi = new OI();
		DriverStation.getInstance().getGameSpecificMessage();
		
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		recorder.readFile(); //read file
		echoiter = 1;
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() { //20 ms
		long startTime = System.currentTimeMillis();
		while (isAutonomous())
		{
			if (echoiter < recorder.currsize)
			{
				double delta_t = recorder.recordings[echoiter].tstamp - recorder.recordings[echoiter-1].tstamp;
				if(System.currentTimeMillis() - startTime >= delta_t) { //timestamp match
				//	DriveTrain.setSpeed(drivetrain.m1, recorder.recordings[echoiter].righte);
					DriveTrain.setSpeed(drivetrain.m6, recorder.recordings[echoiter].righte);
					DriveTrain.setSpeed(drivetrain.m2, recorder.recordings[echoiter].lefte);
				//	DriveTrain.setSpeed(drivetrain.m4, recorder.recordings[echoiter].lefte);					
					startTime = System.currentTimeMillis();
				} else {
					Timer.delay(0.001); //delay iter until ready
					continue;
				}
			}
			if(echoiter < 50000) {
				++echoiter;
			}
			if(recorder.recordings[echoiter+1].tstamp == 0) //reset vals
			{
				DriveTrain.setSpeed(drivetrain.m6, 0);
			//	DriveTrain.setSpeed(drivetrain.m2, 0);
				DriveTrain.setSpeed(drivetrain.m2, 0);
			//	DriveTrain.setSpeed(drivetrain.m4, 0);		
			}
		}	
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if(flag) {
			recorder.doneWrite();
			record = false;
			flag = false;
		}
		else if(record) {
		 	currRecord.tstamp = (int) (Timer.getFPGATimestamp() * 1000); //clock reading
			//System.out.println(Timer.getFPGATimestamp());
			currRecord.lefte = drivetrain.m6.getMotorOutputPercent();
			currRecord.righte = drivetrain.m2.getMotorOutputPercent();
			recorder.writeFile(currRecord);
		} 
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {}
}