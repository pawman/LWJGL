package data;

public class WaveManager {
	private float timeSinceLastWave, timeBetweenEnemies;
	private int waveNumber, enemiesPerWave;
	private Enemy enemyType;
	private Wave currentWave;
	
	public WaveManager(Enemy enemyType, float timeBetweenEnemies, int enemiesPerWave){
		this.timeSinceLastWave = 0;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.waveNumber = 0;
		this.enemyType = enemyType;
		this.enemiesPerWave = enemiesPerWave;
		
		this.currentWave = null;
		
		newWave();
	}
	
	public void update() {
		if(!currentWave.isWaveCompleted()){
			currentWave.Update();			
		}else{
			newWave();
		}
		
	}
	
	private void newWave() {
		currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
		waveNumber++;
		
	}

	public Wave getCurrentWave() {
		return currentWave;
	}

	public void setCurrentWave(Wave currentWave) {
		this.currentWave = currentWave;
	}
}
