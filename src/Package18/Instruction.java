package Package18;

public class Instruction {
	public String instruction;
	public String register;
	public String value;
	
	public Instruction() {
		this.instruction = null;
		this.register = null;
		this.value = null;
	}
	
	public Instruction(String line) {
		String[] lineInst = line.split(" ");
		this.instruction = lineInst[0];
		this.register = lineInst[1];
		if (lineInst.length > 2) this.value = lineInst[2];
	}

}
