package emptybox.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tests.xml.Item;

public class Inventory {

	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Inventory() {
		
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
	
	public ArrayList getItems() {
		return items;
	}
	
}
