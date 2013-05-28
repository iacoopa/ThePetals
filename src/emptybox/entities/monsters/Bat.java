package emptybox.entities.monsters;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.Player;
import emptybox.entities.Shot;
import emptybox.entities.items.Item;
import emptybox.entities.items.RedPotion;

import it.marteEngine.entity.Entity;

public class Bat extends Monster {
	
	private Player player;
	public int health;

	public Bat(float x, float y, Player player, int health) throws SlickException {
		super(x, y, player);
		this.player = player;
		addType("bat");
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		setGraphic(spritesheet.getSprite(14, 12).getScaledCopy(4.0f));
		setHitBox(0, 15, 32, 17);
		this.health = health;
		
		dropList.add(new RedPotion(x, y, player));

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
		follow();
		
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
