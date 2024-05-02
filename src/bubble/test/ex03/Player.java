package bubble.test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable{

	private int x;
	private int y;
	private ImageIcon playerR, playerL;
	private int count;
	
	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	// setter
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setX(int x) {
		this.x += x;
	}
	public void setY(int y) {
		this.y += y;
	}
	public Player() {
		initData();
		setInitLayout();
	}
	
	private void setInitLayout() {
		
	}

	private void initData() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
		
		// 처음 실행 시 초기 값 셋팅
		x = 400;
		y = 540;
		
		// 플레이어가 가만히 멈춘 상태
		left = false;
		right = false;
		up = false;
		down = false;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x,y);
	}

	@Override
	public  void left() {
		
		left = true;
		setIcon(playerL);
		if(count == 0) {
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
		}
		count++;
		
		
		// 왼쪽 방향키 이벤트 발생 시
		// 이미지를 왼쪽으로 보는 이미지로 셋팅
		// 왼쪽으로 가고 있는 상태
		// 멈춰 있는 상태란 개념이 필요하다.
		
	}

	@Override
	public void right() {
		right = true;
		setIcon(playerR);
		if(count == 0) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(right) {
				x = x + SPEED;
				setLocation(x,y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}	
			}
		}).start();
		}
		count++;
		
	} // end of right

	@Override
	public  void up() {
		System.out.println("점프");
		up = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i = 0; i< 130 / JUMPSPEED; i++) {
					y = y - JUMPSPEED;
					setLocation(x,y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 130 / JUMPSPEED; i++) {
					y = y + JUMPSPEED;
					setLocation(x,y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		count++;
		// 상태 처리
		down = false;
	}
	
	
}
