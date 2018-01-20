package echo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Recorder {
	private final int RMAX; //20 ms delay * 50 ms in 1 second * 15 seconds
	public int[] sequencedReadings;
	public static Map<String, Integer> KeyMappings;
	public static Map<String, File> FileSelect;
	public File currentFile;
	
	public Recorder(int arrsize) {
		this.RMAX = arrsize;
		Recorder.KeyMappings= new HashMap<String, Integer>();
		this.sequencedReadings = new int[RMAX];
	}
	public void addKeyMapping(String key, int keymap) {
		Recorder.KeyMappings.put(key, keymap);
	}
	//Obsolete
	public int returnKeyMapping(String keyReading) {
		return Recorder.KeyMappings.get(keyReading);
	}
	
	//file selection process
	public void addFileSelect(String keyFile, File file) {
		Recorder.FileSelect.put(keyFile, file);
	}
	
	public File returnFile(String keyFile) {
		this.currentFile = Recorder.FileSelect.get(keyFile);
		return Recorder.FileSelect.get(keyFile);
	}
}