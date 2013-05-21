package emptybox.entities.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class RedPotion extends Item {

	private Image sprite;
	
	
	
	public RedPotion(float x, float y) throws SlickException {
		super(x, y);
			
		SpriteSheet sheet = new SpriteSheet("/res/images/lofi_obj.png", 8, 8);
		
		sprite = sheet.getSprite(12, 0).getScaledCopy(4.0f);
		
		setGraphic(sprite);
	}
	
	@Override
	public void render (GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
	}
	
	public void update (GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		
		bob();
	}
}
