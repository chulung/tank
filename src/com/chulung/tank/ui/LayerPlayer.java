package com.chulung.tank.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import com.chulung.tank.dto.GameDto;

/**
 * 玩家信息面板 
 * @author chulung
 *
 */
public class LayerPlayer extends Layer{

	public LayerPlayer(int x, int y, int width, int height, GameDto gameDto) {
		super(x, y, width, height, gameDto);
	}

	public void paint(Graphics g) {
		createBorder(g);
		if (GameDto.isPause()) {
			return;
		}
		Image imgC = Img.charImage;
		g.setColor(Color.gray);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		//关数
		g.drawImage(imgC, x+32, y+32, x+32*2, y+32*2, 0, 0, imgC.getWidth(null)/3, imgC.getHeight(null),null);
		g.drawString(GameDto.pass+"", x+32*3, y+32*2-4);
		g.drawImage(imgC, x+32, y+32*2, x+32*2, y+32*3, imgC.getWidth(null)/3, 0, imgC.getWidth(null)/3*2, imgC.getHeight(null),null);
		g.drawString(GameDto.kill+"", x+32*3, y+32*3-4);
		g.drawImage(imgC, x+32, y+32*3, x+32*2, y+32*4, imgC.getWidth(null)/3*2, 0, imgC.getWidth(null), imgC.getHeight(null),null);
		g.drawString(gameDto.getPlayerTank().getType()+"", x+32*3, y+32*4-4);
	}

}
