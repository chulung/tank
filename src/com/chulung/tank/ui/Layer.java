package com.chulung.tank.ui;

import java.awt.Graphics;
import java.awt.Image;

import com.chulung.tank.dto.GameDto;

/**
 * 画面框架层
 * @author chulung
 *
 */
public abstract class Layer {
	
	//TODO 换背景
	private static final Image img =Img.borderImages.firstElement();
	private static final int border = Img.borderWidth;
	private static final int imgH = Img.borderImageH;
	private static final int imgW = Img.borderImageW;
	private static final Image viewImg=Img.viewImages.firstElement();
	/**
	 * x坐标
	 */
	protected int x;
	/**
	 * y坐标
	 */
	protected int y;

	/**
	 * 宽
	 */
	protected int width;

	/**
	 * 高
	 */
	protected int height;

	/**
	 * //TODO 基本块大小
	 */
	protected static int pieceSize=32;
	
	/**
	 * 数据传输层
	 */
	protected GameDto gameDto;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Layer(int x, int y, int width, int height, GameDto gameDto) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gameDto=gameDto;
	}
	
	

	
	/**
	 * 创建边框
	 * @param g
	 */
	protected void createBorder(Graphics g) {
		// 左上
		g.drawImage(img, x - border, y - border, x, y, 0, 0, border, border,
				null);
		// 左下
		g.drawImage(img, x - border, y + height, x, y + height + border, 0,
				imgH - border, border, imgH, null);
		// 右上
		g.drawImage(img, x + width, y - border, x + width + border, y, imgW
				- border, 0, imgW, border, null);
		// 右下
		g.drawImage(img, x + width, y + height, x + width + border, y + height
				+ border, imgW - border, imgH - border, imgW, imgH, null);
		// 上
		g.drawImage(img, x, y - border, x + width, y, border, 0, imgW - border,
				border, null);
		// 右
		g.drawImage(img, x + width, y, x + width + border, y + height, imgW
				- border, border, imgW, imgH - border, null);
		// 下
		g.drawImage(img, x, y + height, x + width, y + height + border, border,
				imgH - border, imgW - border, imgH, null);
		// 左
		g.drawImage(img, x - border, y, x, y + height, 0, border, border, imgH
				- border, null);
		//中
		g.drawImage(viewImg, x, y, x + width, y + height, x, y, x + width, y + height ,null);
	}
	
	/**
	 * 绘制方法
	 * @param g
	 */
	public abstract void paint(Graphics g);
}
