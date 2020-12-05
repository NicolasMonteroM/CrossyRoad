package model;

import java.util.ArrayList;
import java.util.Collections;

import exception.GameLost;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * 
 * @author nicolasmonteromuriel
 *
 */

public class Logic {

	PApplet app;
	private ArrayList<Game> games;
	private ArrayList<Vehicle> vehicles;

	private String[] info;

	Entity p;

	private int playtime;

	private boolean gameOver, lost, won;

	CompareDuration durationComparator;

	public Logic(PApplet app) {

		this.app = app;
		vehicles = new ArrayList<Vehicle>();
		games = new ArrayList<Game>();

		/**
		 * Trying .txt reading, catching Runtime Exception
		 */

		try {
			info = app.loadStrings("data/initialValues.txt");
		} catch (RuntimeException e) {
			System.out.println(e);
		}

		durationComparator = new CompareDuration();

	}

	public void initializeGame() {

		vehicles.clear();

		gameOver = false;
		won = false;
		lost = false;

		playtime = 0;

		System.out.println(info);

		/**
		 * Trying method that splits String array to create initial entities, catching
		 * Runtime Exception.
		 */

		try {
			createInitialEntities();

		} catch (RuntimeException e) {
			System.out.println(e);
		}

		generateVehicles();

		System.out.println(vehicles);

	}

	/*
	 * Timer
	 */

	public void timer() {

		if (app.frameCount % 60 == 0) {
			playtime++;
		}
	}

	/*
	 * Initial entities creation
	 */

	public void createInitialEntities() {

		for (int i = 0; i < info.length; i++) {

			String[] entitiesData = info[i].split(",");
			String type = entitiesData[0];
			int speedDirection = Integer.parseInt(entitiesData[1]);
			int posX = Integer.parseInt(entitiesData[2]);
			int posY = Integer.parseInt(entitiesData[3]);

			if (type.equals("carro")) {
				vehicles.add(new Vehicle(app, posX, posY, speedDirection));

			}

			if (type.equals("personaje")) {
				p = new Player(app, posX, posY, speedDirection);

			}

		}

	}

	/**
	 * Random vehicle generation. Only 30
	 */

	public void generateVehicles() {

		for (int i = 0; i < 30; i++) {

			double dirProb = Math.random();
			int direction;

			if (dirProb < 0.5) {
				direction = 1;

			} else {
				direction = -1;

			}

			int speed = (int) Math.floor(Math.random() * 5) * direction;
			int posY = ((int) Math.floor(Math.random() * 6) * 100) + 150;
			int posX = ((int) Math.floor(Math.random() * 6) * 50) + 100;

			while (speed == 0) {

				speed = (int) Math.floor(Math.random() * 5) * direction;
			}

			vehicles.add(new Vehicle(app, posX, posY, speed));

			System.out.println("––" + "vehicle #" + vehicles.size() + " speed:");
			// System.out.println(vehicles.get(i).getSpeed());
		}

	}

	public void drawVehicle() {

		for (Vehicle p : vehicles) {
			p.drawEntity();

			/**
			 * Creating movement thread for each vehicle
			 */

			Thread vehicleMovement = new Thread(p);
			vehicleMovement.start();

		}

	}

	public void drawPlayer() {

		p.drawEntity();

	}

	public void movePlayer() {

		p.movement();
	}

	public void instructions() {

		app.fill(245, 84, 105, 120);
		app.textAlign(PConstants.CENTER);
		app.text("Move with WASD", app.width / 2, 60);
		app.text("Avoid the moving rectangles", app.width / 2, 80);
		app.text("Get here to win", app.width / 2, app.height - 60);

	}

	public void drawIndicator() {

		app.fill(145, 247, 10);
		app.text(playtime, app.width / 2, 30);

	}

	public void checkGameLost() throws GameLost {

		checkContact();

		if (lost) {

			throw new GameLost();
		}

	}

	public void checkGameWon() {

		if (p.getPosY() > app.height - 150) {

			gameWon();
			System.out.println("nyh5tregt");

		}
	}

	public void gameWon() {

		won = true;
		gameOver = true;

		// addGame();

	}

	public void checkContact() {

		for (int i = 0; i < vehicles.size(); i++) {

			int vechiclePosX = vehicles.get(i).getPosX();
			int vechiclePosY = vehicles.get(i).getPosY();

			if (PApplet.dist(vechiclePosX, vechiclePosY, p.getPosX(), p.getPosY()) < 20) {

				lost = true;
				gameOver = true;

			}
		}
	}

	public void gameOver() throws GameLost {

		checkGameWon();

		/**
		 * Trying game-lost method. Catching own exception
		 */

		try {
			checkGameLost();
		}

		catch (GameLost e) {

			gameOver = true;
			lost = true;
			System.out.println(e);

		}

	}

	public void result() {

		if (gameOver) {

			String result = "";

			if (won) {
				app.background(145, 247, 10);
				result = "You won";
			}

			if (lost) {
				app.background(245, 84, 105);
				result = "You lost";
			}

			app.fill(20);
			app.text(result, app.width / 2, app.height / 2);
			app.text("Press *Space* to restart", app.width / 2, (app.height / 2) + 30);
		}

	}

	/*
	 * Restart method
	 */

	public void restartGame() {

		if (gameOver && app.keyCode == 32) {

			initializeGame();

		}
	}

	public void addGame() {

	}

	/*
	 * Sorting indicators' list by pressing keys
	 */

	/*
	 * public void sort(char c) {
	 * 
	 * switch (c) {
	 * 
	 * case 'q':
	 * 
	 * Collections.sort(games); break;
	 * 
	 * case 'c':
	 * 
	 * System.out.println(c); // Collections.sort(games, durationComparator); break;
	 * 
	 * default: break;
	 * 
	 * } }
	 */

}
