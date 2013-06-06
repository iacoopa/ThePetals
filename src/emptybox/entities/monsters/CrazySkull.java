package emptybox.entities.monsters;

import it.marteEngine.entity.Entity;
import emptybox.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.Player;
import emptybox.entities.Shot;
import emptybox.entities.items.BlueFlame;
import emptybox.entities.items.Item;
import emptybox.entities.items.equipment.IronKnife;
import emptybox.entities.items.equipment.MagicWand;

public class CrazySkull extends Monster {

	Player player;
	private Image sprite;
	private int health = 7;
	private double rand = Math.random() * 360;
	Point point = new Point((float) Math.cos(Math.toRadians(rand)), (float) Math.sin(Math.toRadians(rand)));
	
	public CrazySkull(float x, float y, Player player) throws SlickException {
		super(x, y, player);
		
		this.player = player;
		addType("redSkull");
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		sprite = sheet.getSprite(5, 6).getScaledCopy(4.0f);
		setGraphic(sprite);
		setAngle(0);
		setHitBox(0, 0, 32, 32);
		
		dropList.add(new BlueFlame(x,y, player));
		dropList.add(new MagicWand(x, y, player));
		dropList.add(new IronKnife(x, y, player));
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		
		randomAngle(point, rand);
		
		if (health <= 0) {
			
			double rand = (Math.random() * dropList.size() * 2);
			System.out.println((int) rand % 2 + " " + (int) rand/2);
			
			if ((int)rand % 2 == 0) {
				Item dropItem = dropList.get((int) rand / 2);
				
				dropItem.setPosition(new Vector2f(x, y));
				
				player.grid.getSelectedRoom().entities.add(dropItem);
			} 
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
			health -= shot.damage;
			player.grid.getSelectedRoom().entities.remove(other);
		} catch(ClassCastException e) {
			return;
		}
	}
}
