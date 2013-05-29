package emptybox.entities.items.equipment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;
import emptybox.entities.items.Item;

public class LeatherArmor extends Item {

	public LeatherArmor(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		sprite = sheet.getSprite(4, 4).getScaledCopy(4.0f);
		
		healthBoost = 3;
		damageBoost = 0;
		rangeBoost = 0;
		
		setGraphic(sprite.getScaledCopy(0.75f));
		slot = 1;
		addType("item");
		
		name = "Leather";
		description = "Armor made of \nleather.\n\nHealth + " + healthBoost + ".";
	}
	
	public void update(GameContainer container, int delta) {
		bob();
	}
	
	public void use() {
		if (player.armor != null) {
			player.inventory.grid.addItem(player.armor);
		}
		player.armor = this;
		player.recalcStats();
	}
}
