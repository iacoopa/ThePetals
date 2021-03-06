package emptybox.world;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class StartState extends World {
	
	private AngelCodeFont font;
	private Input input;
	
	public StartState(int id, GameContainer container, StateBasedGame game) throws SlickException {
		super(id, container);
		
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
		input = container.getInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//super.render(container, game, g);
		String title = "Welcome to the Petals demo.";
		String body = "Please press Enter to begin.";
		
		font.drawString(400 - (font.getWidth(title) / 2), 200, title);
		font.drawString(400 - (font.getWidth(body) / 2), 215, body);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game,  int delta) throws SlickException {
		//super.update(container, game, delta);
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(1);
		}
	}
}
