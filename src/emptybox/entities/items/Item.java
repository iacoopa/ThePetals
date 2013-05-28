package emptybox.entities.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import emptybox.Point;
import emptybox.entities.Player;

import it.marteEngine.entity.Entity;

public class Item extends Entity{

	private int bobTimer;
	
	private boolean top, bottom, center;
	protected Image sprite;
	
	public Item(float x, float y, Player p) {
		super(x, y);
		
		bobTimer = 60;
		
		top = false;
		bottom = false;
		center = true;
		
		this.collidable = true;
		
		setHitBox(0,0, 32, 32);
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
}
