package model;

import processing.core.PApplet;

public class Player extends Entity {

	public Player(PApplet app, int posX, int posY, int speed) {
		super(app, posX, posY, speed);
	}

	public void drawEntity() {
		app.noStroke();
		app.fill(245, 84, 105);
		app.ellipse(posX, posY, 20, 20);
	}

	public void movement() {

		if (app.key == 'w') {
			posY -= 50;
		}

		if (app.key == 's') {
			posY += 50;
		}

		if (app.key == 'a') {
			posX -= 50;
		}

		if (app.key == 'd') {
			posX += 50;
		}

	}

}
