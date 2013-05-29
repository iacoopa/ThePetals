package emptybox.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class UserInterface {

	private Image mapbackgroundinside, mapbackgroundoutside, infoBackground;
	
	public UserInterface() {
		try {
			this.mapbackgroundinside = new Image("res/images/lofi_map_inside.png");
			this.mapbackgroundoutside = new Image("res/images/lofi_map_outside.png");
			mapbackgroundinside.setFilter(Image.FILTER_NEAREST);
			mapbackgroundoutside.setFilter(Image.FILTER_NEAREST);
			this.infoBackground = new Image("res/images/lofi_info.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void renderinside(Graphics g) {
		mapbackgroundinside.draw(640, 32, 4.0f);

	}
	
	public void renderoutside(Graphics g) {
		mapbackgroundoutside.draw(608, 0, 4.0f);
		infoBackground.draw(10, 10, 4.0f);
	}
	
}
