
package emptybox.entities;

import it.marteEngine.entity.Entity;

import java.awt.geom.Rectangle2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Stairs extends Entity {
	
	public int width, height;
	public Rectangle2D rect; 
	public String type;
	public boolean delete;
	private StateBasedGame game;
	
	public Stairs (float x, float y, StateBasedGame game) throws SlickException {
		super(x, y);
		this.width = 32;
		this.height = 32;
		SpriteSheet sheet = new SpriteSheet("res/images/hifi_map.png", 32, 32);
		setGraphic(sheet.getSprite(8, 0));
		setHitBox(0, 0, width, height);
		collidable = true;
		addType(SOLID);
		this.game = game;
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		if (delete == true) {
			destroy();
		}
	}
	
	public void collisionResponse(Entity other) {
		try {
			@SuppressWarnings("unused")
			Player player = (Player) other;
			
			game.enterState(3);
		} catch (ClassCastException e) {
			
		}
	}
}
