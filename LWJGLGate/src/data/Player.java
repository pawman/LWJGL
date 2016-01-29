package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

import static helpers.Artist.*;

import java.util.ArrayList;
public class Player {
	private TileGrid grid;
	private WaveManager waveManager;
	private ArrayList<TowerCannon> towerList;
	private boolean leftMouseButtonDown;
	
	
	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<TowerCannon>();
		this.leftMouseButtonDown = false;
	}
	
	public void Update(){
		for (TowerCannon towerCannon : towerList) {
			towerCannon.update();
			towerCannon.updateEnemyLists(waveManager.getCurrentWave().getEnemyList());
		}
		
		if(Mouse.isButtonDown(0) && leftMouseButtonDown){
			towerList.add(new TowerCannon(QuickLoad("cannonBase"), grid.GetTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64)), 20, 1000, waveManager.getCurrentWave().getEnemyList()));
		}
		
		leftMouseButtonDown = Mouse.isButtonDown(0);
		
		//
		while (Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
				Clock.ChangeMultiplayer(0.2f);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
				Clock.ChangeMultiplayer(-0.2f);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()){
				
				towerList.add(new TowerCannon(QuickLoad("cannonBase"), grid.GetTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64)), 30, 1000, waveManager.getCurrentWave().getEnemyList()));
			}
		}
	}
	

	
}
