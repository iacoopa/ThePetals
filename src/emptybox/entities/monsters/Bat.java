package emptybox.entities.monsters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.Player;
import emptybox.entities.Shot;

import it.marteEngine.entity.Entity;

public class Bat extends Entity {
	
	private Player player;
	private float speed = 0.02f;
	public int health;

	public Bat(float x, float y, Player player, int health) throws SlickException {
		super(x, y);
		this.player = player;
		addType("enemy");
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		setGraphic(spritesheet.getSprite(14, 12).getScaledCopy(4.0f));
		setHitBox(0, 0, 32, 32);
		this.health = health;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
	
	//	System.out.println(health);
		
		if (collide("enemy", x += getSlope().x * speed, y) != null) {
			x -= getSlope().x * speed;
		}
		
		if (collide("enemy", x, y += getSlope().y * speed) != null) {
			y -= getSlope().y * speed;
		}
		
		if (health == 0) {
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
	
	public double getDistance() {
		return Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y, 2)) * 0.01;
	}

}
