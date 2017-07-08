package com.chulung.tank.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.entity.Bullet;
import com.chulung.tank.control.BonusControl.Bonus;
import com.chulung.tank.entity.Tank;

/**
 *  游戏主面板层，
 * @author chulung
 *
 */
public class LayerGame extends Layer {

	public LayerGame(int x, int y, int width, int height, GameDto gameDto) {
		super(x, y, width, height, gameDto);
	}

	public void paint(Graphics g) {
		// 创建边界
		createBorder(g);
		if (GameDto.isPause()) {
			return;
		}
		// 绘制地图
		paintMap(g);
		// 绘制子弹
		paintBullte(g);
		// 绘制坦克
		paintTanks(g);
		// 最后绘制伪装网
		paintDefilad(g);
		//绘制奖励
		paintBonus(g);
	}
	
	/**
	 * 绘制奖励
	 * @param g
	 */
	private void paintBonus(Graphics g) {
	//FIXME
		Bonus bonus=gameDto.getBonus();
	}

	/**
	 * //FIXME 代码冗余 绘制伪装网
	 * 
	 * @param g
	 */
	private void paintDefilad(Graphics g) {
		int[][] map = gameDto.getSimpleMap();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				// TODO 硬编码 0路9 伪装网
				if (map[i][j] != 9) {
					continue;
				}
				Image image = Img.tileImages.get(map[i][j] - 1);
				int x2 = this.x + (i + 1) * 32;
				int y2 = this.y + (j - 1) * 32;
				// 地图的xy坐标与数组的xy相反
				g.drawImage(image, y2, x2, null);
			}
		}
	}

	/**
	 * 绘制地图
	 * 
	 * @param g
	 */
	private void paintMap(Graphics g) {
		int[][] map = gameDto.getSimpleMap();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				// TODO 硬编码 0路 4 伪装网
				if (map[i][j] == 0 || map[i][j] == 9) {
					continue;
				}
				Image image = Img.tileImages.get(map[i][j] - 1);
				int x2 = this.x + (i + 1) * 32;
				int y2 = this.y + (j - 1) * 32;
				// 地图的xy坐标与数组的xy相反
				g.drawImage(image, y2, x2, null);
			}
		}
	}

	/**
	 * 绘制子弹
	 * 
	 * @param g
	 */
	private void paintBullte(Graphics g) {
		Object[] bullets = gameDto.getButtles();
			for (Object bullet : bullets) {
				Image image = Img.bulletImage;
				Point point = new Point(((Bullet) bullet).getPoint());
				int size = ((Bullet) bullet).getSize();
				g.drawImage(image, point.x, point.y, point.x + size, point.y
						+ size, 0, 0, size, size, null);
		}
	}

	/**
	 * 绘制全部坦克
	 * 
	 * @param g
	 */
	private void paintTanks(Graphics g) {
		Tank playerTank = gameDto.getPlayerTank();
		Image image = Img.playerImages.get(playerTank.getLevel() - 1);
		//绘制玩家
		paintTank(g, playerTank, image);
		Object[] enemyTanks = gameDto.getEnemyTank().toArray();
		//绘制敌人
		for (Object o : enemyTanks) {
			Tank tank=(Tank)o;
			image = Img.enemyImages.get(tank.getLevel() - 1);
			paintTank(g, tank, image);
		}
	}

	/**
	 * 绘制一辆坦克
	 * @param g
	 * @param tank
	 * @param image
	 */
	private void paintTank(Graphics g, Tank tank, Image image) {
		// TODO 硬编码
		int TankSize = 28;
		Point point = tank.getPoint();
		int direction = tank.getDirection();
		//绘制坦克
		g.drawImage(image, point.x, point.y, point.x + TankSize, point.y
				+ TankSize, 0, direction * TankSize, TankSize, direction
				* TankSize + TankSize, null);
		//显示生命
		g.setColor(Color.RED);
		String hp=tank.getHealth()+"/"+tank.getMaxhealth();
		g.drawString(hp, point.x, point.y);

	}

}
