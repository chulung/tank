package com.chulung.tank.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏基本配置
 * @author chulung
 *
 */
public class UIConfig {
	
	/**
	 * 游戏名
	 */
	private String gameName;
	/**
	 * 窗口宽
	 */
	private int width;
	/**
	 * 窗口高
	 */
	private int height;
	
	/**
	 * 主背景文件夹路径
	 */
	private String bgpath ;
	
	/**
	 * 边框图片文件夹路径
	 */
	private String borderpath;
	
	/**
	 * 边框宽
	 */
	private int border;
	
	/**
	 * 画面层背景
	 */
	private String viewpath;
	
	List<LayerConfig> layerConfigs;
	
	/**
	 * 构造  读取游戏配置
	 * @throws DocumentException
	 */
	public UIConfig() throws DocumentException {
		//读取配置文件
		Document document = new SAXReader().read("common/uiconfig.xml");
		//获取根节点
		Element game = document.getRootElement();
		gameName=game.attributeValue("gamename");
		//获取界面配置节点
		Element frame = game.element("frame");
		//加载界面层配置
		loadUIConfig(frame);

	}
	
	/**
	 * 加载界面层配置
	 * @param frame 界面层元素
	 */
	private void loadUIConfig(Element frame) {
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.border=Integer.parseInt(frame.attributeValue("border"));
		this.bgpath=frame.attributeValue("bgpath");
		this.borderpath=frame.attributeValue("borderpath");
		this.viewpath=frame.attributeValue("viewpath");
		List<Element> layers = frame.elements("layer");
		layerConfigs=new ArrayList<LayerConfig>();
		for (Element element : layers) {
			 String className=element.attributeValue("class");
			 int x=Integer.parseInt(element.attributeValue("x"));
			 int y=Integer.parseInt(element.attributeValue("y"));
			 int width=Integer.parseInt(element.attributeValue("w"));
			 int height=Integer.parseInt(element.attributeValue("h"));
			 layerConfigs.add(new LayerConfig(className, x, y, width, height));
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getBorder() {
		return border;
	}

	public List<LayerConfig> getLayerConfigs() {
		return layerConfigs;
	}

	public String getGameName() {
		return gameName;
	}

	public String getBgpath() {
		return bgpath;
	}

	public String getBorderpath() {
		return borderpath;
	}

	public String getViewpath() {
		return viewpath;
	}

	
}
