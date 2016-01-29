package data;

import java.util.ArrayList;
import static helpers.Clock.*;

public class Wave {
	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private ArrayList<Enemy> enemyList;
	private int enemiesPerWave;
	private boolean waveCompleted;

	public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave) {
		this.spawnTime = spawnTime;
		this.enemyType = enemyType;
		this.enemiesPerWave = enemiesPerWave;
		this.timeSinceLastSpawn = 0;
		this.enemyList = new ArrayList<Enemy>();
		this.waveCompleted = false;
		
		Spawn();
	}

	public void Update() {
		boolean allEnemiesDead = true;
		if(enemyList.size() < enemiesPerWave){
			timeSinceLastSpawn += Delta();
			if (timeSinceLastSpawn > spawnTime) {
				Spawn();
				timeSinceLastSpawn = 0;
			}			
		}

		for (Enemy enemy : enemyList) {
			if (enemy.isAlive()) {
				allEnemiesDead = false;
				enemy.update();
				enemy.draw();
			}
		}
		if (allEnemiesDead) {
			waveCompleted = true;
		}
	}

	private void Spawn() {
		enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getGrid(), 64, 60, 100,
				enemyType.getSpeed()));

	}

	public boolean isWaveCompleted() {
		return waveCompleted;
	}

	public void setWaveCompleted(boolean waveCompleted) {
		this.waveCompleted = waveCompleted;
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
}
