package com.chulung.tank.factory;

import java.awt.Point;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.config.ObjectConfig;
import com.chulung.tank.config.TankConfig;
import com.chulung.tank.entity.Tank;

/**
 * tank工厂
 * @author chulung
 *
 */
public class TankFactory {
	private static Vector<TankConfig> playertTanks;
	private static Vector<TankConfig> enemyTanks;

	/**
	 * 玩家初始位置
	 */
	public static final Point PLAYER_POINT;

	/**
	 * 敌人坦克等级栈
	 */
	private static Stack<Integer> enemyStack;

	/**
	 * 敌人初始位置
	 */
	private static Point[] enemyPonit;

	/**
	 * 敌人剩余数量
	 */
	private static int[] enemyCount;

	static {

		// 物体配置
		ObjectConfig objectConfig = ConfigFactory.getObjectConfig();
		// 玩家坦克配置信息
		playertTanks = objectConfig.getPlayerTanks();
		// 敌人坦克配置信息
		enemyTanks = objectConfig.getEnemyTanks();
		// 敌人初始位置
		enemyPonit = new Point[] { new Point(32 * 2, 64),
				new Point(32 * 6, 64), new Point(32 * 9, 64),
				new Point(32 * 12, 64) };
		PLAYER_POINT = new Point(32 * 5, 32 * 14);

	}

	/**
	 * 创建玩家坦克
	 * 
	 * @return 坦克
	 */
	public static Tank ctreatNewPalyerTank() {
		TankConfig tankConfig = playertTanks.get(0);
		Tank tank = new Tank(tankConfig, new Point(PLAYER_POINT), Tank.UP,
				Tank.IFFPLAYER);
		return tank;
	}

	/**
	 * 创建敌人坦克  存在并发同步
	 * 
	 * @return 坦克
	 */
	public synchronized static Tank ctreatNewEnemyTank() throws EmptyStackException {
		int point = new Random().nextInt(4);
		enemyCount[enemyStack.peek().intValue() - 1] -= 1;
		TankConfig tankConfig = enemyTanks.get(enemyStack.pop().intValue() - 1);
		Tank tank = new Tank(tankConfig, new Point(enemyPonit[point]),
				Tank.DOWN, Tank.IFFENEMY);
		return tank;
	}

	/**
	 * 升级玩家坦克
	 * 
	 * @param tank
	 * @return a new tank
	 */
	public static void upgradeTank(Tank tank) {
		try {
			//等级达到上限异常
			TankConfig tankConfig = playertTanks.get(tank.getLevel());
			tank.upgradeTank(tankConfig);
		} catch (Exception e) {

		}
	}

	/**
	 * 随机敌人类型数量
	 */
	public static void randomEnemy() {
		enemyStack = new Stack<Integer>();
		int pass = GameDto.pass;
		// 修正数量
		int count = 10 + pass / 2;
		Random random = new Random();
		int r1;
		for (int i = 0; i < count; i++) {
			do {
				// 随机敌人等级
				r1 = random.nextInt(9)
						+ (-6 / pass < 0 ? -6 / pass
								: ((pass / 6) < 7 ? (pass / 6) : 7));
			} while (r1 < 1 || r1 > 8);
			enemyStack.push(new Integer(r1));
		}
		enemyCount = new int[8];
		Object[] integer = enemyStack.toArray();
		for (int i = 0; i < integer.length; i++) {
			enemyCount[((Integer) integer[i]).intValue() - 1] += 1;
		}

	}

	public static int[] getEnemyTankCount() {
		return enemyCount;

	}

	public static void main(String[] args) {
		getEnemyTankCount();
	}
}
