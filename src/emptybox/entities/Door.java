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
	public boolean unlocked;

	public Door(float x, float y, String direction, MapGrid grid)
			throws SlickException {
		super(x, y);
		sheet = new SpriteSheet("res/images/lofi_environment.png", 8, 8);
		this.direction = direction;
		if (direction.equals("north")) {
			image = sheet.getSprite(4, 13).getScaledCopy(4.0f);
			setHitBox(0, 0, 32, 32);
		} else if (direction.equals("south")) {
			image = sheet.getSprite(5, 14).getScaledCopy(4.0f);
			setHitBox(0, 8, 32, 16);
		} else if (direction.equals("east")) {
			image = sheet.getSprite(4, 14).getScaledCopy(4.0f);
			setHitBox(8, 0, 16, 32);
		} else if (direction.equals("west")) {
			image = sheet.getSprite(4, 14).getScaledCopy(4.0f);
			setHitBox(8, 0, 16, 32);
		} else {
			image = sheet.getSprite(4, 13).getScaledCopy(4.0f);
			setHitBox(0, 0, 32, 32);
		}
		setGraphic(image);
		addType(SOLID);

		this.grid = grid;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		if (grid.getSelectedRoom().unlocked == true) {
			if (direction.equals("north")) {
				setGraphic(image = sheet.getSprite(5, 13).getScaledCopy(4.0f));
			}
		}
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
