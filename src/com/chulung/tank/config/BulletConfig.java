package com.chulung.tank.config;

/**
 * 
 * @说明 子弹配置 
 * @作者 chulung
 * @创建时间 2014年2月8日 上午11:47:36
 * @遗留问题
 */
public class BulletConfig {
	private int size;
	private String imgpath;
	private int speed;
	private int firePower;
	/**
	 * 
	 * @param size 大小
	 * @param imgpath 图片路径
	 * @param speed 速度
	 * @param firePower 火力
	 */
	public BulletConfig(int size, String imgpath, int speed, int firePower) {
		super();
		this.size = size;
		this.imgpath = imgpath;
		this.speed = speed;
		this.firePower = firePower;
	}


	public int getSize() {
		return size;
	}

	public String getImgpath() {
		return imgpath;
	}


	public int getSpeed() {
		return speed;
	}


	public int getFirePower() {
		return firePower;
	}

}
