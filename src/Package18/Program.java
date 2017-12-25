package Package18;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {
	public ArrayList<Instruction> program = new ArrayList<Instruction>();
	public HashMap<String, Long> registers; 
	public int ip;
	public long rcvFreq;
	public boolean recovered;

	public Program() {
		program = new ArrayList<Instruction>();
		registers = new HashMap<String, Long>();
		ip = 0;
		rcvFreq = 0;
		recovered = false;
	}
	
	public void addProgramLine(String line) {
		Instruction newInst = new Instruction(line);
		switch (newInst.instruction) {
			case "set":
			case "add":
			case "mul":
			case "mod":
			case "rcv":
				if (!registers.containsKey(newInst.register))
					registers.put(newInst.register, 0l);
				break;
		}
		this.program.add(newInst);
	}
	
	public long execute(int startingIP) {
		this.ip = startingIP;
		
		while (ip>=0 && ip<program.size() && !recovered) {
			Instruction inst = program.get(ip);
			switch (inst.instruction) {
			case "snd":
				rcvFreq = eval(inst.register);
				ip+=1;  break;
			case "set":
				registers.put(inst.register, eval(inst.value));
				ip+=1;  break;
			case "add":
				registers.put(inst.register, eval(inst.register)+eval(inst.value));
				ip+=1;  break;
			case "mul":
				registers.put(inst.register, eval(inst.register)*eval(inst.value));
				ip+=1;  break;
			case "mod":
				registers.put(inst.register, eval(inst.register)%eval(inst.value));
				ip+=1;  break;
			case "rcv":
				if (eval(inst.register)!=0) recovered=true;
				else ip+=1;  break;
			case "jgz":
				if (eval(inst.register)>0) ip+=eval(inst.value);
				else ip+=1;
				break;
			}
		}
		return rcvFreq;
	}
	
	public long eval(String operand) {
		if (registers.containsKey(operand)) return registers.get(operand);
		return Integer.parseInt(operand);
	}

}
