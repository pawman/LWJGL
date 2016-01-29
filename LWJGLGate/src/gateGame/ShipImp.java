package gateGame;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.DrawQuadTexRot;

import org.newdawn.slick.opengl.Texture;

import data.Tile;

public class ShipImp implements Ship {

	private float x;
	private float y;
	private int width;
	private int height;
	private String name;
	private float angle;
	private Texture baseTexture;
	private Tile startTile;


	public ShipImp(float x, float y, int width, int height, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;

	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		DrawQuadTexRot(baseTexture, x, y, width, height, angle);
	//	DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

}
