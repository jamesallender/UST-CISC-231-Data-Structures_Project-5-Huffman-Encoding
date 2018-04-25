import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Compressor {
	public static void main(String[] args) {
		//ask user for input file
		//ask user for output file
		//read input file
		//call toString and print it
		//fill output file with compressed data
	}
	
	private static void fileReader() {
		JFrame frame = new JFrame();
		JFileChooser chooser = new JFileChooser();
		
		frame.setAlwaysOnTop(true);
		frame.setVisible(false);
		try {
			if (chooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
				throw new Error("Input file not selected");
			}
			String inFile = chooser.getName();
		} catch (Exception e) {
			System.err.println("A mysterious error occurred.");
		}
	}
}
