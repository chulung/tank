package com.chulung.tank.control;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.entity.Tank;
import com.chulung.tank.factory.TankFactory;
import com.chulung.tank.service.GameService;
import com.chulung.tank.util.SetLinkList;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;

/**
 * 玩家控制层
 * @author chulung
 *
 */
public class PlayerControl extends TankControl {

	private static final int UP = KeyEvent.VK_W;
	private static final int RIGHT = KeyEvent.VK_D;
	private static final int DOWN = KeyEvent.VK_S;
	private static final int LEFT = KeyEvent.VK_A;
	private static final int PAUSE = KeyEvent.VK_ENTER;
	private static final int FIRE = KeyEvent.VK_SPACE;

	/**
	 * 记录移动方位
	 */
	private SetLinkList<Integer> movePoints;

	public PlayerControl(GameService gameService) {
		super(gameService);
		movePoints = new SetLinkList<Integer>();
		movePoints.get();
		createTank();
		gameService.addTank(tank);
		new MoveControl().start();
	}
	
	/**
	 * 键盘控制
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case UP:
			movePoints.remove(new Integer(Tank.UP));
			break;
		case RIGHT:
			movePoints.remove(new Integer(Tank.RIGHT));
			break;
		case DOWN:
			movePoints.remove(new Integer(Tank.DOWN));
			break;
		case LEFT:
			movePoints.remove(new Integer(Tank.LEFT));
			break;
		default:
			break;
		}
	}

	/**
	 * 键盘按下事件
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case UP:
			moveup();
			break;
		case RIGHT:
			moveright();
			break;
		case DOWN:
			movedown();
			break;
		case LEFT:
			moveleft();
			break;
		case FIRE:
			fire();
			break;
		case PAUSE:
			GameDto.ChangePause();
			break;
		case KeyEvent.VK_F1:
			gameService.addPlayerScore(1);
			break;
		default:
			break;
		}

	}

	private void moveleft() {
		if (GameDto.isPause()) {
			return;
		}
		movePoints.add(new Integer(Tank.LEFT));
	}

	private void movedown() {
		if (GameDto.isPause()) {
			return;
		}
		movePoints.add(new Integer(Tank.DOWN));
	}

	private void moveright() {
		if (GameDto.isPause()) {
			return;
		}
		movePoints.add(new Integer(Tank.RIGHT));
	}

	private void moveup() {
		if (GameDto.isPause()) {
			return;
		}
		movePoints.add(new Integer(Tank.UP));
	}

	/**
	 * 移动方法
	 */
	public void moving() {
		Integer direction = movePoints.get();
		if (direction == null) {
			return;
		}
		tank.setDirection(direction.intValue());
		Point move = tank.getMove();
		int speed = tank.getSpeed();
		for (int i = 0; i < speed; i++) {
			Point p = new Point(tank.getPoint().x + move.x, tank.getPoint().y
					+ move.y);
			if (!gameService.tankMove(p))
				return;
			tank.move(p);

		}
	}

	/**
	 * 响应击中
	 */
	public void update(Observable o, Object arg) {
		if (arg == tank && tank.getHealth() <= 0) {
			this.deathFlag = true;
			// 死了则新建
			createTank();

			// 分数清零
			gameService.setScore(0);
			gameService.deathPenalty();

		}
	}

	/**
	 * 创建坦克
	 */
	public void createTank() {
		if (!deathFlag) {
			tank.setPoint(new Point(TankFactory.PLAYER_POINT));
			tank.setDirection(Tank.UP);
			return;
		}
		tank = TankFactory.ctreatNewPalyerTank();
		deathFlag = false;
		gameService.addTank(tank);
	}

	/**
	 * 
	 * @说明 玩家坦克移动控制
	 * @作者 chulung
	 * @创建时间 2014年2月11日 上午10:13:25
	 * @遗留问题
	 */
	class MoveControl extends Thread {
		public void run() {

			try {
				while (true) {
					Thread.sleep(33);
					if (GameDto.isPause()) {
						continue;
					}
					moving();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
