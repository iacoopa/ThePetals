package emptybox.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.map.MapGrid;

import it.marteEngine.entity.Entity;

public class Player extends Entity {

	private SpriteSheet sheet;
	private Image sprite;
	private String UP, DOWN, LEFT, RIGHT, SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT,
			SHOOT_RIGHT;
	public int health, range, damage, attackTimer, hitTimer, level;
	public MapGrid grid;
	private StateBasedGame state;
	public boolean dead;

	public Player(float x, float y, int range, int health, int damage,
			MapGrid grid, StateBasedGame state) throws SlickException {
		super(x, y);
		this.state = state;
		this.health = health;
		this.range = range;
		this.damage = damage;
		sheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		sprite = sheet.getSprite(0, 29).getScaledCopy(4.0f);
		setGraphic(sprite);
		setHitBox(0, 0, 32, 32);
		this.grid = grid;
		level = 1;

		UP = "up";
		DOWN = "down";
		LEFT = "left";
		RIGHT = "right";
		SHOOT_UP = "shoot_up";
		SHOOT_DOWN = "shoot_down";
		SHOOT_LEFT = "shoot_left";
		SHOOT_RIGHT = "shoot_right";

		define(UP, Input.KEY_W);
		define(DOWN, Input.KEY_S);
		define(LEFT, Input.KEY_A);
		define(RIGHT, Input.KEY_D);
		define(SHOOT_UP, Input.KEY_UP);
		define(SHOOT_DOWN, Input.KEY_DOWN);
		define(SHOOT_LEFT, Input.KEY_LEFT);
		define(SHOOT_RIGHT, Input.KEY_RIGHT);
		addType(PLAYER);
		attackTimer = 0;
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		attackTimer += 1;
		hitTimer ++;
		super.update(container, delta);
		if (check(UP)) {

			if (collide(SOLID, x, y -= 0.3f * delta) != null) {
				//direction = "north";
				y += 0.3f * delta;
			}

			this.sprite = sheet.getSprite(3, 29).getScaledCopy(4.0f);
			setGraphic(sprite);
		}
		if (check(LEFT)) {
			if (collide(SOLID, x -= 0.3f * delta, y) != null) {
				this.x += 0.3 * delta;
			//	direction = "west";
			}

			this.sprite = sheet.getSprite(2, 29).getScaledCopy(4.0f);
			setGraphic(sprite);

		}
		if (check(DOWN)) {
			if (collide(SOLID, x, y += 0.3f * delta) != null) {
				this.y -= 0.3 * delta;
				//direction = "south";
			}
			this.sprite = sheet.getSprite(1, 29).getScaledCopy(4.0f);
			setGraphic(sprite);

		}
		if (check(RIGHT)) {
			if (collide(SOLID, x += 0.3f * delta, y) != null) {
				this.x -= 0.3 * delta;
				//direction = "east";
			}
			this.sprite = sheet.getSprite(0, 29).getScaledCopy(4.0f);
			setGraphic(sprite);

		}
				
		if (hitTimer >= 60) {
			sprite.setImageColor(255, 255, 255, 1.0f);
			if (collide("enemy", x, y) != null) {
				health --;
				hitTimer = 0;
			}
		} else {
			sprite.setImageColor(175, 0, 0, 0.5f);
		}
		
		if (health <= 0) {
			dead = true;
		}

			if (check(SHOOT_UP) && attackTimer >= 30) {
				grid.getSelectedRoom().entities.add(new Shot(x, y, "north", 350, 10, grid));	
				attackTimer = 0;
			}
			else if (check(SHOOT_DOWN) && attackTimer >= 30) {
				grid.getSelectedRoom().entities.add(new Shot(x, y, "south", 350, 10, grid));	
				attackTimer = 0;
			}
			else if (check(SHOOT_RIGHT) && attackTimer >= 30) {
				grid.getSelectedRoom().entities.add(new Shot(x, y, "east", 350, 10, grid));	
				attackTimer = 0;
			}
			else if (check(SHOOT_LEFT) && attackTimer >= 30) {
				grid.getSelectedRoom().entities.add(new Shot(x, y, "west", 350, 10, grid));	
				attackTimer = 0;
			}
			
	}
}
