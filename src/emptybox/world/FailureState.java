package emptybox.world;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.entities.Player;

import it.marteEngine.World;

public class FailureState extends World {
	
	private AngelCodeFont font;
	private Input input;
	private Player player;
	
	public FailureState(int id, GameContainer container, StateBasedGame game, Player player) throws SlickException {
		super(id, container);
		
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
		input = container.getInput();
		this.player = player;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//super.render(container, game, g);
		String title = "You died!";
		String body = "Please press Enter to try again!";
		
		font.drawString(400 - (font.getWidth(title) / 2), 200, title);
		font.drawString(400 - (font.getWidth(body) / 2), 215, body);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game,  int delta) throws SlickException {
		//super.update(container, game, delta);
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			player.dead = true;
		}
	}
}
