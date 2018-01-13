package org.usfirst.frc.team1403.robot;
import java.io.*;

public class Recorder {
	private static final int RMAX = 100000; //20 x (500 x 15)
	private FileReader fileReader; //import these later
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
    private PrintWriter printWriter;

	private String fileRead;
	private String fileWrite;
	private String line;
	private String[] divider;
	public Recording[] recordings;
	public int currsize;

	public Recorder(String fileWrite, String fileRead) {
		currsize = 0;
		this.fileRead = fileRead;
        this.fileWrite = fileWrite;
		try {
			fileReader = new FileReader(this.fileRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        bufferedReader =  new BufferedReader(fileReader);
        try {
			fileWriter = new FileWriter(this.fileWrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
        printWriter = new PrintWriter(fileWriter);
        recordings = new Recording[RMAX];
	}
	public void readFile() {
		try {
			while((line = bufferedReader.readLine()) != null) {
				divider = line.split(",");
				recordings[currsize].tstamp = Long.parseLong(divider[0]);
				recordings[currsize].lefte = Double.parseDouble(divider[1]);
				recordings[currsize].righte = Double.parseDouble(divider[2]);
				++currsize;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeFile(Recording recording) {
		printWriter.println(recording.tstamp + "," + "," + recording.lefte + "," + recording.righte);
	}
	public void doneWrite() {
		printWriter.close();
	}
}
