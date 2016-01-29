package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Enemy implements Entity{
	private int width, height, health, currentCheckpoint;
	private float speed, x, y;
	private Texture texture;
	private Tile startTile;
	private boolean first = true, alive = true;
	private TileGrid grid;
	private ArrayList<Checkpoint> checkpoints;
	private int[] direction;
	
	public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, int health, float speed) {
		this.texture = texture;
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.health = health;
		this.speed = speed;
		this.grid = grid;
		
		this.checkpoints = new ArrayList<Checkpoint>();
		this.direction = new int[2];
		// x - direction
		this.direction[0] = 0;
		// y - direction
		this.direction[1] = 1;
		
		this.direction = FindNextD(startTile);
		this.currentCheckpoint = 0;
		PopulateCheckPointList();
		
	}
	
	private Checkpoint FindNextC(Tile s, int[] dir){
		Tile next = null;
		Checkpoint c = null;
		
		boolean found = false;
		int counter = 1;
		
		while(!found){
			if(s.getXPlace() + dir[0] * counter == grid.getTileWide() || 
					s.getYPlace() + dir[1] * counter == grid.getTileHeight()||
					
					s.getTileType() != 
					grid.GetTile(s.getXPlace() + dir[0] * counter, 
							s.getYPlace() + dir[1] * counter).getTileType()){
				found = true;
				counter -= 1;
				next = grid.GetTile(s.getXPlace() + dir[0] * counter, 
						s.getYPlace() + dir[1] * counter);
			}
			counter++;
		}
		c = new Checkpoint(next, dir[0], dir[1]);
		//checkpoints.add(c);
		return c;
	}
	
	private int[] FindNextD(Tile s){
		int[] dir = new int[2];
		Tile u = grid.GetTile(s.getXPlace(), s.getYPlace() - 1);
		Tile r = grid.GetTile(s.getXPlace() + 1, s.getYPlace());
		Tile d = grid.GetTile(s.getXPlace(), s.getYPlace() + 1);
		Tile l = grid.GetTile(s.getXPlace() - 1, s.getYPlace());
		
		if (s.getTileType() == u.getTileType() && direction[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		}else if (s.getTileType() == r.getTileType() && direction[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		}
		else if (s.getTileType() == d.getTileType() && direction[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		}else if (s.getTileType() == l.getTileType() && direction[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		}else{
			dir[0] = 2;
			dir[1] = 2;
		}
		
		return dir;
	}
	
	/*private boolean pathContinues(){
		boolean answear = true;
		
		Tile myTile = grid.GetTile((int) (x / 64), (int) (y / 64));
		Tile nextTile = grid.GetTile((int) (x / 64) + 1, (int) (y / 64));
		if(myTile.getTileType() != nextTile.getTileType()){
			answear = false;
		}
		return answear;
	}*/
	
	public void update(){
		if(first){
			first = false;
		}else{
			if(CheckpointReached()){
				if(currentCheckpoint + 1 == checkpoints.size()){
					Die();
					
				}else{
					currentCheckpoint++;					
				}
			}else{		
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirextion() * speed;							
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirexction() * speed;																				
			}												
		}
	}
	
	private boolean CheckpointReached(){
		boolean reached = false;
		
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		if(x > t.getX() - 3 && x < t.getX() + 3 &&
				y > t.getY() - 3 &&	y < t.getY() + 3){
			reached = true;	
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}
	
	private void PopulateCheckPointList(){
		checkpoints.add(FindNextC(startTile, direction = FindNextD(startTile)));
		
		int counter = 0;
		boolean cont = true;
		
		while(cont){
			int[] currentD = FindNextD(checkpoints.get(counter).getTile());
			
			if(currentD[0] == 2 || counter == 20){
				cont = false;
			}else{
				checkpoints.add(FindNextC(checkpoints.get(counter).getTile(), 
						direction = FindNextD(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
	}
	
	public void damage(int amount){
		health -= amount;
		if(health <=0){
			Die();
		}
	}
	
	private void Die(){
		alive = false;
	}
	
	public void draw(){
		DrawQuadTex(texture, x, y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileGrid getGrid() {
		return grid;
	}

	public void setGrid(TileGrid grid) {
		this.grid = grid;
	}

	public boolean isAlive() {
		return alive;
	}

	
}
