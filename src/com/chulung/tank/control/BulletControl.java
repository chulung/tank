package com.chulung.tank.control;

import java.awt.Point;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.entity.Bullet;
import com.chulung.tank.service.GameService;
import com.chulung.tank.util.ThreadPool;
import com.chulung.tank.entity.Tank;

/**
 * 子弹控制器
 * @author chulung
 *
 */
public class BulletControl implements Runnable{
	private boolean isRuning = true;
	private Point move;
	private Bullet bullet;
	private GameService gameService;
	private int speed;
	private Tank tank;
	
	public BulletControl(Tank tank,Bullet bullet, GameService gameService) {
		this.bullet = bullet;
		move = bullet.getMove();
		speed = bullet.getSpeed();
		this.gameService = gameService;
		this.gameService.addBullet(bullet);
		this.tank=tank;
		ThreadPool.executors.execute(this);
	}

	public void run() {
			try {
				while (isRuning) {
				Thread.sleep(33);
				if (GameDto.isPause()) {
					continue;
				}
				for (int i = 0; i < speed && isRuning; i++) {
					moving();
				}
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally{
				tank.addBullet();

			}

	}

	/**
	 * 移动
	 * @return 
	 */
	public void moving() {
		Point point = new Point(bullet.getPoint().x + move.x,
				bullet.getPoint().y + move.y);
		bullet.move(point);
		if (gameService.bulletMove(bullet)){
			return ;
			}
		isRuning=false;
	}
}
