package com.chulung.tank.control;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.util.Observer;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.entity.Bullet;
import com.chulung.tank.service.GameService;
import com.chulung.tank.util.ThreadPool;
import com.chulung.tank.entity.Tank;
import com.chulung.tank.factory.BulletFactory;

/**
 * 坦克控制抽象类实现了开火等方法，抽象创建坦克等方法，
 * @author chulung
 *
 */
public abstract class TankControl extends KeyAdapter implements  Observer {

	/**
	 * 开火间隔
	 */
	public static final int RELOAD_TIME = 200;

	/**
	 * 游戏业务层
	 */
	protected GameService gameService;
	/**
	 * 坦克
	 */
	protected Tank tank;

	/**
	 * 再装填弹药
	 */
	protected boolean canFire = true;

	/**
	 * 死亡标记
	 */
	protected boolean deathFlag = true;

	public TankControl(GameService gameService) {
		this.gameService = gameService;
	}

	/**
	 * 移动方法
	 */
	public void moving() {
		// 获取移动速度方向
		Point move = tank.getMove();
		// 速度
		int speed = tank.getSpeed();
		for (int i = 0; i < speed; i++) {
			if (GameDto.isPause()) {
				continue;
			}
			Point p = new Point(tank.getPoint().x + move.x, tank.getPoint().y
					+ move.y);
			if (!gameService.tankMove(p))
				return;
			tank.move(p);

		}
	}

	/**
	 * 开火方法
	 */
	public void fire() {
		if (GameDto.isPause()) {
			return;
		}
		if (!deathFlag && canFire) {
			int bulletCount = this.tank.getBulletCount();
			if (bulletCount > 0) {
				bulletCount--;
				this.tank.setBulletCount(bulletCount);
				Bullet bullet = BulletFactory.creatNewBullet(
						this.tank.getBullet(), this.tank.getPoint(),
						this.tank.getMove(), this.tank.getIff());
				new BulletControl(this.tank, bullet, gameService);
			}
			canFire = false;
			// 定时上弹
			ThreadPool.executors.execute(new Reload());
		}
	}
	
	/**
	 * 创建坦克
	 */
	protected abstract void createTank();

	/**
	 *  负责上弹的线程
	 * @author chulung
	 *
	 */
	class Reload implements Runnable {

		/**
		 * 上弹的线程
		 */
		public Reload() {
		}

		public void run() {
			try {
				Thread.sleep(RELOAD_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canFire = true;
		};
	}
}
