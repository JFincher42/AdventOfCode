package ProblemEight;

public class Register {

	public String name;
	public int value;
	
	public Register() {
		this.name = "";
		this.value = 0;
	}
	
	public Register(String name) {
		this.name = name;
		this.value = 0;
	}
	
	public void inc(int value) {
		this.value += value;
	}
	
	public void dec(int value) {
		this.value -= value;
	}
}
