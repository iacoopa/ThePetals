package emptybox.entities.skills;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import emptybox.entities.Player;
import emptybox.entities.Shot;

public class ArrowBlast extends Skill{

	private List<Shot> shots = new ArrayList<Shot>();
	
	public ArrowBlast(float x, float y, Player p, int pos) throws SlickException {
		super(x, y, p, pos);
		
		player = p;
		SpriteSheet sheet = new SpriteSheet("res/images/lofi_obj_packA.png", 8, 8);
		
		this.icon = sheet.getSprite(0, 4).getScaledCopy(4.0f);
		this.cooldown = 0;
	}
	
	public void use() throws SlickException {
		shots.add(new Shot(player.x, player.y, "north", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "south", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "east", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "west", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "northeast", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "northwest", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "southeast", 350, player.damage * 2, player.grid));
		shots.add(new Shot(player.x, player.y, "southwest", 350, player.damage * 2, player.grid));
		
		for (Shot s : shots) {
			player.grid.getSelectedRoom().entities.add(s);
		}
	}
}
