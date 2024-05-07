package bubble.test.ex08;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.accessibility.AccessibleStreamable;
import javax.imageio.ImageIO;
import javax.xml.stream.events.StartDocument;

/*
 * 현재 메인 쓰레드는 너~무 바쁨
 * 백그라운드에서 계속 Player 에 움직임을 관찰할 예정
 */

public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;


	// 생성자 의존 주입 DI
	public BackgroundPlayerService(Player player) {
		this.player = player;
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
			Color leftColor = new Color(image.getRGB(player.getX() + 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));
			Color upColor = new Color(image.getRGB(player.getX() + 35, player.getY() + 20));
			Color downColor = new Color(image.getRGB(player.getX() + 35, player.getY() + 50));
			
			
			int bottomColorleft = image.getRGB(player.getX() + 20, player.getY() + 50);
			int bottomColorright = image.getRGB(player.getX() + 30, player.getY() + 50);
			if (bottomColorleft + bottomColorright != -2) {
				player.setDown(false);
			} else {
				if (!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
			
			// 왼쪽에 충돌함
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				player.setRightWallCrash(true);
				player.setRight(false);
			} else if (downColor.getRed() == 255 && downColor.getGreen() == 0 && downColor.getBlue() == 0) {
				player.setDownWallCrash(true);
				player.setDown(false);
			}  else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			if (upColor.getRed() == 0 && upColor.getGreen() == 0 && upColor.getBlue() == 255) {
				player.setUpWallCrash(true);
				player.setUp(false);
			} else if (downColor.getRed() == 0 && downColor.getGreen() == 0 && downColor.getBlue() == 255) {
				player.setDownWallCrash(true);
				player.setDown(false);
			} else {
				player.setUpWallCrash(false);
				player.setDownWallCrash(false);
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
