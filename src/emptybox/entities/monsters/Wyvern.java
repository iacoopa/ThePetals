package emptybox.entities.monsters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.entities.Player;

import it.marteEngine.entity.Entity;

public class Wyvern extends Entity {

	private Player player;
	private float speed = 0.03f;
	public int health;
	private Vector2f trans = new Vector2f(0, 0);
	
	
	public Wyvern(float x, float y, Player player, int health) throws SlickException {
		super(x, y);
		this.player = player;
		this.health = health;
		
		addType("enemy");
		setHitBox(0, 0, 64, 32);
		
		SpriteSheet spritesheet = new SpriteSheet("res/images/lofi_char.png", 16, 8);
		setGraphic(spritesheet.getSprite(0, 15).getScaledCopy(4.0f));
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
	}
}
