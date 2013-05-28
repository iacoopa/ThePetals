package emptybox.entities.items;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import emptybox.Point;
import emptybox.entities.Player;

import it.marteEngine.entity.Entity;

public class Item extends Entity{

	private int bobTimer;
	
	private boolean top, bottom, center;
	protected Image sprite;
	public String name, description;
	public int damageBoost, healthBoost, rangeBoost;
	protected Player player;
	public int slot = 0;
	public Rectangle rectangle;
	protected AngelCodeFont font;
	protected Input input;
	
	public Item(float x, float y, Player p) throws SlickException {
		super(x, y);
		
		bobTimer = 60;
		player = p;
		top = false;
		bottom = false;
		center = true;
		
		this.collidable = true;
		rectangle = new Rectangle(50 + (40 * slot), 57, 32, 32);
		setHitBox(0,0, 24, 24);
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
	}

	public void Update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		
	}
	
	public void bob() {
		if (bobTimer >= 30 && center == true) {
			bobTimer = 15;
			top = true;
			center = false;
		} else if (bobTimer >= 30 && bottom == true) {
			bobTimer = 0;
			top = true;
			bottom = false;
		} else if (bobTimer >= 30 && top == true) {
			bobTimer = 0;
			bottom = true;
			top = false;
		}
		
		if (bottom) {
			y += 0.2f;
		} else if (top) {
			y -= 0.2f;
		}
		
		bobTimer ++;
	}
	
	public void draw(Point v, Graphics g) {
		sprite.draw(304 + (v.x * 40), 304 + (v.y * 40));
	}
	
	public void use() {
		System.out.println("use");
	}
	
	public void drawEquip(GameContainer container, Graphics g) throws SlickException {
		
		if (slot == 0) {
			sprite.draw(249, 55);
		} else {
			sprite.draw(250 + (40 * slot), 57);
		}
		
		if (input == null) {
			input = container.getInput();
		}
		
		if (rectangle.contains(input.getMouseX(), input.getMouseY())) {
			g.setColor(new Color(102, 102, 102, 127));
			g.fillRect(x, y, 150, 150);
			font.drawString(x + 30, y + 5, name);
			font.drawString(x + 5, y + 25, description);

		}
	}

}
