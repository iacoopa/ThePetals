package emptybox;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import emptybox.generator.Generator;
import emptybox.world.FailureState;
import emptybox.world.StartState;
import emptybox.world.SuccessState;

public class Main extends StateBasedGame {

	private Generator generator;
	
	public Main(String title) {
		super(title);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		generator = new Generator(container, this);
		addState(new StartState(0, container, this));
		addState(generator.generate(1));
		addState(new SuccessState(3, container, this));
	}
	
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new Main(
					"Where the Petals Fall"));
			container.setDisplayMode(800, 600, false);
			container.setTargetFrameRate(60);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}


}
