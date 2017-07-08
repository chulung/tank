package com.chulung.tank.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.factory.TankFactory;

/**
 * 敌人信息面板
 * @author chulung
 *
 */
public class LayerEnemy extends Layer {

	public LayerEnemy(int x, int y, int width, int height, GameDto gameDto) {
		super(x, y, width, height, gameDto);
	}
	
	public void paint(Graphics g) {
		createBorder(g);
		Vector<Image> images = Img.enemyImages;
		int index = -1;
		int[] count = TankFactory.getEnemyTankCount();
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= 4; j++) {
				index++;
				g.drawImage(images.get(index), x + pieceSize * i * 2, y
						+ pieceSize * j, x + pieceSize * i * 2 + 28, y
						+ pieceSize * j + 28, 0, 0, 28, 28, null);
				if (GameDto.isPause()) {
					continue;
				}
				g.drawString(count[index] + "", x + pieceSize * i * 2 + pieceSize, y
						+ pieceSize * j+(pieceSize>>1));
			}
		}

	}

}
