package emptybox.entities.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;

public class RedPotion extends Item {
	
	private Player p;
	
	public RedPotion(float x, float y, Player p) throws SlickException {
		super(x, y, p);

		this.p = p;
		
		SpriteSheet sheet = new SpriteSheet("/res/images/lofi_obj.png", 8, 8);
		
		sprite = sheet.getSprite(12, 0).getScaledCopy(4.0f);
		
		setGraphic(sprite);
		
		addType("item");
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
		p.health += 10;
	}
 }
