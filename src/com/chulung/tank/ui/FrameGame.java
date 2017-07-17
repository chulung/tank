package com.chulung.tank.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import com.chulung.tank.config.UIConfig;
import com.chulung.tank.control.GameControl;
import com.chulung.tank.factory.ConfigFactory;

/**
 * 
 * @说明 主窗体 
 * @作者 chulung
 * @创建时间 2014年2月7日 上午11:57:06
 * @遗留问题
 */
public class FrameGame extends JFrame {
	private static final long serialVersionUID = 1935486369320680504L;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrameGame(GamePane paneMain,final GameControl gameControl){
		UIConfig uiConfig= ConfigFactory.getGameConfig();
		//标题
		this.setTitle(uiConfig.getGameName());
		//默认关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//窗体大小
		this.setSize(uiConfig.getWidth(), uiConfig.getHeight());
		//不可改变窗体大小
		this.setResizable(false);
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension dimension=toolkit.getScreenSize();
		int x=dimension.width-this.getWidth()>>1;
		int y=dimension.height-this.getHeight()>>1;
		//位置居中
		this.setLocation(x, y);
		
		/**
		 * 默认panel
		 */
		this.setContentPane(paneMain);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("\u6E38\u620F(G)");
		mnGame.setMnemonic('G');
		menuBar.add(mnGame);
		
		JMenuItem mmtmStart = new JMenuItem("\u65B0\u6E38\u620F(N)");
		mmtmStart.setMnemonic('N');
		mmtmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameControl.newGame();
			}
		});
		mnGame.add(mmtmStart);
		
		JSeparator separator = new JSeparator();
		mnGame.add(separator);
		
		JMenuItem mntmRanking = new JMenuItem("\u6392\u884C\u699C(R)");
		mntmRanking.setMnemonic('R');
		mnGame.add(mntmRanking);
		
		JMenuItem mntmOption = new JMenuItem("\u9009\u9879(O)");
		mntmOption.setMnemonic('O');
		mnGame.add(mntmOption);
		
		JSeparator separator_1 = new JSeparator();
		mnGame.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("\u9000\u51FA(X)");
		mntmExit.setMnemonic('X');
		mnGame.add(mntmExit);
		
		JMenu mnHelp = new JMenu("\u5E2E\u52A9(H)");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("New menu item");
		mnHelp.add(mntmHelp);
		
		JMenuItem mntmAbout = new JMenuItem("New menu item");
		mnHelp.add(mntmAbout);
		setVisible(true);
	}

}
