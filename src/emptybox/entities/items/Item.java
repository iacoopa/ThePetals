package emptybox.entities.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Item extends Entity{

	private int yRange, bobTimer;
	
	private boolean top, bottom, center;
	
	public Item(float x, float y) {
		super(x, y);
		
		bobTimer = 60;
		yRange = 10;
		
		top = false;
		bottom = false;
		center = true;
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
}
