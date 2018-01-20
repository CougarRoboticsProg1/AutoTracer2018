package echo;

import java.util.HashMap;
import java.util.Map;

public class Recording {
	private Map<String, Double> recording;
	public Recording() {
		this.recording = new HashMap<String, Double>();
	}
	public void addKey(String name, double value) {
		this.recording.put(name, value);
	}
	
	public Map<String, Double> returnMap() {
		return this.recording;	
	}
	
	public String returnMapReading() {
		String ret = new String();
		for (String key : this.recording.keySet()) {
			ret += recording.get(key) + ",";
		}
		if(!ret.isEmpty()) return ret.substring(0, ret.length()-1);
		else return null;
	}
	
	/*TODO: Add Key Mapping*/
	//DriveTrainR    -->     DriveTrain Right Motor Reading
	//DriveTrainL    -->     DriveTrain Left Motor Reading
	//GyroY          -->     Gyro Yaw Reading
	//GyroP          -->     Gyro Pitch Reading
	//GyroR          -->     Gyro Row Reading
}