package emptybox.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.map.MapGrid;

import it.marteEngine.entity.Entity;

public class Shot extends Entity {
	
	private int damage, range;
	private String direction;
	private SpriteSheet sheet;
	private Image sprite;
	private int x1, y1;
	public String type = "shot";
	private MapGrid grid;

	public Shot(float x, float y, String direction, int range, int damage, MapGrid grid) throws SlickException {
		super(x, y);
		this.x1 = (int) x;
		this.y1 = (int) y;
		this.damage = damage;
		this.range = range;
		this.direction = direction;
		sheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		setHitBox(0, 10, 32, 12);
		addType("shot");
		
		if (direction.equals("north")) {
			sprite = sheet.getSprite(4, 13).getScaledCopy(4.0f);
		} if (direction.equals("south")) {
			sprite = sheet.getSprite(0, 13).getScaledCopy(4.0f);
		} if (direction.equals("east")) {
			sprite = sheet.getSprite(6, 13).getScaledCopy(4.0f);
		} if (direction.equals("west")) {
			sprite = sheet.getSprite(2, 13).getScaledCopy(4.0f);
		}
		setGraphic(sprite);
		
		this.grid = grid;
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		

		if (direction.equals("north")) {
			if (collide(SOLID, x, y -= 7) != null) {
				destroy();
				if (y <= y1 - range) {
					destroy();
					grid.getSelectedRoom().entities.remove(this);
				}
					
			}
		}
		
		if (direction.equals("south")) {
			if (collide(SOLID, x, y += 7) != null) {
				destroy();
				if (y <= y1 + range) {
					destroy();
					grid.getSelectedRoom().entities.remove(this);
				}
					
			}
		}
		
		if (direction.equals("east")) {
			if (collide(SOLID, x += 7, y) != null) {
				destroy();
				if (y <= x1 + range) {
					destroy();
					grid.getSelectedRoom().entities.remove(this);
				}
					
			}
		}
		
		if (direction.equals("west")) {
			if (collide(SOLID, x -= 7, y) != null) {
				destroy();
				if (y <= x1 - range) {
					destroy();
					grid.getSelectedRoom().entities.remove(this);
				}
					
			}
		}
		
		if (collide("enemy", x, y) != null) {
			destroy();
			grid.getSelectedRoom().entities.remove(this);
		}
		
	}
	
	
	
	@Override 
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
	}
}
