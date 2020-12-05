package model;

import processing.core.PApplet;

public abstract class Entity {

	protected int posX, posY, speed;
	PApplet app;

	public Entity(PApplet app, int posX, int posY, int speed) {
		this.posX = posX;
		this.posY = posY;
		this.app = app;
		this.speed = speed;
	}

	public void drawEntity() {

	}

	public void movement() {

	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
