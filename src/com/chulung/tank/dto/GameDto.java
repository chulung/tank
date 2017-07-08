package com.chulung.tank.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chulung.tank.entity.Map;
import com.chulung.tank.control.BonusControl.Bonus;
import com.chulung.tank.entity.Bullet;
import com.chulung.tank.entity.Tank;

/**
 * 
 * @说明 界面与控制的数据传输层
 * @作者 chulung
 * @创建时间 2014年2月7日 下午5:30:46
 * @遗留问题
 */
public class GameDto {
	/**
	 * 关数
	 */
	public static int pass;
	/**
	 * 击杀
	 */
	public static int kill;
	/**
	 * 玩家坦克
	 */

	private Tank playerTank;

	/**
	 * 敌人坦克集合
	 */
	private List<Tank> enemyTanks;

	/**
	 * 子弹集合
	 */

	private List<Bullet> bullets;
	/**
	 * 游戏地图
	 */
	private Map map;

	/**
	 * 积分 累计到2^n 次方 玩家为n级 死亡归零
	 */
	private int score;
	
	/**
	 * 奖励
	 */
	private Bonus bonus;

	/**
	 * 暂停标记
	 */
	private static boolean isPause = true;

	/**
	 * 是否暂停
	 * 
	 * @return
	 */
	public static boolean isPause() {
		return isPause;
	}

	/**
	 * 改变暂停标记
	 */
	public static void ChangePause() {
		isPause = !isPause;
	}

	static {
		pass = 1;
	}

	public GameDto() {
		enemyTanks = new ArrayList<Tank>();
		enemyTanks = Collections.synchronizedList(enemyTanks);
		bullets = new ArrayList<Bullet>();
		bullets = Collections.synchronizedList(bullets);
	}

	/**
	 * 添加坦克
	 */
	public void addTank(Tank tank) {
		if (tank.getIff() == Tank.IFFPLAYER) {
			playerTank = tank;
		} else {
			enemyTanks.add(tank);

		}
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public Object[] getButtles() {
		return bullets.toArray();
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int[][] getSimpleMap() {
		return map.getSimpleMap();
	}

	public Tank getPlayerTank() {
		return playerTank;
	}

	public List<Tank> getEnemyTank() {
		return enemyTanks;
	}

	public void removeTank(Tank tank) {
		enemyTanks.remove(tank);
	}

	public void addPlayerScore(int level) {
		kill++;
		score += level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public Bonus getBonus() {
		return bonus;
	}
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

}
