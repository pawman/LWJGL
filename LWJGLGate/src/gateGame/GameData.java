package gateGame;

import java.util.ArrayList;
import java.util.List;

import data.TileGrid;

public class GameData {
	private static List<Ship> ships;
	private static List<Planet> planets;
	private static List<Bulett> buletts;
	private static TileGrid grid;
	
	//temp variable
	
	private static int idShip = 0;
	private static int idPlanet = 0;
	
	
	public GameData() {
		System.out.println("Welcom to LWJGL");
		
		ships = new ArrayList<>();
		planets = new ArrayList<>();
		buletts = new ArrayList<>();
		grid = new TileGrid();
	}

	public static List<Ship> getShips() {
		return ships;
	}
	
	public static void createShip(Owner owner, float x, float y) {
		ships.add(new ShipImp(x, y, 32, 32, owner.getName() + idShip));
		idShip++;
	}


	public static List<Planet> getPlanets() {
		return planets;
	}

	public boolean createPlanet(Owner owner, float x, float y){
		for (Planet planet : planets) {
			if(planet.getX() == x && planet.getY() == y){
				return false;
			}
		}
		planets.add(new PlanetImp(owner.getName()+ idPlanet, x, y, 32, 32));
		idPlanet++;
		return true;
	}

	public static List<Bulett> getBuletts() {
		return buletts;
	}

	public static void addBulett(Bulett bulett) {
		GameData.buletts.add(bulett);
	}

	public static TileGrid getGrid() {
		return grid;
	}

	
	public static void update(){
		grid.Draw();
		for (Planet planet : planets) {
			planet.update();
		}
		
		for (Ship ship : ships) {
			ship.update();
		}
	
	}
	
}
