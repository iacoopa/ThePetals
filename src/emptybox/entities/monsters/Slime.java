package emptybox.entities.monsters;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.Player;
import emptybox.entities.Shot;
import emptybox.entities.items.RedPotion;
import it.marteEngine.entity.Entity;

import emptybox.entities.items.*;

public class Slime extends Monster {

	private Player player;
	private float speed = 0.8f;
	public int health, maxHealth;
	private int movementTimer = 60;

	public Slime(float x, float y, Player player, int health)
			throws SlickException {
		super(x, y, player);
		this.player = player;
		addType("slime");
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png",
				8, 8);
		setGraphic(spritesheet.getSprite(2, 11).getScaledCopy(4.0f));
		setHitBox(0, 15, 32, 17);
		this.health = health;
		this.maxHealth = health;
		
		dropList.add(new RedPotion(x, y, player));
		
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
