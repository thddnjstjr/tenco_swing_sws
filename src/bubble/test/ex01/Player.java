package bubble.test.ex01;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable{

	private int x;
	private int y;
	private ImageIcon playerR, playerL;
	
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
		x = 55;
		y = 535;
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x,y);
	}

	@Override
	public void left() {
		// 왼쪽 방향키 이벤트 발생 시
		// 이미지를 왼쪽으로 보는 이미지로 셋팅
		setIcon(playerL);
		x = x - 10;
		setLocation(x, y);
	}

	@Override
	public void right() {
		setIcon(playerR);
		x = x + 10;
		setLocation(x, y);
	}

	@Override
	public void up() {
		System.out.println("점프");
	}

	@Override
	public void down() {
		System.out.println("다운");
	}
	
	
}
