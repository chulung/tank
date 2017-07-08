package com.chulung.tank.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.chulung.tank.config.LayerConfig;
import com.chulung.tank.config.UIConfig;
import com.chulung.tank.control.PlayerControl;
import com.chulung.tank.dto.GameDto;
import com.chulung.tank.factory.ConfigFactory;

/**
 * 
 * @说明 游戏画面层
 * @作者 chulung
 * @创建时间 2014年2月5日 下午7:28:05
 * @遗留问题
 */
public class GamePane extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private GameDto gameDto;
	/**
	 * 层集合
	 */
	private List<Layer> layers;

	/**
	 * 初始化
	 * 
	 * @throws Exception
	 */
	public GamePane(GameDto gameDto) {
		this.gameDto=gameDto;
		this.setLayout(null);
		try {
			initLayer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设置玩家控制器
	 * 
	 * @param control
	 */
	public void setControl(PlayerControl control) {
		this.addKeyListener(control);
	}

	/**
	 * 初始化画面
	 * 
	 * @throws Exception
	 */
	
	private void initLayer() throws Exception {
		// 获取游戏配置
		UIConfig gameConfig = ConfigFactory.getGameConfig();
		// 获取层配置
		List<LayerConfig> layerConfigs = gameConfig.getLayerConfigs();
		// 层对象集合
		layers = new ArrayList<Layer>();
		// 创建层对象
		for (LayerConfig layerConfig : layerConfigs) {
			// 新实例
			Class<?> cls = Class.forName(layerConfig.getClassName());
			Constructor<?> constructor = cls.getConstructor(int.class,
					int.class, int.class, int.class, GameDto.class);
			Layer layer = (Layer) constructor.newInstance(layerConfig.getX(),
					layerConfig.getY(), layerConfig.getWidth(),
					layerConfig.getHeight(), gameDto);
			layers.add(layer);
		}

	}

	/**
	 * 绘制方法
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// TODO 背景
		Image image = Img.backGroudImages.firstElement();
		g.drawImage(image, 0, 0, ConfigFactory.getGameConfig().getWidth(),
				ConfigFactory.getGameConfig().getHeight(), null);
		// 绘制画面
		for (Layer layer : layers) {
			layer.paint(g);
		}
		requestFocus();
	}
	

	/**
	 * 子线程开始刷新窗口
	 */
	public void startRepaint() {
		Thread repaintThread = new Thread(this);
		repaintThread.start();
	}

	/**
	 * 重绘
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(33);
				if (GameDto.isPause()) {
					continue;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
