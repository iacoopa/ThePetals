package emptybox.world;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class SuccessState extends World {
	
	private AngelCodeFont font;
	private Input input;
	private boolean showSecret;
	private String password = "";
	private int letter;
	
	public SuccessState(int id, GameContainer container, StateBasedGame game) throws SlickException {
		super(id, container);
		
		font = new AngelCodeFont("res/font.fnt", "res/font_0.png");
		input = container.getInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		String title = "Congratulations! You completed the petals demo!";
		String body = "Please press Enter to play again.";
		String bonus = "If you know MY password, please enter it now.";
		String secret = "Natalia,/nThis game is dedicated to you./nI wanted to somehow surprise you with a game that/n" +
				"charted the endeavors of a main character (me) in pursuit of a woman's (you) love./n" +
				"Unfortunately, due to the recent happenings, the sweetness of this will most likely/n" +
				"go unseen. As unfortunate as this is for me, I'm continuing to keep the theme as I move/n" +
				"forward. I hope one day you can come to completely appreciate this gesture./n/n" +
				"I love you,/n" +
				"Thanks for playing.";
		
		font.drawString(400 - (font.getWidth(title) / 2), 200, title);
		font.drawString(400 - (font.getWidth(body) / 2), 215, body);
		font.drawString(400 - (font.getWidth(bonus) / 2), 300, bonus);
		font.drawString(400 - (font.getWidth(password) / 2), 315, password);
		if (showSecret) {
			font.drawString(400 - (font.getWidth(secret) / 2), 330, secret);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game,  int delta) throws SlickException {
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			game.enterState(1);
		}
		
		switch(letter) {
		case 0:
			if (input.isKeyPressed(Input.KEY_7)) {
				password = password + "7";
				letter ++;
			}
			break;
		case 1:
			if (input.isKeyPressed(Input.KEY_R)) {
				password = password + "r";
				letter ++;
			}
			break;
		case 2:
			if (input.isKeyPressed(Input.KEY_U)) {
				password = password + "u";
				letter ++;
			}
			break;
		case 3:
			if (input.isKeyPressed(Input.KEY_B)) {
				password = password + "b";
				letter ++;
			}
			break;
		case 4:
			if (input.isKeyPressed(Input.KEY_A)) {
				password = password + "A";
				letter ++;
			}
			break;
		case 5:
			if (input.isKeyPressed(Input.KEY_5)) {
				password = password + "5";
				letter ++;
			}
			break;
		case 6:
			if (input.isKeyPressed(Input.KEY_R)) {
				password = password + "R";
				letter ++;
			}
			break;
		case 7:
			if (input.isKeyPressed(Input.KEY_A)) {
				password = password + "a";
				letter ++;
				showSecret = true;
			}
			break;
		default:
			break;
		}
	}
}
