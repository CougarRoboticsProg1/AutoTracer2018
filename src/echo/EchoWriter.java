package echo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//basic wrapper
public class EchoWriter {
	private FileWriter fileWriter; //import these later
	private PrintWriter printWriter;	
	public EchoWriter() {
		
	}
	public void initWriter(File file) throws IOException {
		fileWriter = new FileWriter(file, false);
		printWriter = new PrintWriter(this.fileWriter);
	}
	public void writeToFile(String command) {
		printWriter.println(command);
	}
	
	public void destroy() throws IOException {
		fileWriter.close();
		printWriter.close();
	}
}