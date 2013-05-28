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
import emptybox.entities.items.Item;
import emptybox.entities.items.RedPotion;

import it.marteEngine.entity.Entity;

public class Wyvern extends Monster {

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
		super(x, y, player);
		this.player = player;
		this.health = health;

		addType("wyvern");
		setHitBox(0, 15, 64, 17);

		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png",
				16, 8);
		setGraphic(spritesheet.getSprite(0, 15).getScaledCopy(4.0f));

		this.vision = new Circle(x + 32, y + 16, 300);
		rand = Math.random() * 361;
		
		dropList.add(new RedPotion(x, y, player));

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		
		if (player.x <= x) {
			setAngle(360);
		} if (player.x >= x) {
			setAngle(0);
		}

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
			double rand = Math.random() * dropList.size();
			
			Item dropItem = dropList.get((int) rand);
			
			dropItem.setPosition(new Vector2f(x, y));
			
			player.grid.getSelectedRoom().entities.add(dropItem);
			
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
}
