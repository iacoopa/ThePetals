package emptybox.world;

import it.marteEngine.ME;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.entities.Player;
import emptybox.generator.Room;
import emptybox.map.MapGrid;
import emptybox.ui.UserInterface;

public class GameWorld extends World {

	public ArrayList<Room> rooms = new ArrayList<Room>();
	public MapGrid grid;
	private UserInterface ui;
	private Player player;

	public GameWorld(int id, GameContainer container) throws SlickException {
		super(id, container);
		ui = new UserInterface();
		addDoors();		
		player = new Player(400, 400);
	}

	public void setGrid(MapGrid grid) throws SlickException {
		this.grid = grid;
		grid.addDoors();
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
		
		if (!grid.getSelectedRoom().entities.contains((Entity) player)) {
			grid.getSelectedRoom().entities.add(player);
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
}
