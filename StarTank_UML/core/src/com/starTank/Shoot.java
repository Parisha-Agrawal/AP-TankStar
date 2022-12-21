package com.starTank;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;


public class Shoot extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	private HashMap<String,String> fNameTime = new HashMap<>(); // for savedFileName and time

	public HashMap<String, String> getfNameTime() {
		return fNameTime;
	}

	public void addToFNameTime(String fname, String time) {
		fNameTime.put(fname,time);
	}

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenu(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public void remove() {

	}
}
