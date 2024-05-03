package bubble.test.ex07;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Enemy extends JLabel implements Moveable{

	private int x;
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

	private int y;
	private ImageIcon enemyL, enemyR;
	private boolean leftWall;
	private boolean rightWall;
	
	public Enemy() {
		initData();
		setInitLayout();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				boolean AI = true;
				while(true) {
				if(AI) {
					setIcon(enemyR);
					x += 3;
					setLocation(x,y);
				} else {
					setIcon(enemyL);
					x -= 3;
					setLocation(x,y);
				}
				if(x >= 750) {
					AI = false;
				} else if(x <= 200) {
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
		x = 200;
		y = 300;
	}
	
	public void setInitLayout() {
		setIcon(enemyL);
		setLocation(x,y);
		setSize(50,50);
	}
	@Override
	public void left() {
		
	}

	@Override
	public void right() {
		
	}

	@Override
	public void up() {
		
	}
	
	@Override
	public void down() {
	}
}
