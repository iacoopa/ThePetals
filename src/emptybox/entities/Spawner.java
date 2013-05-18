package emptybox.entities;

import org.newdawn.slick.SlickException;

import emptybox.entities.monsters.*;
import it.marteEngine.entity.Entity;

public class Spawner{

	public Entity monster; 
	
	public Spawner(int x, int y, String type, Player p) throws SlickException {
		if (type.equals("slime")) {
			monster = new Slime(x, y, p, 8);
		} else if (type.equals("bat")) {
			monster = new Bat(x, y, p, 5);
		} else if (type.equals("wyvern")) {
			monster = new Wyvern(x, y, p, 15);
		}
	}

}
