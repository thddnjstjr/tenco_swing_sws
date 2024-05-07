package bubble.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.BubbleFrame;
import bubble.interfaces.Moveable;
import bubble.state.EnemyWay;

public class Enemy extends JLabel implements Moveable {

	BubbleFrame mContext;
	// 살아 있는 상태 0, 물방울에 갇힌 상태 1
	private int state;

	// 적군에 좌표값 위치 상태
	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setmContext(BubbleFrame mContext) {
		this.mContext = mContext;
	}

	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
	}

	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}

	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
	}

	private boolean down;




	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private ImageIcon enemyL, enemyR;
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean upWallCrash;
	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public boolean isUpWallCrash() {
		return upWallCrash;
	}

	public void setUpWallCrash(boolean upWallCrash) {
		this.upWallCrash = upWallCrash;
	}

	public boolean isDownWallCrash() {
		return downWallCrash;
	}

	public void setDownWallCrash(boolean downWallCrash) {
		this.downWallCrash = downWallCrash;
	}

	private boolean downWallCrash;
	// 적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	// 적군 방향 상태
	private EnemyWay enemyWay;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		new Thread(new Runnable() {

			@Override
			public void run() {
				boolean AI = true;
				while (true) {
					if (state == 1) {
						setIcon(null);
					}
					if (AI) {
						setIcon(enemyR);
						x += 3;
						setLocation(x, y);
					} else {
						setIcon(enemyL);
						x -= 3;
						setLocation(x, y);
					}
					if (x >= 750) {
						AI = false;
					} else if (x <= 200) {
						AI = true;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	public void initData() {
		enemyL = new ImageIcon("images/enemyL.png");
		enemyR = new ImageIcon("images/enemyR.png");
		

		state = 0;

		enemyWay = EnemyWay.RIGHT;
	}

	public void setInitLayout() {
		setIcon(enemyL);
		setLocation(x, y);
		setSize(50, 50);
	}

	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;
		left = true;
		setIcon(enemyL);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {

					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();
	}

	@Override
	public void right() {
		enemyWay = EnemyWay.RIGHT;
		right = true;
		setIcon(enemyR);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					x = x + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	@Override
	public void up() {

	}

	@Override
	public void down() {
	}
}
