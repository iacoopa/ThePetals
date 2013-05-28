package emptybox.ui;

import java.util.Collection;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import emptybox.entities.items.Item;

public class Inventory {

	public boolean open = false;
	private Image background;
	private AngelCodeFont font;
	private InventoryGrid grid = new InventoryGrid();
	
	public Inventory() throws SlickException {
		background = new Image("res/images/lofi_inventory.png");
		background.setFilter(Image.FILTER_NEAREST);
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
	}
	
	public void addItem(Item item) {
		grid.addItem(item);
	}
	
	public Collection<Item> getItems() {
		return grid.getItems();
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.getScaledCopy(4.0f).draw(290, 275);
		font.drawString(375, 280, "Items");
		
		grid.render(container, g);
	}
	
	public void update(GameContainer container, int delta) {
		grid.update(container, delta, open);
	}
}
