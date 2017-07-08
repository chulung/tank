package com.chulung.tank.config;

/**
 * 坦克配置信息
 * @author chulung
 *
 */
public class TankConfig {
	private String type;
	private int size;
	private int level;
	private int health;
	private int bullet;
	private int speed;
	private int bulletCount;

	/**
	 * @param name
	 *            型号
	 * @param size
	 *            大小
	 * @param level
	 *            等级
	 * @param health
	 *            生命
	 * @param bullet
	 *            子弹
	 * @param speed
	 *            速度
	 * @param bulletCount 子弹数
	 */
	public TankConfig(String type, int size, int level, int health, int bullet,
			int speed, int bulletCount) {
		this.type = type;
		this.size = size;
		this.level = level;
		this.health = health;
		this.bullet = bullet;
		this.speed = speed;
		this.bulletCount = bulletCount;
	}

	public String getType() {
		return type;
	}

	public int getSize() {
		return size;
	}

	public int getLevel() {
		return level;
	}

	public int getHealth() {
		return health;
	}

	public int getBullet() {
		return bullet;
	}

	public int getSpeed() {
		return speed;
	}

	public int getBulletCount() {
		return bulletCount;
	}

}
