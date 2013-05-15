package emptybox.entities.monsters;

import java.math.BigInteger;

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
	private float speed = 0.03f;
	public int health, gcdTimer;
	private Vector2f trans = new Vector2f(0,0);
	private int gcd;

	public Bat(float x, float y, Player player, int health) throws SlickException {
		super(x, y);
		this.player = player;
		addType("enemy");
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		setGraphic(spritesheet.getSprite(14, 12).getScaledCopy(4.0f));
		setHitBox(0, 0, 32, 32);
		this.health = health;
		gcdTimer = 30;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
		gcdTimer ++;
		if (gcdTimer >= 15) {
			if (Math.abs(getSlope().x) > Math.abs(getSlope().y)) {
				gcd = gcdThing((int) getSlope().x, (int) getSlope().y);
			} else {
				gcd = gcdThing((int) getSlope().y, (int) getSlope().x);
			}
			gcdTimer = 0;
		}
		
		System.out.println(gcd);
		
		trans.x = (getSlope().x / gcd);
		trans.y = (getSlope().y / gcd);
		
		if (collide("enemy", x += trans.x * speed, y) != null) {
			x -= trans.x * speed;
		}
		
		if (collide("enemy", x, y += trans.y * speed) != null) {
			y -= trans.y * speed;
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
	
	private static int gcdThing(int a, int b) {
	    BigInteger b1 = new BigInteger(""+ Math.abs(a)); // there's a better way to do this. I forget.
	    BigInteger b2 = new BigInteger(""+ Math.abs(b));
	    BigInteger gcd = b1.gcd(b2);
	    return gcd.intValue();
	}

}
