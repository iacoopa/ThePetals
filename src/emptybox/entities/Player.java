package emptybox.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import it.marteEngine.entity.Entity;

public class Player extends Entity {

	private SpriteSheet sheet;
	private Image sprite;
	private String UP, DOWN, LEFT, RIGHT, SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT,
			SHOOT_RIGHT;

	public Player(float x, float y) throws SlickException {
		super(x, y);

		sheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		sprite = sheet.getSprite(0, 29);
		setGraphic(sprite.getScaledCopy(4.0f));
		setHitBox(0, 0, 32, 32);

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
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);
		if (check(UP)) {

			if (collide(SOLID, x, y -= 0.3f * delta) != null) {
				//direction = "north";
				y += 0.3f * delta;
			}

			this.sprite = sheet.getSprite(3, 29);
			setGraphic(sprite.getScaledCopy(4.0f));
		}
		if (check(LEFT)) {
			if (collide(SOLID, x -= 0.3f * delta, y) != null) {
				this.x += 0.3 * delta;
			//	direction = "west";
			}

			this.sprite = sheet.getSprite(2, 29);
			setGraphic(sprite.getScaledCopy(4.0f));

		}
		if (check(DOWN)) {
			if (collide(SOLID, x, y += 0.3f * delta) != null) {
				this.y -= 0.3 * delta;
				//direction = "south";
			}
			this.sprite = sheet.getSprite(1, 29);
			setGraphic(sprite.getScaledCopy(4.0f));

		}
		if (check(RIGHT)) {
			if (collide(SOLID, x += 0.3f * delta, y) != null) {
				this.x -= 0.3 * delta;
				//direction = "east";
			}
			this.sprite = sheet.getSprite(0, 29);
			setGraphic(sprite.getScaledCopy(4.0f));

		}

	}

}
