package bubble.test.ex05;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel{

	private Player player;
	
	
	
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적군을 맞춘 상태
	private int state; // 0.(기본 물방울), 1.(적을 가둔 상태 물방울)
	
	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울 팡!
	
	// 연관관계, 의존성 컴포지션 관계, 생성자 의존 (DI) 
	public Bubble(Player player) {
		this.player = player;
		initData();
		setInitLayout();
	}
	
	//get,set
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
		
		left = false;
		right = false;
		up = false;
		state = 0;
		
		
	}
	private void setInitLayout() {
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50,50);
		setLocation(x, y);
		
		
	}
	public void leftfire() {
		left = true;
		new Thread(new Runnable() {
			public void run() {
				while(left) {
					x -= 10;
					setLocation(x, y);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	public void rightfire() {
		right = true;
		
	}
	
	
}
