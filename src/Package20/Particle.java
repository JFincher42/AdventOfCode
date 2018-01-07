package Package20;

//import java.math.*;

public class Particle {
	public int posX, posY, posZ;
	public int velX, velY, velZ;
	public int accX, accY, accZ;
	
	public int distance;
	public boolean collided;
	
	public void calcDistance() {
		//distance = Math.abs(posX) + Math.abs(posY) + Math.abs(posZ);
		distance = Math.abs(posX) + Math.abs(posY) + Math.abs(posZ);
	}
	
	public void move() {
		velX += accX;  posX += velX;  
		velY += accY;  posY += velY;  
		velZ += accZ;  posZ += velZ;  
	}
	
	public Particle() {
		posX = 0;  posY = 0;  posZ = 0;
		velX = 0;  velY = 0;  velZ = 0;
		accX = 0;  accY = 0;  accZ = 0;
		collided = false;
	}
	
	public Particle(String strPos, String strVel, String strAcc) {
		int start, comma;

		// Position
		start=strPos.indexOf('<');
		strPos = strPos.substring(start+1);  comma = strPos.indexOf(',');
		posX = Integer.parseInt(strPos.substring(0, comma));

		strPos = strPos.substring(comma+1);  comma = strPos.indexOf(',');
		posY = Integer.parseInt(strPos.substring(0, comma));

		strPos = strPos.substring(comma+1);  comma = strPos.indexOf('>');
		posZ = Integer.parseInt(strPos.substring(0, comma));

		// Velocity
		start=strVel.indexOf('<');
		strVel = strVel.substring(start+1);  comma = strVel.indexOf(',');
		velX = Integer.parseInt(strVel.substring(0, comma));

		strVel = strVel.substring(comma+1);  comma = strVel.indexOf(',');
		velY = Integer.parseInt(strVel.substring(0, comma));

		strVel = strVel.substring(comma+1);  comma = strVel.indexOf('>');
		velZ = Integer.parseInt(strVel.substring(0, comma));

		// Acceleration
		start=strAcc.indexOf('<');
		strAcc = strAcc.substring(start+1);  comma = strAcc.indexOf(',');
		accX = Integer.parseInt(strAcc.substring(0, comma));

		strAcc = strAcc.substring(comma+1);  comma = strAcc.indexOf(',');
		accY = Integer.parseInt(strAcc.substring(0, comma));

		strAcc = strAcc.substring(comma+1);  comma = strAcc.indexOf('>');
		accZ = Integer.parseInt(strAcc.substring(0, comma));
		
		// Collision
		collided = false;
	}
	
	public boolean inPlay() { return !collided; }

}
