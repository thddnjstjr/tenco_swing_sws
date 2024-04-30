package ch06;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*
 * 키 이벤트 리스너 사용해보기
 */

public class MiniGame3 extends JFrame {

	private JTextArea textArea;
	private JLabel background1;
	private JLabel player;
	private JLabel enemy;
	private int x = 700;
	private int y = 600;
	private int x2 = 280;
	private int y2 = 370;
	boolean fall = false;
	boolean jump = false;
	int field =750;
		
	
	
	public MiniGame3() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 프레임 사이즈 조절 불가
		
		Icon icon1 = new ImageIcon("images/backgroundMap.png");
		Icon icon2 = new ImageIcon("images/playerL.png");
		Icon icon3 = new ImageIcon("images/enemyR.png");
		background1 = new JLabel(icon1);
		background1.setSize(1000,1000);
		background1.setLocation(0,0);
		player = new JLabel(icon2);
		player.setSize(50,50);
		player.setLocation(x,y);
		enemy = new JLabel(icon3);
		enemy.setSize(50,50);
		enemy.setLocation(x2,y2);
	}
	private void setInitLayout() {
		// 좌표 기준으로 셋팅하고 싶다면 null
		setVisible(true);
		add(enemy);
		add(player);
		add(background1);
		setLayout(null);
		
		
		
	}
	
	private void addEventListener() {
		// textArea 위에서 사용자가 키보드를 누르면 감지해서
		// 나에게(코드기준) 알려줘
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					 System.out.println("왼쪽");
					 player.setIcon(new ImageIcon("images/playerL.png"));
					 x -= 10;
					 player.setLocation(x,y);
					 if(x<50) {
						 x =50;
					 }
				 }
				 if(e.getKeyCode() == KeyEvent.VK_UP) {
					 System.out.println("위");
					 y -= 10;
					 player.setLocation(x,y);
					 if(y<220) {
						 y =220;
					 }
				 }
				 if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					 System.out.println("오른쪽");
					 player.setIcon(new ImageIcon("images/playerR.png"));
					 x += 10;
					 player.setLocation(x,y);
					 if(x>900) {
						 x =900;
					 }
				 }
				 if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					 y += 10;
					 System.out.println("아래");
					 if(y>750) {
						 y =750;
					 }
				 }
			}
	
		});
	}

	public static void main(String[] args) {
		new MyKeyEvent();
	}	
}
