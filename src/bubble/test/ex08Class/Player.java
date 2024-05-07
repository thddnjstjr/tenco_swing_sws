package bubble.test.ex08Class;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Player extends JLabel implements Moveable{

	private int x;
	private int y;
	private ImageIcon playerR, playerL;
	private int count;
	
	public boolean isGravity() {
		return gravity;
	}

	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	//벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean upWallCrash;
	private boolean downWallCrash;
	private boolean gravity;
	
	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	// enum 타입의 활용
	
	
	// get,set
	public Player() {
		initData();
		setInitLayout();
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

	public ImageIcon getPlayerR() {
		return playerR;
	}

	public void setPlayerR(ImageIcon playerR) {
		this.playerR = playerR;
	}

	public ImageIcon getPlayerL() {
		return playerL;
	}

	public void setPlayerL(ImageIcon playerL) {
		this.playerL = playerL;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public boolean isLeftWallCrash() {
		return leftWallCrash;
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

	public int getSPEED() {
		return SPEED;
	}

	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	private void setInitLayout() {
		
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

	private void initData() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
		
		// 처음 실행 시 초기 값 셋팅
		x = 400;
		y = 535;
		
		// 플레이어가 가만히 멈춘 상태
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x,y);
		
		
	}

	@Override
	public  void left() {
		left = true;
		setIcon(playerL);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(left) {
					
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
		// 왼쪽 방향키 이벤트 발생 시
		// 이미지를 왼쪽으로 보는 이미지로 셋팅
		// 왼쪽으로 가고 있는 상태
		// 멈춰 있는 상태란 개념이 필요하다.
		
	}

	@Override
	public void right() {
		right = true;
		setIcon(playerR);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(right) {
				x = x + SPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}	
			}
		}).start();
		
	} // end of right

	@Override
	public  void up() {
		gravity = false;
		System.out.println("점프");
		up = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i = 0; i< 150 / JUMPSPEED; i++) {
					if(upWallCrash == true) {
						continue;
					}
					y = y - JUMPSPEED;
					setLocation(x,y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				up = false;
				down();
			}
		}).start();
		// 객체의 상태값을 잘 조절해야 한다.
	}

	@Override
	public void down() {
		System.out.println("다운");
		down = true;
		
		new Thread(()-> {
			
			
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 150 / JUMPSPEED; i++) {
					while(down) {
					if(downWallCrash == true) {
						down = false;
					}
					y = y + JUMPSPEED;
					setLocation(x,y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				}
				down = false;
			}
		}).start();
		// 상태 처리
		
	}
	
	
}
