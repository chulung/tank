package com.chulung.tank.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

public class PlayAudio extends Thread{
	
	//音乐文件名
	String musicName;
	//播放对象
	AudioClip clip;
	
	public PlayAudio(String musicName) {
		this.musicName=musicName;
	}
	
	/**
	 * 关闭当前线程中的音乐
	 */
	public void stopMusic(){
		clip.stop();
	}
	
	public void playMusic(){
		clip.play();
	}
	
	@Override
	public void run() {
		try {
			File musicFile=new File(musicName);
			URL url=musicFile.toURI().toURL();
			//通过URL得到一个播放对象
			clip=Applet.newAudioClip(url);
			//播放
			clip.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
