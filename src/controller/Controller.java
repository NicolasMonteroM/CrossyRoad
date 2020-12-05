package controller;

import exception.GameLost;
import model.Logic;
import processing.core.PApplet;

public class Controller {

	Logic logic;
	PApplet app;

	public Controller(PApplet app) {
		this.app = app;
		logic = new Logic(app);

	}

	public void startGame() {

		logic.initializeGame();
	}

	public void drawGame() {

		logic.drawVehicle();
		logic.drawPlayer();
		logic.instructions();
		logic.drawIndicator();
		logic.timer();
		logic.result();

	}

	public void gameOver() {

		/**
		 * Trying game-over method, catches own exception.
		 */

		try {
			logic.gameOver();
		} catch (GameLost e) {

			System.out.println(e);
		}

	}

	public void controllerInteractions(char c) {
		// logic.sort(c);
		logic.movePlayer();
		logic.restartGame();

	}

}
