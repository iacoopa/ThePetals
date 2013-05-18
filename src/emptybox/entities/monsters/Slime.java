package emptybox.entities.monsters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.Player;
import emptybox.entities.Shot;
import it.marteEngine.entity.Entity;

public class Slime extends Entity {

	private Player player;
	private float speed = 0.8f;
	public int health, maxHealth;
	private Vector2f trans = new Vector2f(0, 0);
	private double rand;
	private int movementTimer = 60;

	public Slime(float x, float y, Player player, int health)
			throws SlickException {
		super(x, y);
		this.player = player;
		addType("enemy");
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png",
				8, 8);
		setGraphic(spritesheet.getSprite(2, 11).getScaledCopy(4.0f));
		setHitBox(0, 10, 32, 22);
		this.health = health;
		this.maxHealth = health;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		movementTimer++;
		if (health == maxHealth) {
			if (movementTimer >= 60) {
				rand = Math.random() * 361;
				movementTimer = 0;
			}
			randomFollow(rand);
		} else if (health != maxHealth){
			follow();
		}
		
		if (health == 0) {
			player.level ++;
			destroy();
			player.grid.getSelectedRoom().entities.remove(this);
			player.grid.getSelectedRoom().enemies.remove(this);
		}
	}
	
	@Override 
	public void collisionResponse(Entity other) {
		try {
			Shot shot = (Shot) other;
			health -= 1;
			player.grid.getSelectedRoom().entities.remove(other);
		} catch(ClassCastException e) {
			return;
		}
	}
	
	public Vector2f getSlope() {
		return new Vector2f((player.x - x), (player.y - y));
	}
	
	private void follow() {
		trans.x = player.x - x;
		trans.y = player.y - y;
		
		speed = 1.8f;
		
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
	
	private void randomFollow(double rand) {
		
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
}
