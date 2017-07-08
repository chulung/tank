package com.chulung.tank.control;

import java.awt.Point;
import java.util.*;

import com.chulung.tank.service.GameService;

/**
 * 奖励控制
 * 
 * @author chulung
 * 
 */
public class BonusControl implements Runnable {

	public static final int HEALTH = 0;
	private static final int[] REWARD_TYPE;

	private GameService gameService;

	private Random random;

	static {
		REWARD_TYPE = new int[] { 0 };
	}

	public BonusControl(GameService gameService) {
		this.gameService = gameService;
		random = new Random();
	}

	public static void main(String[] args) {
		new BonusControl(null);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// 每隔10到20s出现奖励
			int sleepTime = random.nextInt(1000) + 1000;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Bonus bonus = new Bonus(this.randomType(), this.randomPoint());
			gameService.setBonus(bonus);
		}
	}

	/**
	 * 随机位置
	 * 
	 * @return
	 */
	private Point randomPoint() {
		Point point;
		// TODO 硬编码
		int x = random.nextInt(416);
		int y = random.nextInt(416);
		point = new Point(x, y);
		return point;
	}

	/**
	 * 随机奖励类型
	 * 
	 * @return
	 */
	private int randomType() {
		return random.nextInt(REWARD_TYPE.length);
	}

	public class Bonus {
		public int bonusType;
		public Point bonusPoint;

		public Bonus(int rewardType, Point rewardPoint) {
			this.bonusType = rewardType;
			this.bonusPoint = rewardPoint;
		}
	}

}
