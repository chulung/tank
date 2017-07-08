package com.chulung.tank.service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.entity.Map;
import com.chulung.tank.factory.TankFactory;
import com.chulung.tank.control.GameControl;
import com.chulung.tank.control.BonusControl.Bonus;
import com.chulung.tank.entity.Bullet;
import com.chulung.tank.entity.Tank;

/**
 * 
 * @说明 游戏业务逻辑
 * @作者 chulung
 * @创建时间 2014年2月7日 上午10:35:21
 * @遗留问题
 */
public class GameService extends Observable {
	private GameDto gameDto;
	
	private GameControl gameControl;

	private Map map;

	public GameService(GameControl gameControl, GameDto gameDto) {
		this.gameControl=gameControl;
		this.gameDto = gameDto;
	}

	public void addTank(Tank tank) {

		gameDto.addTank(tank);
	}

	/**
	 * 判断坦克是否移动
	 * 
	 * @param point
	 *            移动位置
	 * @return boolean
	 */
	public synchronized boolean tankMove(Point point) {
		// TODO 可以优化 只判断前进方向
		// TODO 硬编码
		if ((point.x <= 32 || point.x >= 420)
				|| (point.y <= 64 || point.y >= 452)) {
			return false;
		}
		// 详细地图数组
		int[][] mapArray = map.getDetaiedMap();
		for (int i = point.x - 32; i < point.x - 32 + 28; i++) {
			for (int j = point.y - 64 - 1; j < point.y - 64 + 28 - 1; j++) {
				// 地图的xy坐标与数组的xy相反 路或伪装
				if (mapArray[j][i] != 0 && mapArray[j][i] != 9) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 向数据层添加子弹
	 * 
	 * @param bullet
	 */
	public void addBullet(Bullet bullet) {
		gameDto.addBullet(bullet);
	}

	/**
	 * 数据层移除子弹
	 * 
	 * @param bullet
	 */
	public void removeBullet(Bullet bullet) {
		gameDto.removeBullet(bullet);
	}

	/**
	 * 子弹移动
	 * 
	 * @param bullet
	 *            位置
	 * @return 是否移动
	 */
	public synchronized boolean bulletMove(Bullet bullet) {
		boolean flag=true;
		// TODO 可以优化 只判断前进方向
		Point point = bullet.getPoint();
		// TODO 硬编码 //越界
		if ((point.x < 32 || point.x > 448 - 6)
				|| (point.y < 64 || point.y > 480 - 6)) {
			flag= false;
		}

		// 详细地图数组
		int[][] mapArray = map.getDetaiedMap();
		// 障碍物处理  TODO 数组越界
		for (int i = point.x - 31; i < point.x - 31 + 4; i++) {
			for (int j = point.y - 63; j < point.y - 63 + 4; j++) {
				// 地图的xy坐标与数组的xy相反
				// 不是路水伪装则碰撞
				if (mapArray[j][i] != 0 && mapArray[j][i] != 8
						&& mapArray[j][i] != 9) {
					// 为土墙 或石墙则依据子弹威力处理 /32得到简单地图坐标
					map.damageTile(j / 32, i / 32, bullet.getFirePower());
					flag= false;
				}
			}
		}
		// TODO 击中坦克判断 逻辑处理可优化
		Tank playertank = gameDto.getPlayerTank();
		List<Tank> tanks =new ArrayList<Tank>(gameDto.getEnemyTank()) ;
		tanks.add(playertank);
		if (hitJudge(bullet, tanks)) {
			flag= false;
		}
		if(!flag){
			//移除子弹
			this.removeBullet(bullet);
		}
		return flag;
	}
	
	

	/**
	 * 击中坦克判断
	 * 
	 * @param bullet
	 * @param tanks
	 */
	private boolean hitJudge(Bullet bullet, List<Tank> tanks) {
		for (Tank tank : tanks) {
			Point point = bullet.getPoint();
			Point tankPoint = tank.getPoint();
			//TODO 数字硬编码
			if (bullet.getIff() != tank.getIff()
					&& (point.x - tankPoint.x > -3 && point.x - tankPoint.x < 31)
					&& (point.y - tankPoint.y > -3 && point.y - tankPoint.y < 31)) {
				tank.hit(bullet.getFirePower());
				//设置子弹击中观测点
				setChanged();
				//通知观察者
				notifyObservers(tank);
				return true;
			}
		}
		return false;
	}

	public void setMap(Map map) {
		this.map = map;
		gameDto.setMap(map);
	}

	public void removeTank(Tank tank) {
		gameDto.removeTank(tank);
	}
	
	
	public void addPlayerScore(int addScore) {
		gameDto.addPlayerScore(addScore);
		//升级判断
		int score=gameDto.getScore();
		int level=0;
		while (score!=0) {
			score=score>>1;
			level++;
		}
		if (level>gameDto.getPlayerTank().getLevel()) {
			TankFactory.upgradeTank(gameDto.getPlayerTank());
		}
	}

	public void setScore(int i) {
		gameDto.setScore(i);
	}

	public Tank getPlayerTank() {
		return gameDto.getPlayerTank();
	}
	
	/**
	 * 死亡惩罚
	 */
	public void deathPenalty() {
		// 关数减1
		int pass = GameDto.pass;
		GameDto.pass = pass > 1 ? --pass : pass;
	}
	
	/**
	 * 玩家胜利
	 */
	public void playerWin() {
		GameDto.pass++;
		gameControl.initGame();
	}

	public void setBonus(Bonus bonus) {
		gameDto.setBonus(bonus);
	}
}
