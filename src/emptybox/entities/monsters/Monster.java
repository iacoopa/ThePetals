package emptybox.entities.monsters;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import emptybox.Point;
import emptybox.entities.EnemyShot;
import emptybox.entities.Player;
import emptybox.entities.items.Item;

import it.marteEngine.entity.Entity;

public class Monster extends Entity {

	private float speed = 0.8f;
	private Player player;
	protected ArrayList<Item> dropList = new ArrayList<Item>();
	private Vector2f trans = new Vector2f(0, 0);
	protected double rand;

	public Monster(float x, float y, Player player) {
		super(x, y);
		addType("enemy");
		this.player = player;
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
	}
	
	protected void follow() {
		trans.x = player.x - x;
		trans.y = player.y - y;
		
		if (this.isType("bat")) {
			speed = 2.0f;
		}
		else {
			speed = 1.8f;
		}
		
		if (collide("enemy", x, y) != null) {
			x -= speed * Math.cos(Math.toRadians(trans.getTheta())); 
		}
		if (collide("enemy", x, y) != null) {
			y -= speed * Math.sin(Math.toRadians(trans.getTheta())); 
		}
		if (collide(SOLID, x += speed * Math.cos(Math.toRadians(trans.getTheta())), y) != null) {
			x -= speed * Math.cos(Math.toRadians(trans.getTheta())); 
		}
		if (collide(SOLID, x, y += speed * Math.sin(Math.toRadians(trans.getTheta()))) != null) {
			y -= speed * Math.sin(Math.toRadians(trans.getTheta()));
		}
	}
	
	protected void randomAngle(Point trans, double rand) {
		speed = 1.8f;
		
		if (collide(SOLID, x += speed * trans.x, y) != null) {
			trans.x = (float) (-trans.x);
			trans.y += (float) (Math.random() * 0.7) - .35;
		}
		if (collide(SOLID, x, y += speed * trans.y) != null) { 
			trans.y = (float) (-trans.y);
			trans.x += (float) (Math.random() * 0.7) - .35;
		}
	}
	
	protected void randomFollow(double rand) {
		
		speed = 1.5f;
		
		if (collide(SOLID, x += speed * Math.cos(Math.toRadians(rand)), y) != null) {
			x -= speed * Math.cos(Math.toRadians(rand)); 
		}
		if (collide(SOLID, x, y += speed * Math.sin(Math.toRadians(rand))) != null) {
			y -= speed * Math.sin(Math.toRadians(rand));
		}
		if (collide("enemy", x, y) != null) {
			x -= speed * Math.cos(Math.toRadians(rand)); 
		}
		if (collide("enemy", x, y) != null) {
			y -= speed * Math.sin(Math.toRadians(rand));
		}
          
	}
	
	protected void shoot() throws SlickException {
		player.grid.getSelectedRoom().entities.add(new EnemyShot(x + 32, y + 16,
				new Vector2f(x, y), new Vector2f(player.x + 5, player.y), 350,
				player.grid));
	}

}
