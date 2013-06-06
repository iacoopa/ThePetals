package emptybox.entities.items.equipment;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;
import emptybox.entities.items.Item;

public class IronKnife extends Item {

	public IronKnife(float x, float y, Player p) throws SlickException {
		super(x, y, p);
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		sprite = sheet.getSprite(5, 3).getScaledCopy(4.0f);
		
		healthBoost = 0;
		damageBoost = 1;
		rangeBoost = -70;
		speedBoost = -20;
		
		setGraphic(sprite.getScaledCopy(0.75f));
		slot = 2;
		addType("item");
		
		name = "Lmofe Sword";
		description = "Knife made of \niron.\n\nDamage + " + damageBoost + ".\nRange - " + rangeBoost + ".\nAtk Speed + " + (int)Math.abs(speedBoost) + ".";
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
