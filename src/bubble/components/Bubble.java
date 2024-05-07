package bubble.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.BubbleFrame;
import bubble.interfaces.Moveable;
import bubble.service.BackgroundBubbleService;

public class Bubble extends JLabel implements Moveable{

	private BubbleFrame mContext;
	
	
	private Player player;
	private Enemy enemy;
	BackgroundBubbleService backgroundBubbleService;
	
	
	
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean leftWall;
	private boolean rightWall;
	

	public boolean isLeftWall() {
		return leftWall;
	}

	public void setLeftWall(boolean leftWall) {
		this.leftWall = leftWall;
	}

	public boolean isRightWall() {
		return rightWall;
	}

	public void setRightWall(boolean rightWall) {
		this.rightWall = rightWall;
	}

	// 적군을 맞춘 상태
	private int state; // 0.(기본 물방울), 1.(적을 가둔 상태 물방울)

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울 팡!

	// 연관관계, 의존성 컴포지션 관계, 생성자 의존 (DI)
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initData();
		setInitLayout();

		// 객체 생성시 무조건 스레드 시작
	}

	// get,set
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ImageIcon getBubble() {
		return bubble;
	}

	public void setBubble(ImageIcon bubble) {
		this.bubble = bubble;
	}

	public ImageIcon getBubbled() {
		return bubbled;
	}

	public void setBubbled(ImageIcon bubbled) {
		this.bubbled = bubbled;
	}

	public ImageIcon getBomb() {
		return bomb;
	}

	public void setBomb(ImageIcon bomb) {
		this.bomb = bomb;
	}

	private void initData() {

		bubble = new ImageIcon("images/bubble.png");
		bubbled = new ImageIcon("images/bubbled.png");
		bomb = new ImageIcon("images/bomb.png");
		backgroundBubbleService = new BackgroundBubbleService(this);
		
		left = false;
		right = false;
		up = false;
		state = 0;
		

	}

	private void setInitLayout() {

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);
		setLocation(x, y);
	}

	// 공통으로 사용하는 부분을 메서드로 만들어 보자.
	// 이 메서드는 내부에서만 사용할 예정


	public void up() {
		up = true;

		while (true) {
			if(backgroundBubbleService.topWall()) {
				break;
			}
			y -= 5;
			setLocation(x, y);
			
			for(int i = 0; i < mContext.getEnemylength(); i++) 
			{
				int crashX = Math.abs(x - mContext.getEnemy(i).getX());
				int crashY= Math.abs(y - mContext.getEnemy(i).getY());
			if( crashX < 50 &&  crashY < 50) {
				if(mContext.getEnemy(i).getState() == 0) {
				crash(i);
				}
			}
			
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clearBubble();
	}

	@Override
	public void left() {
		left = true;
		while(left) {
		for (int i = 0; i < 400; i++) {
			if(backgroundBubbleService.leftWall() == true) {
				continue;
			}
			x--;
			setLocation(x, y);
			
			// 현재 버블에 x,y 좌표값을 알고 있다.
			
			
			for(int j = 0; j < mContext.getEnemylength(); j++) 
			{
				int crashX = Math.abs(x - mContext.getEnemy(j).getX());
				int crashY= Math.abs(y - mContext.getEnemy(j).getY());
			if( crashX < 50 &&  crashY < 50) {
				if(mContext.getEnemy(j).getState() == 0) {
				crash(j);
				}
			}
			
			}
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			left = false;
		}
		}
		up();
		
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			if(backgroundBubbleService.rightWall() == true) {
				continue;
			}
			x++;
			setLocation(x, y);
			for(int j = 0; j < mContext.getEnemylength(); j++) 
			{
				int crashX = Math.abs(x - mContext.getEnemy(j).getX());
				int crashY= Math.abs(y - mContext.getEnemy(j).getY());
			if( crashX < 50 &&  crashY < 50) {
				if(mContext.getEnemy(j).getState() == 0) {
				crash(j);
				}
			}
			
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		up();
		
	}
	
	// 외부 호출 안될 메서드
	private void clearBubble() {
		// 3초뒤에 터짐
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			// 메모리에서 해체 처리 해야 함
			
			// JFrame 안에 remove 메서드가 있다
			Thread.sleep(500);
			// todo 테스트 필요 !!
			setIcon(null);
		//	mContext.remove(this);
		// 	mContext.repaint();	
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void crash(int array) {
		mContext.getEnemy(array).setState(1);
		setIcon(bubbled);
		mContext.remove(mContext.getEnemy(array));
		mContext.getEnemy(array).setIcon(null);
		mContext.repaint();
		// 적군이 살아 있는 상태에서
		// 버블에 갇힌 상태로 변경
		
		// 버블에 이미지를 변경 처리
	}
	
}
