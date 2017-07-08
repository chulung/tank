package com.chulung.tank.util;

import java.util.Random;
/**
 * 
 * @说明 粗糙的随机地图生成
 * @作者 chulung
 * @创建时间 2014年2月11日 下午4:47:44
 * @遗留问题
 */
public class RandomMap {
	public static int [][] randomMap() {
		int[][] map = new int[13][13];
		Random random = new Random();
		for (int i = 1; i < 12; i++) {
			for (int j = 0; j < 13; j++) {
				map[i][j] = random.nextInt(100) > 85 ? 9
						: random.nextInt(100) > 90 ? 8
								: random.nextInt(100) > 80 ? 7 : random
										.nextInt(100) > 40 ? 3 : 0;
			}
		}
		return map;
	}
}
