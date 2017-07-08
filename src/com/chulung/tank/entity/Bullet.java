package com.chulung.tank.entity;

import java.awt.Point;


import com.chulung.tank.config.BulletConfig;

/**
 * 
 * @说明 子弹
 * @作者 chulung
 * @创建时间 2014年2月5日 下午8:47:03
 * @遗留问题
 */
public class Bullet {
	private int size;
	private int speed;
	private Point point;
	private Point move;
	/**
	 * 威力
	 */
	private int firePower;
	private int iff;

	/**
	 * @param bulletConfig 子弹配置文件
	 * @param point
	 *            物体位置
	 * @param iff 敌我识别标记
	 * @param move
	 *            运动方向
	 */
	public Bullet(BulletConfig bulletConfig, Point point, Point move, int iff) {
		size = bulletConfig.getSize();
		speed = bulletConfig.getSpeed();
		firePower = bulletConfig.getFirePower();
		this.point=point;
		this.move=move;
		this.iff=iff;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getFirePower() {
		return firePower;
	}

	public Point getPoint() {
		return point;
	}

	@Override
	public String toString() {
		return "Bullet [ size=" + size + ", speed=" + speed
				+ ", firePower=" + firePower + ", point=" + point
				+ ", direction=" + move  + "]";
	}

	public Point getMove() {
		return move;
	}
	
	/**
	 * 子弹移动
	 */
	public void move(Point point){
		this.point.x=point.x;
		this.point.y=point.y;
	}

	public int getIff() {
		return iff;
	}
}
