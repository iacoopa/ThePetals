package emptybox.entities.monsters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.EnemyShot;
import emptybox.entities.Player;
import emptybox.entities.Shot;

import it.marteEngine.entity.Entity;

public class Wyvern extends Entity {

	private Player player;
	private float speed = 0.03f;
	public int health;
	private Vector2f trans = new Vector2f(0, 0);
	private Circle vision;
	private double rand;
	private int movementTimer = 90;
	private int shootTimer = 60;

	public Wyvern(float x, float y, Player player, int health)
			throws SlickException {
		super(x, y);
		this.player = player;
		this.health = health;

		addType("enemy");
		setHitBox(0, 0, 64, 32);

		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png",
				16, 8);
		setGraphic(spritesheet.getSprite(0, 15).getScaledCopy(4.0f));

		this.vision = new Circle(x + 32, y + 16, 300);
		rand = Math.random() * 361;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		
		g.draw(vision);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		vision.setCenterX(x + 32);
		vision.setCenterY(y + 16);
		
		if (player.intersect(vision) != null) {
			follow();
			if (shootTimer >= 60) {
				shoot();
				shootTimer = 0;
			} else {
				shootTimer ++;
			}
		} else {
			if (movementTimer >= 30) {
				rand = Math.random() * 361;
				movementTimer = 0;
				randomFollow(rand);
			} else {
				randomFollow(rand);
				movementTimer++;
			}
			
		}
		
		if (health == 0) {
			player.level ++;
			destroy();
			player.grid.getSelectedRoom().entities.remove(this);
			player.grid.getSelectedRoom().enemies.remove(this);
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

	private void shoot() throws SlickException {
		player.grid.getSelectedRoom().entities.add(new EnemyShot(x + 32, y + 16,
				new Vector2f(x, y), new Vector2f(player.x - 22, player.y + 16), 350,
				player.grid));
	}
}
