package data;


public class TileGrid {
	public Tile[][] map;
	private int tileWide, tileHeight;
	
	public TileGrid(){
		this.tileWide = 20;
		this.tileHeight = 15;
		map = new Tile[20][15];
		
		for (int i =0; i < map.length;i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Grass);
			}
		}
	}
	
	public TileGrid(int[][] newMap){
		this.tileWide = newMap[0].length;
		this.tileHeight = newMap.length;
		map = new Tile[tileWide][tileHeight];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int type = newMap[j][i];
				switch (type) {
				case 0:
					map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Grass);
					break;
				case 1:
					map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Dirt);
					break;
				case 2:
					map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Water);
					break;

				default:
					break;
				}
			}
			
		}
	}
	
	public int getTileWide() {
		return tileWide;
	}

	public void setTileWide(int tileWide) {
		this.tileWide = tileWide;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public void SetTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * 64, yCoord * 64, 64, 64, type);
	}
	
	public Tile GetTile(int xPlace, int yPlace) {
		if(xPlace <tileWide && yPlace < tileHeight && xPlace >= 0 && yPlace >= 0){
			return map[xPlace][yPlace];			
		}else{
			return new Tile(0, 0, 0, 0, TileType.NULL);
		}
	}
	
	public void Draw() {
		for (int i =0; i < map.length;i++) {
			for (int j = 0; j < map[i].length; j++) {
				Tile t= map[i][j];
				t.draw();
			//	DrawQuadTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
			}
		}
		
	}
}
