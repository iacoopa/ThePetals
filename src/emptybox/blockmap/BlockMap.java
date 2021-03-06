package emptybox.blockmap;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import emptybox.entities.Block;
import emptybox.entities.Player;
import emptybox.entities.Spawner;

public class BlockMap {

	TiledMap map;
	public float mapWidth, mapHeight, x, y;
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Spawner> spawners = new ArrayList<Spawner>();

	public BlockMap(String ref, float x, float y) throws SlickException {
		map = new TiledMap(ref);
		this.x = x;
		this.y = y;
		mapWidth = map.getWidth() * map.getTileWidth();
		mapHeight = map.getHeight() * map.getTileHeight();

		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				if (map.getTileId(i, j, map.getLayerIndex("collision")) == 1) { // collision
																						// block

					if (map.getTileImage(i, j, map.getLayerIndex("Walls")) != null) {
						blocks.add(new Block(i * map.getTileWidth(), (j * map
								.getTileHeight()) + 181, map.getTileImage(i, j,
								map.getLayerIndex("Walls"))));

					}
					if (map.getTileImage(i, j, map.getLayerIndex("Objects")) != null) {
						blocks.add(new Block(i * map.getTileWidth(), (j * map
								.getTileHeight()) + 181, map.getTileImage(i, j,
								map.getLayerIndex("Objects"))));
					}
				}

				else {
					continue;
				}
			}
		}
	}

	public void addSpawners(Player player) throws SlickException {
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				if (map.getTileId(i, j, map.getLayerIndex("spawner")) == 499) {
					spawners.add(new Spawner(i * 32, (j * 32) + 181, "wyvern",
							player));
					// System.out.println("added");
				}
				if (map.getTileId(i, j, map.getLayerIndex("spawner")) == 436) {
					spawners.add(new Spawner(i * 32, (j * 32) + 181, "slime",
							player));
					// System.out.println("added");
				}
				if (map.getTileId(i, j, map.getLayerIndex("spawner")) == 464) {
					spawners.add(new Spawner(i * 32, (j * 32) + 181, "bat",
							player));
					// System.out.println("added");
				}
				if (map.getTileId(i, j, map.getLayerIndex("spawner")) == 360) {
					spawners.add(new Spawner(i * 32, (j * 32) + 181,
							"redSkull", player));
				}
			}
		}
	}

	public void render(Graphics g) {
		map.render((int) x, (int) y);
	}
}
