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
	private float speed = 1.3f;
	public int health, maxHealth;
	private Vector2f trans = new Vector2f(0, 0);
	private int rand;
	private int movementTimer = 50;

	public Slime(float x, float y, Player player, int health)
			throws SlickException {
		super(x, y);
		this.player = player;
		addType("enemy");
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png",
				8, 8);
		setGraphic(spritesheet.getSprite(2, 11).getScaledCopy(4.0f));
		setHitBox(0, 0, 32, 32);
		this.health = health;
		this.maxHealth = health;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		
		if (health == maxHealth) {
			rand = (int) (Math.random() * 9);
			if (movementTimer >= 60) {
				trans.x = 0;
				trans.y = 0;
				switch (rand) {
				case 0:
					trans.x = 1;
					break;
				case 1:
					trans.y = 1;
					break;
				case 2:
					trans.x = -1;
					break;
				case 3:
					trans.y = -1;
					break;
				case 4:
					trans.x = 1;
					trans.y = 1;
					break;
				case 5:
					trans.x = -1;
					trans.y = -1;
					break;
				case 7:
					trans.x = 1;
					trans.y = -1;
					break;
				case 8:
					trans.x = -1;
					trans.y = 1;
				}
				movementTimer = 0;
			}
	
			movementTimer++;
		
		} else {
			if (getSlope().x > 0) {
				trans.x = 1;
			}
			if (getSlope().x < 0) {
				trans.x = -1;
			}
			if (getSlope().y > 0) {
				trans.y = 1;
			}
			if (getSlope().y < 0) {
				trans.y = -1;
			}
			if (getSlope().x == 0) {
				trans.x = 0;
			}
			if (getSlope().y == 0) {
				trans.y = 0;
			}
		}

		if (collide(SOLID, x += trans.x * speed, y) != null) {
			x -= trans.x * speed;
		}
		if (collide(SOLID, x, y += trans.y * speed) != null) {
			y -= trans.y * speed;
		}
		
		if (collide("enemy", x, y) != null) {
			x -= trans.x * speed;
		}
		
		if (collide("enemy", x, y) != null) {
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
}
