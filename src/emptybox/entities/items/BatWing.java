package emptybox.entities.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;

public class BatWing extends Item {
	
	public BatWing(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj_packA.png", 8, 8);
	
		sprite = sheet.getSprite(0, 2).getScaledCopy(4.0f);
		
		addType("item");
		
		setGraphic(sprite.getScaledCopy(0.75f));
		
		this.name = "Bat Wing";
		this.description = "The leathery wing \nof a bat.\n\nRight-click to\ndestroy.";
	}
	
	@Override
	public void render (GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
	}
	
	public void update (GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		
		bob();
	}

	public void use() {
	}
}
