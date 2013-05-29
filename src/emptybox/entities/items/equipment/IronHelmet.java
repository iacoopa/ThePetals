package emptybox.entities.items.equipment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;
import emptybox.entities.items.Item;

public class IronHelmet extends Item {

	public IronHelmet(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj_packA.png", 8, 8);
		sprite = sheet.getSprite(0, 3).getScaledCopy(4.0f);
		
		healthBoost = 5;
		damageBoost = 0;
		rangeBoost = 0;
		
		setGraphic(sprite.getScaledCopy(0.75f));
		slot = 0;
		addType("item");
		
		name = "Iron Helmet";
		description = "Helmet made of \niron.\n\nHealth + " + healthBoost + ".";
	}
	
	public void update(GameContainer container, int delta) {
		bob();
	}
	
	public void use() {
		if (player.helmet != null) {
			player.inventory.grid.addItem(player.helmet);
		}
		player.helmet = this;
		player.recalcStats();
	}
}
