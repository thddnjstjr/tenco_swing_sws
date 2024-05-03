package bubble.test.ex07;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

			Color enemyLeft = new Color(image.getRGB(enemy.getX()+0, enemy.getY() + 50));
			Color enemyRight = new Color(image.getRGB(enemy.getX()+50, enemy.getY() + 50));
			if (enemyLeft.getRed() == 255 && enemyLeft.getGreen() == 255 && enemyLeft.getBlue() == 255) {
				enemy.setLeftWall(true);
			} else if (enemyLeft.getRed() == 255 && enemyLeft.getGreen() == 255 && enemyLeft.getBlue() == 255) {
				enemy.setRightWall(true);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
