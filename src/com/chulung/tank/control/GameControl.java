package com.chulung.tank.control;

import java.util.ArrayList;
import java.util.List;

import com.chulung.tank.dto.GameDto;
import com.chulung.tank.entity.Map;
import com.chulung.tank.factory.TankFactory;
import com.chulung.tank.service.GameService;
import com.chulung.tank.util.PlayAudio;
import com.chulung.tank.util.RandomMap;
import com.chulung.tank.ui.FrameGame;
import com.chulung.tank.ui.GamePane;

/**
 * 
 * @说明 游戏主控制层
 * @作者 chulung
 * @创建时间 2014年2月7日 下午4:57:51
 * @遗留问题
 */
public class GameControl{

	/**
	 * 初始ai数
	 */
	private static final int AI_COUNTS = 4;
	/**
	 * 游戏界面层
	 */
	private GamePane gamePane;
	/**
	 * 游戏业务逻辑层
	 */
	private GameService gameService;

	private GameDto gameDto;

	private PlayerControl playerControl;

	private List<AIControl> aiThreads;

	/**
	 * 控制层
	 * 
	 */
	public GameControl() {

		// 数据传输层
		gameDto = new GameDto();
		// 业务逻辑层
		gameService = new GameService(this,gameDto);
		// 玩家控制层
		playerControl = new PlayerControl(gameService);
		gameService.addObserver(playerControl);
		// 主面板
		gamePane = new GamePane(gameDto);
		// 添加玩家控制器
		gamePane.setControl(playerControl);
		// 绘制窗口
		new FrameGame(gamePane, this);

	}

	/**
	 * 添加ai
	 */
	private void initAI() {
		aiThreads = new ArrayList<AIControl>();
		new PlayAudio("music/开始.wav").start();
		// 随机敌人坦克数量类型
		TankFactory.randomEnemy();
		// 添加ai数 根据关数修正ai数
		for (int i = 0; i < AI_COUNTS + GameDto.pass / 4; i++) {
			// 电脑控制层 放狗咯
			AIControl ai = new AIControl(gameService);
			aiThreads.add(ai);
			// 添加子弹击中观察者
			gameService.addObserver(ai);

		}
	}

	private void setMap(Map map) {
		gameService.setMap(map);
	}


	/**
	 * 初始化游戏
	 */
	public void initGame() {
		this.setMap(new Map(RandomMap.randomMap()));
		playerControl.createTank();
		initAI();
	}


	/**
	 * 新游戏
	 */
	public void newGame() {
		if (aiThreads != null) {
			for (AIControl aiThread : aiThreads) {
				aiThread.stopAI();
			}
		}
		GameDto.pass = 1;
		GameDto.kill = 0;
		initGame();
		gamePane.startRepaint();
		if (GameDto.isPause()) {
			GameDto.ChangePause();
		}
	}


}
