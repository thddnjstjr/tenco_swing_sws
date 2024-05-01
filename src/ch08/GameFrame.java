package ch08;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	BufferedImage backgroundImage;
	BufferedImage player1;
	BufferedImage enemy1;
	ImagePanel imagePanel;
	Thread thread;

	int playerx = 150;
	int playery = 300;
	int enemyx = 250;
	int enemyy = 420;
	int space = 0;
	boolean flag = true;

	public GameFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	// 클래스 안에 클래스 -> 중첩 클래스 -> 외부클래스, 내부클래스
	private class ImagePanel extends JPanel implements Runnable {

		// paintComponents --> x
		// paintComponent --> o
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, 600, 600, null);
			g.drawImage(player1, playerx, playery, 50, 50, null);
			g.drawImage(enemy1, enemyx, enemyy, 50, 50, null);

			// Todo 플레이어, 적군 그림 그려야 함
			// 쓰레드를 활용할 예정

		}

		@Override
		public void run() {

			// true : 왼쪽으로 가는 상황
			// false : 오른쪽으로 가는 상황
			boolean direction = true;

			while(true) {
				while(flag) {
				System.out.println("진행중");
				
				if(direction) {
					enemyx -= 10;
				} else {
					enemyx += 10;
				}
				
				// 방향 바꾸는 개념은 적군 x 좌표값이 
				if(enemyx <= 50) {
					// false --> 오른쪽으로 
					direction = false; 
				} 
				
				if(enemyx >= 500) {
					// true --> 왼쪽으로 
					direction = true; 
				}
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(playerx >= (enemyx - 40) && playerx <= (enemyx +40)) {
					if(playery >= (enemyy - 40) && playery <= (enemyy +40)) {
						player1 = null;
					}
				}
				repaint();
				
				}
				repaint();
				
				
			}
		}

	}

	private void initData() {
		setTitle("Thread 활용한 미니 예제");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		try {
			// 예외가 발생할 수 있는 코드를 작성하는 영역
			backgroundImage = ImageIO.read(new File("images/backgroundMap.png"));
			player1 = ImageIO.read(new File("images/playerL.png"));
			enemy1 = ImageIO.read(new File("images/enemyL.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		imagePanel = new ImagePanel();
		// 다른작업자에게 일을 위임 시킨다.
		thread = new Thread(imagePanel);
		thread.start();
	}

	private void setInitLayout() {
		// 배치 관리자를 좌표 기반
		// setLayout(null);
		// setResizable(false); // 프레임 크기 조절 불가 설정
		setVisible(true);

		add(imagePanel);

	}

	private void addEventListener() {
		// 이벤트 리스너 등록 방법 2가지 중
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("여기가 동작 합니다.");
				int code = e.getKeyCode();
				System.out.println(code);
				if (code == KeyEvent.VK_LEFT) {
					playerx -= 10;
				} else if (code == KeyEvent.VK_UP) {
					playery -= 10;
				} else if (code == KeyEvent.VK_RIGHT) {
					playerx += 10;
				} else if (code == KeyEvent.VK_DOWN) {
					playery += 10;
				} else if (code == KeyEvent.VK_SPACE) {
					// 1. 스페이스를 눌렀을 때 적군을 멈출 수 있도록 코드 수정
					if((space % 2) == 0) {
						flag = false;
					} else {
						flag = true;
					}
					space++;
					System.out.println(space);
				}

				// 2. player가 적군과 만났다면 player 그림을 없애주세요
				
				
				repaint();
			}

		});
	}

}
