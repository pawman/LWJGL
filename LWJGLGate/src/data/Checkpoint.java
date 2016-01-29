package data;

public class Checkpoint {
	private Tile tile;
	private int xDirextion, yDirexction;
	
	
	public Checkpoint(Tile tile, int xDirextion, int yDirexction) {
		this.tile = tile;
		this.xDirextion = xDirextion;
		this.yDirexction = yDirexction;
	}


	public Tile getTile() {
		return tile;
	}


	public int getxDirextion() {
		return xDirextion;
	}


	public int getyDirexction() {
		return yDirexction;
	}
	
	
	
}
