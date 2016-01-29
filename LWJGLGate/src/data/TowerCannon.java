package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {
	private float x, y, timeSinceLastShoot, firingSpeed, angle;
	private int width, height, damage, range;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted;

	public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
		this.baseTexture = baseTexture;
		this.cannonTexture = QuickLoad("cannonGun");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();
		this.damage = damage;
		this.range = range;
		this.firingSpeed = 3;
		this.timeSinceLastShoot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.target = acquireTarget();
		// this.targeted = false;
		// this.angle = calculateAngle();
	}

	private Enemy acquireTarget() {
		Enemy closet = null;
		float closetDistance = 10000;
		for (Enemy enemy : enemies) {
			if (isInRange(enemy) && findDistance(enemy) < closetDistance) {
				closetDistance = findDistance(enemy);
				closet = enemy;
			}
		}
		if (closet != null) {
			targeted = true;
		}
		return closet;
	}

	private boolean isInRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range) {
			return true;
		} else {
			return false;
		}
	}

	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}

	public void updateEnemyLists(ArrayList<Enemy> newList) {
		enemies = newList;
	}

	public void update() {
		if (!targeted) {
			target = acquireTarget();
		}

		if (target == null || target.isAlive() == false) {
			targeted = false;
		}

		timeSinceLastShoot += Delta();
		if (timeSinceLastShoot > firingSpeed) {
			shoot();
		}

		for (Projectile projectile : projectiles) {
			projectile.update();
		}
		angle = calculateAngle();
		draw();
	}

	private void shoot() {
		timeSinceLastShoot = 0;
		projectiles.add(new Projectile(QuickLoad("bullet"), target, x + Game.TITLE_SIZE / 2, y + Game.TITLE_SIZE / 2,
				32, 32, 900, damage));

	}

	public void draw() {
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}

}
