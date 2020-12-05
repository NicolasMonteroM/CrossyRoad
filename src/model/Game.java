package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import processing.core.PApplet;

public class Game implements Comparable<Game>{
	
	private int duration;
	private LocalDateTime date;
	private PApplet app;
	
	private String dateTime;

	public Game(PApplet app, int duration, LocalDateTime date) {
		this.app = app;
		this.duration = duration;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm:ss");
		dateTime= date.format(format);
		this.date = date;
		
	}

	public void drawGameInfo(int posX, int posY) {
		
		
	}

	@Override
	public int compareTo(Game o) {
		
		return date.compareTo(o.getDate());
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
