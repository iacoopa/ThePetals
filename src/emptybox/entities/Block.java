
package emptybox.entities;

import it.marteEngine.entity.Entity;

import java.awt.geom.Rectangle2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Block extends Entity {
	
	public int width, height;
	public Rectangle2D rect; 
	public String type;
	public boolean delete;
	
	public Block (float x, float y, Image image) {
		super(x, y);
		this.width = 32;
		this.height = 32;
		//setGraphic(image);
		setHitBox(0, 9, width, height);
		collidable = true;
		addType(SOLID);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		if (delete == true) {
			destroy();
		}
	}
}
