package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bubble.components.Bubble;
import bubble.components.Enemy;
import bubble.components.Player;

public class BubbleFrame extends JFrame {

	// 컨텍스트를 생성하는 방법 (셀프 참조)
	BubbleFrame mContext = this;
	int[] ePostionX = {200, 720 , 100};
	int[] ePostionY = {300, 180 , 535};
	private JLabel backgroundMap;
	// 포함관계 - 콤포지션
	private Player player;
	private Bubble bubble;
	private Enemy[] enemy = new Enemy[3];
	public BubbleFrame() {

		initData();
		setInitLayout();
		addEventListener();

		// Player 백그라운드 서비스 시작
		
	}

	private void initData() {
		// todo 이미지 변경
		// backgroundMap = new JLabel(new ImageIcon("images/backgroundMapService.png"));
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMapService.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame --> root panel
		setContentPane(backgroundMap); // add 처리
		setSize(1000, 640);

		// mContext --> 참조 타입( ) --> 주소값에 크기는 기본 4byte 이다.
		player = new Player(mContext);
		
		for(int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy(mContext);
			enemy[i].setX(ePostionX[i]);
			enemy[i].setY(ePostionY[i]);
		}

	}

	private void setInitLayout() {
		// 좌표 값으로 배치
		setLayout(null);
		setResizable(false); // 프레임 조절 불가
		setLocationRelativeTo(null); // JFrame 여러분 모니터 가운데 자동 배치
		setVisible(true);

		add(player);
		add(enemy[0]);
		add(enemy[1]);
		add(enemy[2]);
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
					player.attack();
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
	
	// getter
	public Player getPlayer() {
		return player;
	}
	public Enemy getEnemy(int array) {
		return enemy[array];
	}
	public int getEnemylength() {
		return enemy.length;
	}
	// 코드 테스트
	public static void main(String[] args) {
		// main 함수를 가지고 있는 클래스는 하위에 생성된 모든 객체들에
		// 주소값을 알고 있다.  (중요! 중요! 중요!)
		new BubbleFrame();
	} // end of main

}
