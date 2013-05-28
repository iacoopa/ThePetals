package emptybox.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.map.MapGrid;

import it.marteEngine.entity.Entity;

public class EnemyShot extends Entity {
	
	private int damage, range;
	private String direction;
	private SpriteSheet sheet;
	private Image sprite;
	private int x1, y1;
	public String type = "shot";
	private MapGrid grid;
	private Animation deathAnim;
	private boolean dead;
	private Vector2f start, dest, trans;
	private float speed = 1.8f;

	public EnemyShot(float x, float y, Vector2f start, Vector2f dest, int range, MapGrid grid) throws SlickException {
		super(x, y);
		dead = false;
		
		addType("enemyshot");
		
		this.range = range;
		sheet = new SpriteSheet("res/images/lofi_obj.png", 8, 8);
		this.deathAnim = new Animation(sheet, 0, 9, 10, 9, true, 60, true);
		setHitBox(0, 10, 32, 12);
		
		sprite = sheet.getSprite(6, 13).getScaledCopy(4.0f);
		
		this.grid = grid;
	
		trans = new Vector2f(dest.x - start.x, dest.y - start.y);
		setAngle((int) trans.getTheta());
		setGraphic(sprite);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		x += speed * Math.cos(Math.toRadians(trans.getTheta()));
		y += speed * Math.sin(Math.toRadians(trans.getTheta()));
	}
	
	
	
	@Override 
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
		
		if (dead) {
			deathAnim.draw(x, y);
			deathAnim.start();
		}
	}
}
