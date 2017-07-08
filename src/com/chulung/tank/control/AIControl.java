package com.chulung.tank.control;

import java.util.EmptyStackException;
import java.util.Observable;
import java.util.Random;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.factory.TankFactory;
import com.chulung.tank.service.GameService;

/**
 * 坦克控制
 * 
 * @author chulung
 * 
 */
public class AIControl extends TankControl implements Runnable {

	/**
	 * 线程循环标记
	 */
	protected boolean isRuing = true;

	/**
	 * 在线电脑数
	 */
	private static int AICounts;

	private Thread thread;

	private static Random random = new Random();

	/**
	 * 
	 * @param gameService
	 *            业务逻辑层
	 */
	public AIControl(GameService gameService) {
		// TODO Auto-generated constructor stub
		super(gameService);
		AICounts++;
		// 启动ai
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * 
	 * 
	 */
	protected void createTank() {

		tank = TankFactory.ctreatNewEnemyTank();
		gameService.addTank(tank);

	}

	public void run() {
		try {
			Thread.sleep(1000);// 准备1s
			while (isRuing) {
				Thread.sleep(33);
				if (GameDto.isPause()) {
					continue;
				}
				if (deathFlag) {
					Thread.sleep(3000);
					try {
						createTank();
					} catch (EmptyStackException e) {
						// 家底打光了
						AICounts--;
						if (AICounts == 0) {
							// 玩家胜利
							gameService.playerWin();
						}
						return;
					}
					deathFlag = false;
				}
				moveAndFire();
			}
		} catch (InterruptedException e) {
		} finally {
			gameService.removeTank(this.tank);
		}

	}

	/**
	 * ai移动和开火控制
	 * 
	 * @throws InterruptedException
	 */
	private void moveAndFire() throws InterruptedException {
		for (int i = random.nextInt(200); i > 0; i--) {
			moving();
			// 装填好开火
			fire();
			Thread.sleep(33);
		}
		tank.setDirection(random.nextInt(4));
	}

	/**
	 * 响应击中
	 */
	public void update(Observable o, Object arg) {
		if (arg == tank && tank.getHealth() == 0) {

			// 死了则移除
			gameService.removeTank(tank);
			// 计算分数
			int score = tank.getLevel() / 2 == 0 ? 1 : tank.getLevel() / 2;
			gameService.addPlayerScore(score);
			deathFlag = true;
		}
	}

	/**
	 * 停止ai
	 */
	public void stopAI() {
		thread.interrupt();
	}

}
