package package23;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

public class Program {
	public ArrayList<Instruction> program;
	public HashMap<String, Long> registers; 
	public int ip;
	//	public long rcvFreq;
	//	public boolean recovered;
	public LinkedList<Long> inQueue;
	public boolean ended;
	public boolean blocking;
	public Program coop;					// The program with which we cooperate
	public int mulCount;

	public Program() {
		program = new ArrayList<Instruction>();
		inQueue = new LinkedList<Long>();
		registers = new HashMap<String, Long>();
		ip = 0;
		//		rcvFreq = 0;
		//		recovered = false;
		ended = false;
		blocking = false;
		mulCount = 0;
		this.registers.put("a", 0l);
		this.registers.put("b", 0l);
		this.registers.put("c", 0l);
		this.registers.put("d", 0l);
		this.registers.put("e", 0l);
		this.registers.put("f", 0l);
		this.registers.put("g", 0l);
		this.registers.put("h", 0l);
	}

	public void cooperate(Program coop) {
		this.coop = coop;
	}

	public void addProgramLine(String line) {
		Instruction newInst = new Instruction(line);
//		switch (newInst.instruction) {
//		case "set":
//		case "add":
//		case "sub":
//		case "mul":
//		case "mod":
//		case "jnz":
//		case "rcv":
//			if (!registers.containsKey(newInst.register))
//				registers.put(newInst.register, 0l);
//			break;
//		}
		this.program.add(newInst);
	}

	public void send(long value) {
		inQueue.add(value);
	}

	public void execute(int startingIP) {
		this.ip = startingIP;

		while (ip>=0 && ip<program.size() && !blocking) {
			Instruction inst = program.get(ip);
			switch (inst.instruction) {
			case "snd":
				coop.send(eval(inst.register));
				ip+=1;  break;
			case "rcv":
				if (null == inQueue.peek()) {
					blocking = true;
				} else {
					blocking = false;
					registers.put(inst.register, inQueue.remove());
					ip+=1;
				}
				break;

			case "set":
				registers.put(inst.register, eval(inst.value));
				ip+=1;  break;
			case "add":
				registers.put(inst.register, eval(inst.register)+eval(inst.value));
				ip+=1;  break;
			case "sub":
				registers.put(inst.register, eval(inst.register)-eval(inst.value));
				ip+=1;  break;
			case "mul":
				registers.put(inst.register, eval(inst.register)*eval(inst.value));
				mulCount+=1;
				ip+=1;  break;
			case "mod":
				registers.put(inst.register, eval(inst.register)%eval(inst.value));
				ip+=1;  break;
			case "jgz":
				if (eval(inst.register)>0) ip+=eval(inst.value);
				else ip+=1;
				break;
			case "jnz":
				if (eval(inst.register)!=0) ip+=eval(inst.value);
				else ip+=1;
				break;
			}
		}
		if (ip >= program.size()) ended = true;
		//return rcvFreq;
	}

	public int step(int startingIP) {
		this.ip = startingIP;

		Instruction inst = program.get(ip);
		switch (inst.instruction) {
		case "snd":
			coop.send(eval(inst.register));
			//sendCount+=1;
			this.ip+=1;  break;
		case "rcv":
			if (null == inQueue.peek()) {
				blocking = true;
			} else {
				blocking = false;
				registers.put(inst.register, inQueue.remove());
				this.ip+=1;
			}
			break;

		case "set":
			registers.put(inst.register, eval(inst.value));
			this.ip+=1;  break;
		case "add":
			registers.put(inst.register, eval(inst.register)+eval(inst.value));
			this.ip+=1;  break;
		case "sub":
			registers.put(inst.register, eval(inst.register)-eval(inst.value));
			ip+=1;  break;

		case "mul":
			registers.put(inst.register, eval(inst.register)*eval(inst.value));
			mulCount+=1;
			this.ip+=1;  break;
		case "mod":
			registers.put(inst.register, eval(inst.register)%eval(inst.value));
			this.ip+=1;  break;
		case "jgz":
			if (eval(inst.register)>0) this.ip+=eval(inst.value);
			else this.ip+=1;
			break;
		case "jnz":
			if (eval(inst.register)!=0) ip+=eval(inst.value);
			else ip+=1;
			break;

		}

		if (this.ip >= program.size() || this.ip < 0) ended = true;
		return this.ip;
	}

	long eval(String operand) {
		if (registers.containsKey(operand)) return registers.get(operand);
		return Integer.parseInt(operand);
	}

}
