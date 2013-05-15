package emptybox.world;

import it.marteEngine.ME;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

import java.util.ArrayList;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheetFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.entities.Player;
import emptybox.entities.monsters.Bat;
import emptybox.entities.monsters.Slime;
import emptybox.generator.Generator;
import emptybox.generator.Room;
import emptybox.map.MapGrid;
import emptybox.ui.UserInterface;

public class GameWorld extends World {

	public ArrayList<Room> rooms = new ArrayList<Room>();
	public MapGrid grid;
	private UserInterface ui;
	private Player player;
	private AngelCodeFont font;
	private StateBasedGame game;
	private Generator generator;

	public GameWorld(int id, GameContainer container, StateBasedGame game, Generator generator) throws SlickException {
		super(id, container);
		ui = new UserInterface();
		addDoors();		
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
		this.game = game;
		this.generator = generator;
	}

	public void setGrid(MapGrid grid) throws SlickException {
		this.grid = grid;
		grid.addDoors();
		player = new Player(384, 512, 350, 10, 10, grid, game);
		
		for (Room r : grid.grid.values()) {
			r.entities.add(player);
		}
		addEnemies();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		ui.renderinside(g);
		g.translate(3 * 32, -(17 * 32));
		grid.render(g);
		g.translate( - (3 * 32), - (-(17 * 32)));
		g.setColor(Color.black);
		g.translate(1216 + grid.distance(), 0);
		g.fillRect(0, 0, 608, 192);
		g.fillRect(0, 192, 800, 480);
		ui.renderoutside(g);
		font.drawString(65, 135, "Find the Petals.");
		font.drawString(15, 15, "Archer - Level: " + player.level);
		font.drawString(15, 30, "Health: " + player.health + "");
		grid.getSelectedRoom().render(g);
		super.render(container, game, g);
		g.translate(-1216 + grid.distance(), 0);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
								
		for (Entity e : grid.getSelectedRoom().entities) {
			if (!getEntities().contains(e)) {
				add(e, World.GAME);
			}
		}
		
		for (Entity e : getEntities()) {
			if (!grid.getSelectedRoom().entities.contains(e)) {
				ME.remove(e);	
			}
		}
		
		for (int i = 0; i <= grid.getSelectedRoom().entities.size(); i++) {
			
			if (grid.getSelectedRoom().entities.get(i).isType("enemy")) {
				break;
			}
			if (i == grid.getSelectedRoom().entities.size()) {
				grid.getSelectedRoom().unlocked = true;
			}
				
		}
		
		if (grid.getSelectedRoom().enemies.isEmpty()) {
			grid.getSelectedRoom().unlocked = true;
		}
		
		if (player.dead) {
			game.addState(generator.generate(1));
			game.enterState(1);
		}
		
	}

	public void addDoors() {
		for (Room r : rooms) {
			if (grid.roomExists(new Vector2f(r.gridX, r.gridY - 1))) { // north
				r.hasDoorNorth = true;
			}
			if (grid.roomExists(new Vector2f(r.gridX, r.gridY + 1))) { // south
				r.hasDoorSouth = true;
			}
			if (grid.roomExists(new Vector2f(r.gridX + 1, r.gridY))) { // east
				r.hasDoorEast = true;
			}
			if (grid.roomExists(new Vector2f(r.gridX - 1, r.gridY))) { // west
				r.hasDoorWest = true;
			}

		}
	}
	
	public void addEnemies() throws SlickException {
		
		
		for (Room r : grid.grid.values()) {
			if (r.start != true) {
				int rand = (int) (Math.random() * 2);
				switch (rand) {
					case 0:
						Bat bat = new Bat(64, 213, player, 5); // top left
						r.entities.add(bat);
						r.enemies.add(bat);
						// Bat bat1 = new Bat(736, 213, player, 5); // top right
						// r.entities.add(bat1);
						// r.enemies.add(bat1);
						//Bat bat2 = new Bat(64, 536, player, 5); // bottom left
						//r.entities.add(bat2);
						//r.enemies.add(bat2);
						Bat bat3 = new Bat(736, 536, player, 5); // bottom right
						r.entities.add(bat3);
						r.enemies.add(bat3);
						break;
					case 1:
						Slime s1 = new Slime(290, 400, player, 8);
						Slime s2 = new Slime(500, 400, player, 8);
						Bat b1 = new Bat(400, 400, player, 5);
						r.entities.add(b1);
						r.enemies.add(b1);
						r.entities.add(s1);
						r.enemies.add(s1);
						r.entities.add(s2);
						r.enemies.add(s2);
				}
			}
		}
	}
 }
