package com.chulung.tank.config;

import java.util.List;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 物体配置
 * @author chulung
 *
 */
public class ObjectConfig {
	/**
	 * 坦克大小
	 */
	private  int tankSize;
	/**
	 * 障碍物块大小
	 */
	private  int tileSize;
	private Vector<TankConfig> playerTanks;
	private Vector<TankConfig> enemyTanks;
	private Vector<BulletConfig> bulletConfigs;
	private Vector<TileConfig> tileConfigs;
	private String player1imgPath;
	private String enemyimgPath;
	private String tileimgPath;
	
	/**
	 * 
	 * @throws DocumentException
	 */
	public ObjectConfig() throws DocumentException {
		// 读取配置文件
		Document document = new SAXReader().read("common/object.xml");
		// 获取根节点
		Element object = document.getRootElement();
		// 获取坦克配置节点
		Element tank = object.element("tank");
		tankSize = Integer.parseInt(tank.attributeValue("size"));
		Element player = tank.element("player");
		player1imgPath = player.attributeValue("imgpath");
		playerTanks = new Vector<TankConfig>();
		loadTankConfig(player, playerTanks, tankSize);

		Element enemy = tank.element("enemy");
		enemyimgPath = enemy.attributeValue("imgpath");
		enemyTanks = new Vector<TankConfig>();
		loadTankConfig(enemy, enemyTanks, tankSize);
		// 获取
		Element bullet = object.element("bullet");
		loadBulletConfig(bullet);

		Element tile = object.element("tile");
		tileimgPath = tile.attributeValue("imgpath");
		tileSize = Integer.parseInt(tile.attributeValue("tilesize"));
		loadTileConfig(tile);
	}

	/**
	 * 加载障碍物配置
	 * 
	 * @param tile
	 */
	private void loadTileConfig(Element tile) {
		List<Element> tileList = tile.elements("tile");
		tileConfigs = new Vector<TileConfig>();
		for (Element element : tileList) {
			String imgpath = element.attributeValue("imgpath");
			int health = Integer.parseInt(element.attributeValue("health"));
			TileConfig config = new TileConfig(tileSize, imgpath, health);
			tileConfigs.add(config);
		}
	}

	/**
	 * 加载子弹配置
	 * 
	 * @param bullet
	 *            子弹节点
	 */
	private void loadBulletConfig(Element bullet) {
		List<Element> bulletList = bullet.elements("bullet");
		bulletConfigs = new Vector<BulletConfig>();
		int size = Integer.parseInt(bullet.attributeValue("size"));
		String imgpath = bullet.attributeValue("imgpath");
		for (Element element : bulletList) {
			int speed = Integer.parseInt(element.attributeValue("speed"));
			int firePower = Integer.parseInt(element
					.attributeValue("firepower"));
			;
			BulletConfig bulletConfig = new BulletConfig(size, imgpath, speed,
					firePower);
			bulletConfigs.add(bulletConfig);
		}
	}

	/**
	 * @param tank
	 *            坦克集合
	 * @param root
	 *            根节点
	 * @param size
	 *            坦克大小
	 */
	private void loadTankConfig(Element root, Vector<TankConfig> Tanks, int size) {
		List<Element> tankElements = root.elements("level");
		for (Element element : tankElements) {
			String type = element.attributeValue("type");
			int level = Integer.parseInt(element.attributeValue("level"));
			int health = Integer.parseInt(element.attributeValue("health"));
			int bullet = Integer.parseInt(element.attributeValue("bullet"));
			int speed = Integer.parseInt(element.attributeValue("speed"));
			int bulletCount = Integer.parseInt(element
					.attributeValue("bulletCount"));
			TankConfig tank = new TankConfig(type, size, level, health, bullet,
					speed, bulletCount);
			Tanks.add(tank);
		}

	}

	/**
	 * 
	 * @return 玩家坦克配置集合
	 */
	public Vector<TankConfig> getPlayerTanks() {
		return playerTanks;
	}

	/**
	 * 
	 * @return 敌人坦克配置集合
	 */
	public Vector<TankConfig> getEnemyTanks() {
		return enemyTanks;
	}

	/**
	 * 子弹配置集合
	 * 
	 * @return
	 */
	public Vector<BulletConfig> getBulletConfigs() {
		return bulletConfigs;
	}

	public Vector<TileConfig> getTileConfigs() {
		return tileConfigs;
	}

	public String getPlayer1imgPath() {
		return player1imgPath;
	}

	public String getEnemyimgPath() {
		return enemyimgPath;
	}

	public String getTileimgPath() {
		return tileimgPath;
	}

	public int getTankSize() {
		return tankSize;
	}

	public int getTileSize() {
		return tileSize;
	}

}
