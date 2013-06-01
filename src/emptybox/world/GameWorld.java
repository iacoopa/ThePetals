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
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.entities.Door;
import emptybox.entities.Player;
import emptybox.entities.Stairs;
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
	private Room exitRoom;

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
		player = new Player(384, 512, grid, game);
		
		for (Room r : grid.grid.values()) {
			r.entities.add(player);
		}
		addEnemies();
		game.addState(new FailureState(2, container, game, player));
		
		for (Room r : rooms) {
			if (r.exit) {
				exitRoom = r;
			}
		}
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
		grid.getSelectedRoom().render(g);
		super.render(container, game, g);
		ui.renderoutside(g);
		font.drawString(65, 135, "Find the Petals.");
		font.drawString(15, 15, "Archer - Level: " + player.level);
		font.drawString(15, 30, "Health: " + player.health + "/" + player.maxHealth);
		font.drawString(15, 45, "Damage: " + player.damage + "");
		font.drawString(15, 60, "Range: " + player.range + "");
		font.drawString(15, 75, "Atk Speed: " + player.maxAttackTimer + "");
		
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
			for (Entity e : grid.getSelectedRoom().doors) {
				Door d = (Door) e;
				d.unlocked = true;
			}
		}
		
		if (player.dead) {
			
			game.addState(generator.generate(game.getStateCount() + 1));
			game.enterState(game.getStateCount());
		}
		
		for (int i = 0; i < rooms.size() + 1; i++) {
			if (rooms.get(i).unlocked) {
				if (i == rooms.size()) {
					exitRoom.entities.add(new Stairs(400, 400, game));
				}
			} else {
				break;
			}
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
		for (Room r : rooms) {
			r.map.addSpawners(player);
			r.addEnemies();
		}
	}
 }
