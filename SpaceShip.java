package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	int hp = 4; 
	boolean alive = true;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}

	public void hp(){
		if(hp < 4)
			hp++;
			
	}



	public void hit() {
		hp--; //hp
		if(hp <= 0) 
		alive = false;
	}

	public int getHp() {
		return hp;
	}

	public boolean isAlive() {
		return alive;
	}
}

