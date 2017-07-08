package com.chulung.tank.ui;

import java.awt.Image;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.chulung.tank.config.ObjectConfig;
import com.chulung.tank.config.UIConfig;
import com.chulung.tank.factory.ConfigFactory;

/**
 * 
 * @说明 图片素材类
 * @作者 chulung
 * @创建时间 2014年2月9日 上午11:26:12
 * @遗留问题
 */
public class Img {
	

	/**
	 * 背景图片集合
	 */
	public static Vector<Image> backGroudImages;

	/**
	 * 边框图片集合
	 */
	public static Vector<Image> borderImages;

	/**
	 * 边框宽度
	 */
	public static int borderWidth;

	/**
	 * 边框图片宽
	 */
	public static int borderImageW;
	/**
	 * 边框图片高
	 */
	public static int borderImageH;

	/**
	 * 画面层背景
	 */
	public static Vector<Image> viewImages;

	/**
	 * 玩家1坦克图片
	 */
	public static Vector<Image> playerImages;

	/**
	 * 敌人坦克图片
	 */
	public static Vector<Image> enemyImages;

	/**
	 * 方块图片
	 */
	public static Vector<Image> tileImages;

	private static UIConfig gameConfig;

	private static ObjectConfig objectConfig;
	
	public static Image bulletImage=new ImageIcon("image/tank/bullet.gif").getImage();
	
	public static Image charImage=new ImageIcon("image/bg/char.gif").getImage();
	
	public static final Image HealthImage =new ImageIcon("image/tank/health.jpg").getImage();
	
	public static final Image PAUSEIMG=new ImageIcon("image/bg/pause.gif").getImage();
	
	public static final Image BONUS =new ImageIcon("image/reward/health.jpg").getImage();
	
	
	static {
		loadImage();
	}

	/**
	 * 加载图片素材
	 */
	public static void loadImage() {
		// 获取配置信息
		gameConfig = ConfigFactory.getGameConfig();
		objectConfig = ConfigFactory.getObjectConfig();
		// 获取背景
		backGroudImages = new Vector<Image>();
		addImage(backGroudImages, gameConfig.getBgpath());
		// 获取边框图片
		borderWidth = gameConfig.getBorder();
		borderImages = new Vector<Image>();
		addImage(borderImages, gameConfig.getBorderpath());
		borderImageW = borderImages.firstElement().getWidth(null);
		borderImageH = borderImages.firstElement().getHeight(null);
		// 获取画面层背景
		viewImages = new Vector<Image>();
		addImage(viewImages, gameConfig.getViewpath());
		// 获取玩家坦克图片
		playerImages = new Vector<Image>();
		addImage(playerImages, objectConfig.getPlayer1imgPath());
		// 获取敌人坦克图片
		enemyImages = new Vector<Image>();
		addImage(enemyImages, objectConfig.getEnemyimgPath());
		// 获取方块图片
		tileImages=new Vector<Image>();
		addImage(tileImages, objectConfig.getTileimgPath());

	}

	/**
	 * 添加图片至集合
	 * 
	 * @param vector
	 *            集合
	 * @param path
	 *            路径
	 */

	private static void addImage(Vector<Image> vector, String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		String fileName;
		for (File f : files) {
			if ((fileName = f.getName()).matches(".+(\\.gif|\\.jpg)$")) {
				Image img = new ImageIcon(path + fileName).getImage();
				vector.add(img);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(enemyImages.size());
	}
}
