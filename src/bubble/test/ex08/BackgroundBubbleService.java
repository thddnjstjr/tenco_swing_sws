package bubble.test.ex08;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBubbleService implements Runnable{

	private BufferedImage image;
	private Bubble bubble;

	// 생성자 의존 주입 DI
		public BackgroundBubbleService(Bubble bubble) {
			this.bubble = bubble;
			try {
				image = ImageIO.read(new File("images/backgroundMapService.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while(true) {
				if(!bubble.isUp()) {
				Color bubbleLeft = new Color(image.getRGB(bubble.getX(),bubble.getY()+25));
				Color bubbleRight = new Color(image.getRGB(bubble.getX()+60,bubble.getY()+25));
				if (bubbleLeft.getRed() == 255 && bubbleLeft.getGreen() == 0 && bubbleLeft.getBlue() == 0) {
					bubble.setLeftWall(true);
				} else if (bubbleRight.getRed() == 255 && bubbleRight.getGreen() == 0 && bubbleRight.getBlue() == 0) {
					bubble.setRightWall(true);
				}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	
}
