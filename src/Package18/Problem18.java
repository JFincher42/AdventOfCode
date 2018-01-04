package Package18;

import java.io.File;
import java.util.Scanner;

public class Problem18 {
	
	public static void main(String[] args) {
		Scanner inFile;
		Program Program0 = new Program();
		Program Program1 = new Program();
		try {
			inFile = new Scanner(new File("input/problem18.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			Program0.addProgramLine(line);
			Program1.addProgramLine(line);
		}
		inFile.close();
		
		Program0.registers.put("p",0l);
		Program1.registers.put("p",1l);
		Program0.cooperate(Program1);
		Program1.cooperate(Program0);

		//boolean execute = true;
		int p0IP = 0;
		int p1IP = 0;
		while (!((Program0.ended && Program1.ended) || (Program0.blocking && Program1.blocking))) {
			if (!Program0.ended) p0IP = Program0.step(p0IP);
			if (!Program1.ended) p1IP = Program1.step(p1IP);
			
			//execute = !((Program0.ended && Program1.ended) || (Program0.blocking && Program1.blocking));
			
		}
		System.out.println("Program 1 sent: " + Program1.sendCount);
	}

}
