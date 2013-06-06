package emptybox.entities;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.entities.items.Item;
import emptybox.entities.skills.ArrowBlast;
import emptybox.entities.skills.Skill;
import emptybox.map.MapGrid;
import emptybox.ui.Inventory;

import it.marteEngine.entity.Entity;

public class Player extends Entity {

	private SpriteSheet sheet;
	private Image sprite;
	private String UP, DOWN, LEFT, RIGHT, SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT,
			SHOOT_RIGHT, INVENTORY, SKILL1;
	public int health, range, damage, maxHealth, attackTimer, hitTimer, maxAttackTimer, level, skill1Cooldown;
	public MapGrid grid;
	public Inventory inventory = new Inventory();
	public boolean dead = false;
	private List<Skill> skills = new ArrayList<Skill>();
	public Item helmet, armor, weapon;
	private Image blankHelmet, blankArmor, blankWeapon;
	private StateBasedGame game;

	public Player(float x, float y,
			MapGrid grid, StateBasedGame state) throws SlickException {
		super(x, y);
		this.game = state;
		this.health = 10;
		this.maxHealth = 10;
		this.range = 350;
		this.damage = 1;
		this.hitTimer = 60;
		this.maxAttackTimer = 20;
		
		sheet = new SpriteSheet("res/images/lofi_char.png", 8, 8);
		sprite = sheet.getSprite(0, 29).getScaledCopy(4.0f);
		setGraphic(sprite);
		setHitBox(8, 15, 28, 17);
		this.grid = grid;
		level = 1;
		
		SpriteSheet objSheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		SpriteSheet helmSheet = new SpriteSheet("res/images/lofi_obj_packA.png", 8, 8);
		blankHelmet = helmSheet.getSprite(0, 3).getScaledCopy(4.0f);
		blankHelmet.setImageColor(148, 148, 148, 127);
		blankArmor = objSheet.getSprite(4, 4).getScaledCopy(4.0f);
		blankArmor.setImageColor(148, 148, 148, 127);
		blankWeapon = objSheet.getSprite(5, 3).getScaledCopy(4.0f);
		blankWeapon.setImageColor(148, 148, 148);

		UP = "up";
		DOWN = "down";
		LEFT = "left";
		RIGHT = "right";
		SHOOT_UP = "shoot_up";
		SHOOT_DOWN = "shoot_down";
		SHOOT_LEFT = "shoot_left";
		SHOOT_RIGHT = "shoot_right";
		INVENTORY = "inventory";
		SKILL1 = "skill1";

		define(UP, Input.KEY_W);
		define(DOWN, Input.KEY_S);
		define(LEFT, Input.KEY_A);
		define(RIGHT, Input.KEY_D);
		define(SHOOT_UP, Input.KEY_UP);
		define(SHOOT_DOWN, Input.KEY_DOWN);
		define(SHOOT_LEFT, Input.KEY_LEFT);
		define(SHOOT_RIGHT, Input.KEY_RIGHT);
		define(INVENTORY, Input.KEY_I);

		addType(PLAYER);
		attackTimer = 0;

		
		skills.add(new ArrowBlast(x, y, this, 0));
		define(SKILL1, skills.get(0).key);
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		super.render(container, g);
		if (inventory.open) {
			inventory.render(container, g);
		}
		
		for (Skill s : skills) {
			s.draw(container, g);
		}
		for (int i = 0; i < 3; i ++) {
			g.drawImage(new Image("res/images/lofi_item_slot.png").getScaledCopy(4.0f), 245 + (40 * i), 53);
		}
		
		if (helmet != null) {
			helmet.drawEquip(container, g);
		} else {
			//blankHelmet.draw(249, 55);
		}
		if (armor != null) {
			armor.drawEquip(container, g);
		} else {
			//blankArmor.draw(250 + 40, 57);
		} if (weapon != null) {
			weapon.drawEquip(container, g);
		} else {
			//blankWeapon.draw(250 + 80, 57);
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		if (inventory.open != true) {
		attackTimer += 1;
		hitTimer++;
		super.update(container, delta);
		if (check(UP)) {

			if (collide(SOLID, x, y -= 0.3f * delta) != null) {
				y += 0.3f * delta;
			}

			this.sprite = sheet.getSprite(3, 29).getScaledCopy(4.0f);
			setGraphic(sprite);
		}
		if (check(LEFT)) {
			if (collide(SOLID, x -= 0.3f * delta, y) != null) {
				this.x += 0.3 * delta;
			}

			this.sprite = sheet.getSprite(2, 29).getScaledCopy(4.0f);
			setGraphic(sprite);

		}
		if (check(DOWN)) {
			if (collide(SOLID, x, y += 0.3f * delta) != null) {
				this.y -= 0.3 * delta;
			}
			this.sprite = sheet.getSprite(1, 29).getScaledCopy(4.0f);
			setGraphic(sprite);

		}
		if (check(RIGHT)) {
			if (collide(SOLID, x += 0.3f * delta, y) != null) {
				this.x -= 0.3 * delta;
			}
			this.sprite = sheet.getSprite(0, 29).getScaledCopy(4.0f);
			setGraphic(sprite);

		}

		if (hitTimer >= 60) {
			sprite.setImageColor(255, 255, 255, 1.0f);
			if (collide("enemy", x, y) != null) {
				health--;
				hitTimer = 0;
			}
			if (collide("enemyshot", x, y) != null) {
				health--;
				hitTimer = 0;
			}
		} else {
			sprite.setImageColor(175, 0, 0, 0.5f);
		}

		if (health <= 0) {
			game.enterState(2);
		}

		if (check(SHOOT_UP) && attackTimer >= maxAttackTimer) {
			if (weapon != null && weapon.wand) {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "northwest",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "northeast",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "north",
						range, damage, grid));
				attackTimer = 0;
			}
			else {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "north",
						range, damage, grid));
				attackTimer = 0;
			}
		} else if (check(SHOOT_DOWN) && attackTimer >= maxAttackTimer) {
			if (weapon != null && weapon.wand) {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "southwest",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "southeast",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "south",
						range, damage, grid));
				attackTimer = 0;
			}
			else {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "south",
						range, damage, grid));
				attackTimer = 0;
			}
		} else if (check(SHOOT_RIGHT) && attackTimer >= maxAttackTimer) {
			if (weapon != null && weapon.wand) {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "northeast",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "southeast",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "east",
						range, damage, grid));
				attackTimer = 0;
			}
			else {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "east",
						range, damage, grid));
				attackTimer = 0;
			}
		} else if (check(SHOOT_LEFT) && attackTimer >= maxAttackTimer) {
			if (weapon != null && weapon.wand) {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "northwest",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "southwest",
						range, damage, grid));
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "west",
						range, damage, grid));
				attackTimer = 0;
			}
			else {
				grid.getSelectedRoom().entities.add(new Shot(x, y + 3, "west",
						range, damage, grid));
				attackTimer = 0;
			}
		}

		if (collide("item", x, y) != null) {
			Item item = (Item) collide("item", x, y);

			grid.getSelectedRoom().entities.remove(item);
			item.destroy();
			inventory.addItem(item);
		}
		
		}

		if (pressed(INVENTORY)) {
			if (inventory.open) {
				inventory.open = false;
			} else {
				inventory.open = true;
			}
		}
		skills.get(0).cooldown --;
		if (skills.get(0).cooldown <= 0) {
			if (pressed(SKILL1)) {
				skills.get(0).use();
				skills.get(0).cooldown = 300;
			}
		}
		
		inventory.update(container, delta);
		
		if (health > maxHealth) {
			health = maxHealth;
		}
	}
	
	public void recalcStats() {
		maxHealth = 10;
		range = 350;
		damage = 1;
		maxAttackTimer = 20;
		if (helmet != null) {
			maxHealth += helmet.healthBoost;
			damage += helmet.damageBoost;
			range += helmet.rangeBoost;
			maxAttackTimer += helmet.speedBoost;
		} if (armor != null) {
			maxHealth += armor.healthBoost;
			damage += armor.damageBoost;
			range += armor.rangeBoost;
			maxAttackTimer += armor.speedBoost;
		} if (weapon != null) {
			maxHealth += weapon.healthBoost;
			damage += weapon.damageBoost;
			range += weapon.rangeBoost;
			maxAttackTimer += weapon.speedBoost;
		}
	}
}