package emptybox.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import emptybox.Point;
import emptybox.entities.items.Item;

public class InventoryGrid {

	private Item[][] items = new Item[5][5];
	private Map<Rectangle, Point> boxes = new HashMap<Rectangle, Point>();
	private Map<Point, Image> slots = new HashMap<Point, Image>();
	private Input input = null;
	public Point selectedPoint;
	
	public InventoryGrid() throws SlickException {
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j ++) {
				Image image = new Image("res/images/lofi_item_slot.png");
				image.setFilter(Image.FILTER_NEAREST);
				slots.put(new Point(i, j), image.getScaledCopy(4.0f));
			}
		}
		for (int j = 0; j < 5; j ++) {
			for (int i = 0; i < 5; i ++) {
				items[i][j] = null;
			}
		}	
		
		for (int j = 0; j < 5; j ++) {
			for (int i = 0; i < 5; i ++) {
				boxes.put(new Rectangle(300 + (i * 40), 300 + (j * 40), 40, 40), new Point(i, j));
			}
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		for (Image i : slots.values()) {
			for (Point p : slots.keySet()) {
				i.draw(300 + (p.x * 40), 300 + (p.y * 40));
			}
		}
		for (int j = 0; j < 5; j ++) {
			for (int i = 0; i < 5; i ++) {
				if (items[i][j] != null) {
					items[i][j].draw(new Point(i, j), g);
				}
			}
		}
	}
	
	public void addItem(Item item) {		
		for (int j = 0; j < 5; j ++) {
			for (int i = 0; i < 5; i ++) {
				if (items[i][j] == null) {
					items[i][j] = item;
					System.out.println("Item get!");
					return;
				}
			}
		}
	}
	
	public Collection<Item> getItems() {
		return null;
	}
	
	public boolean isOccupied(Point p) {
		if (items[(int) p.x][(int) p.y] != null) {
			return true;
		}
		return false;
	}
	
	public void update(GameContainer container, int delta, boolean open) {
		if (input == null) {
			input = container.getInput();
		}
		
		if (open) {
			for (Rectangle rect : boxes.keySet()) {
				if (rect.contains(input.getMouseX(), input.getMouseY())) {
					selectedPoint = boxes.get(rect);
				}
			}
			
			if (selectedPoint != null) {
				System.out.println(selectedPoint.x + " " + selectedPoint.y);
			}
			
			if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
				if (items[(int) selectedPoint.x][(int) selectedPoint.y] != null) {
					System.out.println("click");
					items[(int) selectedPoint.x][(int) selectedPoint.y].use();
					items[(int) selectedPoint.x][(int) selectedPoint.y] = null;
				}
			}
		}
	}
}