package bubble.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import bubble.components.Enemy;

/*
 * 현재 메인 쓰레드는 너~무 바쁨
 * 백그라운드에서 계속 Player 에 움직임을 관찰할 예정
 */

public class BackgroundEnemyService implements Runnable {

	private BufferedImage image;
	private Enemy enemy;


	// 생성자 의존 주입 DI
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			// 색상 확인 todo (보정값 필요)
			Color leftColor = new Color(image.getRGB(enemy.getX() + 10, enemy.getY() + 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 60, enemy.getY() + 25));
			Color upColor = new Color(image.getRGB(enemy.getX() + 35, enemy.getY() + 20));
			Color downColor = new Color(image.getRGB(enemy.getX() + 35, enemy.getY() + 50));
			
			
			int bottomColorleft = image.getRGB(enemy.getX() + 20, enemy.getY() + 50);
			int bottomColorright = image.getRGB(enemy.getX() + 30, enemy.getY() + 50);
			if (bottomColorleft + bottomColorright != -2) {
				enemy.setDown(false);
			} else {
				if (!enemy.isUp() && !enemy.isDown()) {
					enemy.down();
				}
			}
			
			// 왼쪽에 충돌함
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				enemy.setLeftWallCrash(true);
				enemy.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				enemy.setRightWallCrash(true);
				enemy.setRight(false);
			} else if (downColor.getRed() == 255 && downColor.getGreen() == 0 && downColor.getBlue() == 0) {
				enemy.setDownWallCrash(true);
				enemy.setDown(false);
			}  else {
				enemy.setLeftWallCrash(false);
				enemy.setRightWallCrash(false);
			}
			if (upColor.getRed() == 0 && upColor.getGreen() == 0 && upColor.getBlue() == 255) {
				enemy.setUpWallCrash(true);
				enemy.setUp(false);
			} else if (downColor.getRed() == 0 && downColor.getGreen() == 0 && downColor.getBlue() == 255) {
				enemy.setDownWallCrash(true);
				enemy.setDown(false);
			} else {
				enemy.setUpWallCrash(false);
				enemy.setDownWallCrash(false);
			}
			// 위 두 조건이 아니면 player 마음대로 움직일 수 있다.
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
