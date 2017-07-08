package com.chulung.tank.config;

/**
 * 障碍物 
 * @author chulung
 *
 */
public class TileConfig {
	private int size;
	private String imgpath;
	private int health;
	
	/**
	 * 
	 * @param size 大小
	 * @param imgpath 图片路径
	 * @param health 生命
	 */
	public TileConfig(int size, String imgpath, int health) {
		super();
		this.size = size;
		this.imgpath = imgpath;
		this.health = health;
	}

	public int getSize() {
		return size;
	}

	public String getImgpath() {
		return imgpath;
	}

	public int getHealth() {
		return health;
	}
	
	
}
