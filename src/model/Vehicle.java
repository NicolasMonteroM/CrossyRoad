package model;

import processing.core.PApplet;
import processing.core.PConstants;

public class Vehicle extends Entity implements Runnable {

	public Vehicle(PApplet app, int posX, int posY, int speed) {
		super(app, posX, posY, speed);
	}

	@Override
	public void drawEntity() {

		app.rectMode(PConstants.CENTER);
		app.stroke(2);
		app.fill(145, 247, 10);
		app.rect(posX, posY, 30, 20);

	}

	@Override
	public void movement() {
		posX += speed;

		if (posX < -30) {

			posX = app.width;

		}

		if (posX > app.width) {

			posX = -30;

		}
	}
	
	/**
	 * Run method, overrided from Runnable
	 */

	@Override
	public void run() {

		movement();

	}

}
