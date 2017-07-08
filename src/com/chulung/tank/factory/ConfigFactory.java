package com.chulung.tank.factory;

import com.chulung.tank.config.ObjectConfig;
import com.chulung.tank.config.UIConfig;
import org.dom4j.DocumentException;

/**
 * 配置工厂
 * @author chulung
 *
 */
public class ConfigFactory {
	private static UIConfig gameConfig;
	private static ObjectConfig objectConfig;

	static {
		try {
			gameConfig = new UIConfig();
			objectConfig = new ObjectConfig();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 游戏配置
	 * 
	 * @return 游戏配置对象
	 */
	public static UIConfig getGameConfig() {
		return gameConfig;
	}
	
	/**
	 * 物体配置
	 * @return 物体配置对象
	 */
	public static ObjectConfig getObjectConfig() {
		return objectConfig;
	}

}
