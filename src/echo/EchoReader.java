package echo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//basic wrapper
public class EchoReader {
	private FileReader fileReader; //import these later
	private BufferedReader bufferedReader;
	public EchoReader() {
		
	}
	public void initReader(File file) throws FileNotFoundException {
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(this.fileReader);
	}
	public String getNextLine(String str) throws IOException {
		return bufferedReader.readLine();
	}
	public void destroy() throws IOException {
		fileReader.close();
		bufferedReader.close();
	}
}