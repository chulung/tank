package com.chulung.tank.entity;

import java.awt.Point;


import com.chulung.tank.config.TankConfig;

/**
 * 
 * @说明 坦克实体
 * @作者 chulung
 * @创建时间 2014年2月7日 下午8:32:48
 * @遗留问题
 */
public class Tank{
	
	/**
	 * 敌我识别标记 玩家
	 */
	public static final int IFFPLAYER=1;
	
	/**
	 * 敌我识别标记 敌人
	 */
	public static final int IFFENEMY=-1;
	/**
	 * 方向向上
	 */
	public static final int UP = 0;
	/**
	 * 方向向右
	 */
	public static final int RIGHT = 1;
	/**
	 * 方向向下
	 */
	public static final int DOWN = 2;
	/**
	 * 方向向左
	 */
	public static final int LEFT = 3;
	/**
	 * 向上移
	 */
	public static final Point MOVEUP;
	/**
	 * 向右移
	 */
	public static final Point MOVERIGHT;
	/**
	 * 向下移
	 */
	public static final Point MOVEDOWN;
	/**
	 * 向左移
	 */
	public static final Point MOVELEFT;
	
	
	static {
		MOVEUP = new Point(0, -1);
		MOVERIGHT = new Point(1, 0);
		MOVEDOWN = new Point(0, 1);
		MOVELEFT = new Point(-1, 0);
	}
	private String type;
	private int size;
	private int level;
	private int Maxhealth;
	private int health;
	private int bulletType;
	private int speed;
	private int bulletMaxCount;
	private int bulletCount;
	private Point point;
	private int iff;
	/**
	 * 方向 1上2右3下4左
	 */
	private int direction;

	/**
	 * 
	 * @param tankConfig
	 *            坦克配置
	 * @param point
	 *            位置
	 * @param direction
	 *            方向 0上1右2下3左 
	 * @param iff   敌我识别标记
	 */
	public Tank(TankConfig tankConfig, Point point, int direction, int iff) {
		this.type = tankConfig.getType();
		this.size = tankConfig.getSize();
		this.level = tankConfig.getLevel();
		this.health = tankConfig.getHealth();
		this.Maxhealth=health;
		this.bulletType = tankConfig.getBullet();
		this.speed = tankConfig.getSpeed();
		this.bulletCount=tankConfig.getBulletCount();
		this.bulletMaxCount=bulletCount;
		this.point = point;
		this.direction = direction;
		this.iff=iff;
	}

	public void move(Point point) {
		this.point.x = point.x;
		this.point.y = point.y;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getBullet() {
		return bulletType;
	}

	public void setBullet(int bullet) {
		this.bulletType = bullet;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setBulletCount(int bulletCount) {
		this.bulletCount = bulletCount;
	}

	public int getDirection() {
		return direction;
	}

	/**
	 * 
	 * @param direction
	 *            方向 0上1右2下3左
	 */
	public void setDirection(int direction) {
		this.direction = direction;
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

	@Override
	public String toString() {
		return "Tank [type=" + type + ", size=" + size
				+ ", level=" + level + ", health=" + health + ", bullet="
				+ bulletType + ", speed=" + speed + ", point=" + point
				+ ", direction=" + direction + "]";
	}

	public int getBulletCount() {
		return bulletCount;
	}

	public Point getMove() {
		
		if (direction==UP) {
			return MOVEUP;
		}
		if (direction==LEFT) {
			return MOVELEFT;
		}
		if (direction==DOWN) {
			return MOVEDOWN;
		}
		return MOVERIGHT;
	}
	
	/**
	 * 添加子弹
	 */
	public void addBullet(){
		//子弹不能超过上限
		bulletCount=(bulletCount>=bulletMaxCount)?bulletMaxCount:++bulletCount;
	}
	

	public int getIff() {
		return iff;
	}
	
	/**
	 * 中弹
	 * @param firePower 子弹威力
	 */
	public void hit(int firePower) {
		this.health=(health-firePower)>0?(health-firePower):0;
	}
	
	/**
	 * 升级坦克
	 * @param tankConfig
	 */
	public void upgradeTank(TankConfig tankConfig) {
		this.type = tankConfig.getType();
		this.size = tankConfig.getSize();
		this.level = tankConfig.getLevel();
		this.Maxhealth = tankConfig.getHealth();
		//回复血
		this.health=health+25>Maxhealth?Maxhealth:health+25;
		this.bulletType = tankConfig.getBullet();
		this.speed = tankConfig.getSpeed();
		this.bulletCount=tankConfig.getBulletCount();
		this.bulletMaxCount=bulletCount;
	}

	public int getMaxhealth() {
		return Maxhealth;
	}
	
}
