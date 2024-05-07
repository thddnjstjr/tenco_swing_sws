package bubble.test.ex09Class;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundEnemyService {

	private BufferedImage image;
	private Enemy enemy;
	private Bubble bubble;

	// 생성자 의존 주입 DI
	public BackgroundEnemyService(Enemy enemy, Bubble bubble) {
		this.enemy = enemy;
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean rightWall() {
		if (enemy.getX() + 50 == bubble.getX() && enemy.getY() + 25 == bubble.getY() + 25) {
			enemy.setRightWall(true);
			System.out.println(bubble.getX());
			bubble.count();
			return true;
		}
		return false;
	}

	public boolean leftWall() {
		if (enemy.getX() == bubble.getX() + 50 && enemy.getY() + 25 == bubble.getY() + 25) {
			enemy.setLeftWall(true);
			bubble.count();
			return true;
		}
		return false;
	}

}
