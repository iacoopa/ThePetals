package emptybox.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import emptybox.map.MapGrid;

import it.marteEngine.entity.Entity;

public class Door extends Entity {

	private SpriteSheet sheet;
	private Image image;
	public String direction;
	private MapGrid grid;

	public Door(float x, float y, String direction, MapGrid grid)
			throws SlickException {
		super(x, y);
		sheet = new SpriteSheet("res/images/lofi_environment.png", 8, 8);
		image = sheet.getSprite(0, 13).getScaledCopy(4.0f);
		this.direction = direction;
		setGraphic(image);
		addType(SOLID);
		setHitBox(0, 0, 32, 32);
		this.grid = grid;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	@Override
	public void collisionResponse(Entity other) {
		if (grid.getSelectedRoom().unlocked == true) {

			if (other.isType(Entity.PLAYER)) {
				Player player = null;
				try {
					player = (Player) other;
				} catch (ClassCastException e) {
					return;
				}
				if (direction.equals("north")) {
					player.setPosition(new Vector2f(384, 511));
					grid.selectTile(new Vector2f(grid.getSelectedRoom().gridX,
							grid.getSelectedRoom().gridY - 1));
				}
				if (direction.equals("south")) {
					player.setPosition(new Vector2f(384, 257));
					grid.selectTile(new Vector2f(grid.getSelectedRoom().gridX,
							grid.getSelectedRoom().gridY + 1));
				}
				if (direction.equals("east")) {
					player.setPosition(new Vector2f(65, 416));
					grid.selectTile(new Vector2f(
							grid.getSelectedRoom().gridX + 1, grid
									.getSelectedRoom().gridY));
				}
				if (direction.equals("west")) {
					player.setPosition(new Vector2f(707, 416));
					grid.selectTile(new Vector2f(
							grid.getSelectedRoom().gridX - 1, grid
									.getSelectedRoom().gridY));
				}
			}
		}
	}
}
