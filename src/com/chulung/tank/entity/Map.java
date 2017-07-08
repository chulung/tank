package com.chulung.tank.entity;

import com.chulung.tank.factory.ConfigFactory;

/**
 * 
 * @说明 地图数据实体 
 * @作者 chulung
 * @创建时间 2014年2月9日 下午4:13:26
 * @遗留问题
 */
public class Map {
	/**
	 * 方块数
	 */
	private static int simpleMapSize=13;
	/**
	 * 方块大小
	 */
	private static int tileSize;
	/**
	 * 13*13的简单地图  只表示基本方块
	 */
	private int[][] simpleMap;
	
	/**
	 * 416*416的游戏画面地图 精确到像素
	 */
	private int[][] detaiedMap;

	public Map(int[][] simpleMap) {
		this.simpleMap = simpleMap;
		simpleMapSize=simpleMap.length;
		tileSize=ConfigFactory.getObjectConfig().getTileSize();
		detaiedMap= new int[simpleMapSize*tileSize][simpleMapSize*tileSize];
		//初始化详细地图
		initDetaiedMap();
	}
	

	private void initDetaiedMap() {
		for (int i = 0; i < detaiedMap.length; i++) {
			for (int j = 0; j < detaiedMap.length; j++) {
				detaiedMap[i][j]=simpleMap[i/tileSize][j/tileSize];
			}
		}
	}



	public int[][] getSimpleMap() {
		return simpleMap;
	}

	public int[][] getDetaiedMap() {
		return detaiedMap;
	}

	
	/**
	 * 
	 * @param y 纵坐标
	 * @param x 横坐标
	 * @param firePower 子弹威力
	 */
	public void damageTile(int y, int x, int firePower) {
		//TODO 障碍物损毁硬编码
		//摧毁石墙则需要子弹威力大于100
		int damage=simpleMap[y][x]>4?(firePower/100):(firePower/25);
		if (damage==0) {
			return;
		}
		simpleMap[y][x]=simpleMap[y][x]-damage<0?0:simpleMap[y][x]-damage;
		for (int i = 0; i < tileSize; i++) {
			for (int j = 0; j <tileSize; j++) {
				detaiedMap[y*tileSize+i][x*tileSize+j]=simpleMap[y][x];
			}
		}
	}

}
