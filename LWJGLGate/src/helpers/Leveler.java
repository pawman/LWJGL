package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import data.Tile;
import data.TileGrid;
import data.TileType;

public class Leveler {
	
	public static void saveMap(String mapName, TileGrid grid){
		String mapData = "";
		
		for(int i = 0; i < grid.getTileWide(); i++){
			for(int j = 0; j < grid.getTileHeight(); j++){
				mapData += getTileID(grid.GetTile(i, j));
			}
		}
		
		try{
			File file = new File(mapName);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(mapData);
			bufferedWriter.close();
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	public static TileGrid loadMap(String mapName){
		TileGrid grid = new TileGrid();
		
		try{
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(mapName));
			String data = bufferedReader.readLine();
			
			for(int i = 0; i < grid.getTileWide(); i++){
				for(int j = 0; j < grid.getTileHeight(); j++){
					grid.SetTile(i, j, getTileType(data.substring(i * grid.getTileHeight() + j, i * grid.getTileHeight() + j + 1)));
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}
		
		
		return grid;
		
	}
	
	public static TileType getTileType(String ID){
		TileType type = TileType.NULL;
		
		switch (ID) {
		case "0":
			type = TileType.Grass;
			break;
		case "1":
			type = TileType.Dirt;
			break;
		case "2":
			type = TileType.Water;
			break;
		case "3":
			type = TileType.NULL;
			break;
		default:
			break;
		}
		return type;
	}
	
	public static String getTileID(Tile t){
		String ID = "E";
		
		switch (t.getTileType()) {
		case Grass:
			ID = "0";
			break;
		case Dirt:
			ID = "1";
			break;
		case Water:
			ID = "2";
			break;
		case NULL:
			ID = "3";
			break;

		default:
			break;
		}
		
		return ID;
	}
}
