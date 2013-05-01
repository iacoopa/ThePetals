package emptybox.generator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import emptybox.blockmap.BlockMap;
import emptybox.map.MapGrid;
import emptybox.world.GameWorld;

public class Generator {

	private GameWorld world;
	private MapGrid grid;
	private Room startingRoom, lastRoom;
	private GameContainer container;

	public Generator(GameContainer container) {
		this.container = container;
	}

	public GameWorld generate() throws SlickException {
		world = new GameWorld(0, container);
		startingRoom = new Room(38 / 2, 38 / 2, 0, new BlockMap(
				"/res/images/map.tmx", 0, 192));
		grid = new MapGrid(38, 38, new Rectangle(
				(startingRoom.gridX * 32) - 100,
				((startingRoom.gridY * 32) - 100), 200, 200), new Vector2f(
				startingRoom.gridX, startingRoom.gridY));

		grid.grid.put(new Vector2f(startingRoom.gridX, startingRoom.gridY), startingRoom);
		
		lastRoom = startingRoom;

		for (int i = 1; i <= (100 + Math.random() * 200); i++) {
			int direction = (int) (Math.random() * (4));

			Room room = null;

			switch (direction) {
			case 0:
				room = new Room(lastRoom.gridX, lastRoom.gridY - 1, i,
						new BlockMap("/res/images/map.tmx", 0, 192)); // north
				break;
			case 1:
				room = new Room(lastRoom.gridX, lastRoom.gridY + 1, i,
						new BlockMap("/res/images/map.tmx", 0, 192)); // south
				break;
			case 2:
				room = new Room(lastRoom.gridX + 1, lastRoom.gridY, i,
						new BlockMap("/res/images/map.tmx", 0, 192)); // east
				break;
			case 3:
				room = new Room(lastRoom.gridX - 1, lastRoom.gridY, i,
						new BlockMap("/res/images/map.tmx", 0, 192)); // west
				break;
			default:
				room = new Room(lastRoom.gridX, lastRoom.gridY - 1, i,
						new BlockMap("/res/images/map.tmx", 0, 192)); // default
																	  // (north)
				break;
			}

			grid.grid.put(new Vector2f(room.gridX, room.gridY), room);
			world.rooms.add(room);
			lastRoom = room;
		}
		grid.selectTile(new Vector2f(startingRoom.gridX, startingRoom.gridY));
				
		for (Room r : grid.grid.values()) {
			r.addGrid(grid);
		}
		
		world.setGrid(grid);
		return world;
	}
}
