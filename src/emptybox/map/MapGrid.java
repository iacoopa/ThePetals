package emptybox.map;

import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import emptybox.generator.Room;

public class MapGrid {

	public HashMap<Vector2f, Room> grid = new HashMap<Vector2f, Room>();
	public int width, height;
	public Rectangle viewport;
	private Vector2f selectedCoords;
	public int tileSize = 32;
	private SpriteSheet sheet;
	private Image currentSprite, otherSprite, exitSprite;

	public MapGrid(int width, int height, Rectangle viewport, Vector2f start)
			throws SlickException {

		sheet = new SpriteSheet("res/images/hifi_map.png", 32, 32);
		currentSprite = sheet.getSprite(4, 2);
		otherSprite = sheet.getSprite(5, 2);
		exitSprite = sheet.getSprite(8, 1);
		this.width = width;
		this.height = height;
		this.viewport = viewport;
		this.selectedCoords = start;
		selectTile(new Vector2f(19, 19));
	}

	public boolean roomExists(Vector2f coords) {
		if (grid.keySet().contains(coords)) {
			return true;
		} else {
			return false;
		}
	}

	public int distance() {
		return (getSelectedRoom().gridX - 19) * 64;
	}

	public void addDoors() throws SlickException {
		for (Room r : grid.values()) {
			if (grid.keySet().contains(new Vector2f(r.gridX, r.gridY - 1))) {
				r.hasDoorNorth = true;
			}
			if (grid.keySet().contains(new Vector2f(r.gridX, r.gridY + 1))) {
				r.hasDoorSouth = true;
			}
			if (grid.keySet().contains(new Vector2f(r.gridX + 1, r.gridY))) {
				r.hasDoorEast = true;
			}
			if (grid.keySet().contains(new Vector2f(r.gridX - 1, r.gridY))) {
				r.hasDoorWest = true;
			}
			r.updateDoors();
		}
	}

	public void render(Graphics g) {
		g.translate(viewport.getCenterX() - (selectedCoords.x * 32),
				viewport.getCenterY() - (selectedCoords.y * 32));

		for (Room r : grid.values()) {
			if (r.exit) {
				g.drawImage(exitSprite, r.gridX * 32, r.gridY * 32);
			} else {
				g.drawImage(otherSprite, r.gridX * 32, r.gridY * 32);
			}
		}

		if (getSelectedRoom().exit) {
			g.drawImage(exitSprite, selectedCoords.x * 32,
					selectedCoords.y * 32);
		} else {
			g.drawImage(currentSprite, selectedCoords.x * 32,
					selectedCoords.y * 32);
		}

		g.translate((-(viewport.getCenterX()) - (selectedCoords.x * 32)),
				-((viewport.getCenterY()) - (selectedCoords.y * 32)));
	}

	public void selectTile(Vector2f coords) {
		if (grid.get(coords) != null) {
			selectedCoords = coords;
		}
	}

	public Room getSelectedRoom() {
		return grid.get(selectedCoords);
	}
}
