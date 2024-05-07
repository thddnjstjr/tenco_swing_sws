package bubble.test.ex08;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	// 포함관계 - 콤포지션
	private Player player;
	private Bubble bubble;
	private Enemy enemy;
	public BubbleFrame() {

		initData();
		setInitLayout();
		addEventListener();

		// Player 백그라운드 서비스 시작
		new Thread(new BackgroundPlayerService(player)).start();
		new Thread(new BackgroundEnemyService(enemy)).start();
	}

	private void initData() {
		// todo 이미지 변경
		// backgroundMap = new JLabel(new ImageIcon("images/backgroundMapService.png"));
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMapService.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame --> root panel
		setContentPane(backgroundMap); // add 처리
		setSize(1000, 640);

		player = new Player();
		enemy = new Enemy();

	}

	private void setInitLayout() {
		// 좌표 값으로 배치
		setLayout(null);
		setResizable(false); // 프레임 조절 불가
		setLocationRelativeTo(null); // JFrame 여러분 모니터 가운데 자동 배치
		setVisible(true);

		add(player);
		add(enemy);
	}

	private void addEventListener() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("key code : " + e.getKeyCode());

				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:

					// 왼쪽으로 방향키 누르고 있다면
					// key 이벤트가 계쏙 <- <- <- <- <- <- <-
					// 왼쪽 상태가 아니라면
					// 왼쪽 벽에 충돌 한게 아니라면
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isUpWallCrash()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
					bubble = new Bubble(player);
					new Thread(new BackgroundBubbleService(bubble) {}).start();
					add(bubble);
					break;
				default:
					break;
				}

			} // end of keyPressed

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_UP:
					break;
				default:
					break;
				}

			} // end of keyReleased

		});

	}

	// 코드 테스트
	public static void main(String[] args) {
		new BubbleFrame();
	} // end of main

}
