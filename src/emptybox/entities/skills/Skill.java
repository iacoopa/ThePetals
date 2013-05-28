package emptybox.entities.skills;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import emptybox.entities.Player;

import it.marteEngine.entity.Entity;

public class Skill extends Entity {

	protected int damage, healthGain, manaGain, healthCost,
				manaCost, level;
	protected Image icon;
	public int cooldown;
	protected int duration;
	public int position;
	public int key;
	private AngelCodeFont font;
	protected Player player;
	
	public Skill(float x, float y, Player player, int pos) throws SlickException {
		super(x, y);
		position = pos;
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
		
		switch(pos) {
		case 0:
			this.key = Input.KEY_Q;
			break;
		}
	}
	
	public void draw(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(new Image("res/images/lofi_item_slot.png").getScaledCopy(4.0f), 245 + (40 * position), 6);
		icon.draw(250 + (40 * position), 10);
		if (cooldown >= 0) {
			font.drawString(260 + (40 * position), 20, "" + cooldown / 60 + "");
		} else {
			font.drawString(260 + (40 * position), 20, "0");
		}
	}
	
	public void use() throws SlickException {
		
	}
	
	public void update(GameContainer container, int delta) {

	}
}
