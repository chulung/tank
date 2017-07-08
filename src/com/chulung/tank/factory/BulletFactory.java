package com.chulung.tank.factory;

import java.awt.Point;
import java.util.Vector;

import com.chulung.tank.config.BulletConfig;
import com.chulung.tank.config.ObjectConfig;
import com.chulung.tank.entity.Bullet;
import org.dom4j.DocumentException;

/**
 * 子弹工厂
 * @author chulung
 *
 */
public class BulletFactory {
	private static Vector<BulletConfig> bulletConfigs;
	static{
		try {
			bulletConfigs=new ObjectConfig().getBulletConfigs();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static Bullet creatNewBullet(int level, Point point, Point move, int iff){
		Point point1=new Point(point);
		Point move1=new Point(move);
		//TODO 硬编码 子弹位置修正量 使其从炮管发出
		point1.x+=11;
		point1.y+=11;
		Bullet bullet=new Bullet(bulletConfigs.get(level-1), point1, move1,iff);
		return bullet;
		
	}
}	

