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
        File filer = new File(this.fileRead);
        File filew = new File(this.fileWrite);
        if(!filer.exists()) {
        	try {
				filer.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(!filew.exists()) {
        	try {
				filew.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		try {
			fileReader = new FileReader(filer);
			bufferedReader =  new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
      
        try {
			fileWriter = new FileWriter(filew);
			printWriter = new PrintWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        recordings = new Recording[RMAX];
        for(int i = 0; i < RMAX; i++) {
        	recordings[i] = new Recording(0, 0.0, 0.0);
        }
	}
	public void readFile() {
		try {
			while((line = bufferedReader.readLine()) != null) {
				divider = line.split(",");
				if(divider.length < 3) break;
				System.out.println(divider[0] + " " + divider[1] + " " + divider[2]);
				recordings[currsize].tstamp = Integer.parseInt(divider[0]);
				recordings[currsize].lefte = Double.parseDouble(divider[2]);
				recordings[currsize].righte = Double.parseDouble(divider[1]);
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
		try {
			printWriter.println(recording.tstamp  + "," + recording.lefte + "," + recording.righte);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	public void doneWrite() {
		try {
			// printWriter.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
