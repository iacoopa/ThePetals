package emptybox.entities.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;

public class SlimeJelly extends Item {
	
	public SlimeJelly(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		
		SpriteSheet sheet = new SpriteSheet("/res/images/lofi_char.png", 8, 8);
	
		sprite = sheet.getSprite(14, 11).getScaledCopy(4.0f);
		
		addType("item");
		
		setGraphic(sprite.getScaledCopy(0.75f));
		
		this.name = "Slime Jelly";
		this.description = "The remains of a\nslime.\nExtremely gooey.\n\nRight-click to\ndestroy.";
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
