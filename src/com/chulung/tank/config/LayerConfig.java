package com.chulung.tank.config;

/**
 * 画面层配置
 * @author chulung
 *
 */
public class LayerConfig {
	private String className;

	private int x;

	private int y;

	private int width;

	private int height;

	/**
	 * 
	 * @param className
	 *            画面层类名
	 * @param x
	 *            坐标
	 * @param y
	 *            坐标
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public LayerConfig(String className, int x, int y, int width, int height) {
		this.className = className;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String getClassName() {
		return className;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
