package data;

import static helpers.Artist.*;

import org.lwjgl.opengl.Display;

import helpers.Clock;
import helpers.StateManager;

public class Boot {

	public Boot() {
		BeginSession();

		// Game game = new Game(map);

		while (!Display.isCloseRequested()) {
			Clock.update();

			// game.update();
			StateManager.update();

			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	public static void main(String[] args) {
		new Boot();
	}
}
