package emptybox.generator;

import it.marteEngine.entity.Entity;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import emptybox.blockmap.BlockMap;
import emptybox.entities.Block;
import emptybox.entities.Door;
import emptybox.entities.Spawner;
import emptybox.map.MapGrid;

public class Room {

	public int gridX, gridY, id;
	public boolean visisted, hasDoorNorth, hasDoorSouth, hasDoorEast, hasDoorWest;
	public BlockMap map;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Entity> enemies = new ArrayList<Entity>();
	public ArrayList<Entity> doors = new ArrayList<Entity>();
	private MapGrid grid;
	public boolean exit, unlocked, start, visited;
	public boolean hasChest;
	
	public Room(int gridX, int gridY, int id, BlockMap map) throws SlickException {
		this.gridX = gridX;
		this.gridY = gridY;
		this.id = id;
		this.map = map;
		this.exit = false;
		this.unlocked = false;
		this.start = false;		
		
		for (Block b : map.blocks) {
			entities.add(b);
		}	
		if ((int) Math.random() * 2 == 1) {
			hasChest = true;
		} else {
			hasChest = false;
		}
	}
	
	public void addGrid(MapGrid grid) {
		this.grid = grid;
	}
	
	public void addEnemies() {
		for (Spawner s : map.spawners) {
			entities.add(s.monster);
			enemies.add(s.monster);
		}
	}
	
	public void render(Graphics g) {
		map.render(g);
	}
	
	public void updateDoors() throws SlickException {

		if (hasDoorNorth) {
			entities.add(new Door(384, 192, "north", grid));
			doors.add(new Door(384, 192, "north", grid));
		} if (hasDoorSouth) {
			entities.add(new Door(384, 567, "south", grid));
			doors.add(new Door(384, 567, "south", grid));
		} if (hasDoorEast) {
			entities.add(new Door(760, 416, "east", grid));
			doors.add(new Door(760, 416, "east", grid));
		} if (hasDoorWest) {
			entities.add(new Door(12, 416, "west", grid));
			doors.add(new Door(12, 416, "west", grid));
		}
	}
}
