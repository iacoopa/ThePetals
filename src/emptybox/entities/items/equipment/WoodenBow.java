package emptybox.entities.items.equipment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;
import emptybox.entities.items.Item;

public class WoodenBow extends Item {

	public WoodenBow(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		sprite = sheet.getSprite(0, 4).getScaledCopy(4.0f);
		
		healthBoost = 0;
		damageBoost = 1;
		rangeBoost = 50;
		
		setGraphic(sprite.getScaledCopy(0.75f));
		slot = 2;
		addType("item");
		
		name = "Wooden Bow";
		description = "Bow made of \nwood.\n\nDamage + " + damageBoost + ".\nRange + " + rangeBoost + ".";
	}
	
	public void update(GameContainer container, int delta) {
		bob();
	}
	
	public void use() {
		if (player.weapon != null) {
			player.inventory.grid.addItem(player.weapon);
		}
		player.weapon = this;
		player.recalcStats();
	}
}
