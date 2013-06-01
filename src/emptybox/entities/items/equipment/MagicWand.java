package emptybox.entities.items.equipment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;
import emptybox.entities.items.Item;

public class MagicWand extends Item {

	public MagicWand(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		sprite = sheet.getSprite(12, 3).getScaledCopy(4.0f);
		
		healthBoost = 0;
		damageBoost = 1;
		rangeBoost = -25;
		speedBoost = 10;
		
		setGraphic(sprite.getScaledCopy(0.75f));
		slot = 2;
		wand = true;
		addType("item");
		
		name = "Magic Wand";
		description = "A wooden wand/nimbued with magic. + " + damageBoost + ".\nRange - " + rangeBoost + ".";
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
