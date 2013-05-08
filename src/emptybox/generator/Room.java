package emptybox.generator;

import it.marteEngine.entity.Entity;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import emptybox.blockmap.BlockMap;
import emptybox.entities.Block;
import emptybox.entities.Door;
import emptybox.map.MapGrid;

public class Room {

	public int gridX, gridY, id;
	public boolean visisted, hasDoorNorth, hasDoorSouth, hasDoorEast, hasDoorWest;
	private BlockMap map;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Entity> enemies = new ArrayList<Entity>();
	private MapGrid grid;
	public boolean exit, unlocked;
	
	public Room(int gridX, int gridY, int id, BlockMap map) throws SlickException {
		this.gridX = gridX;
		this.gridY = gridY;
		this.id = id;
		this.map = map;
		this.exit = false;
		this.unlocked = false;
		
		for (Block b : map.blocks) {
			entities.add(b);
		}
	}
	
	public void addGrid(MapGrid grid) {
		this.grid = grid;
	}
	
	public void render(Graphics g) {
		map.render(g);
	}
	
	public void updateDoors() throws SlickException {

		if (hasDoorNorth) {
			entities.add(new Door(384, 224, "north", grid));
		} if (hasDoorSouth) {
			entities.add(new Door(384, 546, "south", grid));
		} if (hasDoorEast) {
			entities.add(new Door(740, 416, "east", grid));
		} if (hasDoorWest) {
			entities.add(new Door(32, 416, "west", grid));
		}
	}
}
