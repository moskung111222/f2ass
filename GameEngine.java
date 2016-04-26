package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;
import javax.swing.JOptionPane;

public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Item> item = new ArrayList<Item>();//add item
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;	
	
	private Timer timer;
	//private long hp = 4;
	private long score = 0;
	private double difficulty = 0.15; //add difficulty 
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process(); 
				checkSpaceShip();
				processItem(); //itemfall
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateItem(){
		Item it = new Item((int)(Math.random()*390), 30);
		gp.sprites.add(it);
		item.add(it);
	}	



	private void processItem(){
		if(Math.random() < difficulty/2){
			generateItem();
		}
		
		Iterator<Item> it_iter = item.iterator();
		while(it_iter.hasNext()){
			Item it = it_iter.next();
			it.proceed();
			
			if(!it.isAlive()){
				gp.sprites.remove(it);
				it_iter.remove();			
			}
		}
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double tr;
		for(Item it : item){
			tr = it.getRectangle();
			if(tr.intersects(vr)){	
				//add hp
				v.hp();
				return;
			}
		}
	}


	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100; //add score
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				v.hit();
				e.hit();
				return;
			}
		}
	}
	

	public void checkSpaceShip() {
		if(!v.isAlive()) die();
	}


	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.15;
			break;
		}
	}

	public long getScore(){ 
		return score;
	}
	
	public long getSpaceShipHp() {
		return v.getHp();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
