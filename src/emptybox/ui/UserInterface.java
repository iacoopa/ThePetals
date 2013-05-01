package emptybox.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class UserInterface {

	private Image mapbackgroundinside, mapbackgroundoutside;
	
	public UserInterface() {
		try {
			this.mapbackgroundinside = new Image("res/images/lofi_map_inside.png");
			this.mapbackgroundoutside = new Image("res/images/lofi_map_outside.png");
			mapbackgroundinside.setFilter(Image.FILTER_NEAREST);
			mapbackgroundoutside.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void renderinside(Graphics g) {
		mapbackgroundinside.draw(640, 32, 4.0f);

	}
	
	public void renderoutside(Graphics g) {
		mapbackgroundoutside.draw(608, 0, 4.0f);

	}
	
}
