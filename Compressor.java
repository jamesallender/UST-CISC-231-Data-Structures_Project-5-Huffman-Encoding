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
	//	Scanner scanner = null;
		
		frame.setAlwaysOnTop(true);
		frame.setVisible(false);
		try {
			if (chooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
				throw new Error("Input file not selected");
			}
			String inFile = chooser.getName();
	//		scanner = new Scanner(file);
	//		while(file.exists() && scanner.hasNext()) {
	//			int arrive = scanner.nextInt();
	//			int duration = scanner.nextInt();
	//			boolean isWalkIn = scanner.nextBoolean();
	//			if (forceWalkIns) {
//					bank.newCustomer(arrive, duration, true);
//				} else {
//					bank.newCustomer(arrive, duration, isWalkIn);
//				}
//			}
//		} catch (FileNotFoundException e) {
//			System.err.println("Data file not found.");
		} catch (Exception e) {
			System.err.println("A mysterious error occurred.");
//			e.printStackTrace(System.err);
		}
//		scanner.close();
	}
}
