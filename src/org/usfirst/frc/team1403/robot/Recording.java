package org.usfirst.frc.team1403.robot;

public class Recording {
	public int tstamp; //delta t
	public double gyror; //gyro
	public double lefte; //left encoder
	public double righte; //right encoder
	public Recording(int tstamp, double gyror, double lefte, double righte) {
		this.tstamp = tstamp;
		this.gyror = gyror; //gyro
		this.lefte = lefte;
		this.righte = righte;
	}
}
