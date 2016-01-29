package data;

public enum TileType {
	Grass("grass",true), Dirt("earth",false), Water("water",false), NULL("water",false), BaseTower("cannonBase",false), Gun("cannonGun",false);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable){
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
