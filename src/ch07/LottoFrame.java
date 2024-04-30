package ch07;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LottoFrame extends JFrame implements ActionListener {

	private JButton button;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private LottoMaker lottomaker;
	private boolean isStart = true;
	private int NUMBER_DISTANCE = 50;
	private JLabel backGround;
	int[] getNumbers;

	public LottoFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setResizable(false);
		setTitle("홀짝 게임");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button = new JButton("게임 시작");
		lottomaker = new LottoMaker();
		Icon icon = new ImageIcon("images/tazza.gif");
		backGround = new JLabel(icon);
		backGround.setLocation(300,100);
		button2 = new JButton("홀수");
		button3 = new JButton("짝수");
		button4 = new JButton("다시하기");
		
	}

	private void setInitLayout() {
		add(backGround);
		add(button, BorderLayout.NORTH);
		setVisible(true);
	}

	private void addEventListener() {
		button.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 JButton selectedButton = (JButton) e.getSource();
		 if(selectedButton.getText().equals("게임 시작")) {
			 backGround.setIcon(new ImageIcon("images/cazino.png"));
			 backGround.setLayout(new FlowLayout(FlowLayout.CENTER));
			 backGround.add(button2);
			 backGround.add(button3);
			 remove(button);
			 isStart = true;
		 }
		 else if(selectedButton.getText().equals("홀수")) {
			 backGround.setIcon(new ImageIcon("images/background1.png"));
			 isStart = false;
			 backGround.setLayout(new FlowLayout(FlowLayout.CENTER));
			 backGround.remove(button2);
			 backGround.remove(button3);
			 backGround.add(button4);
			 
		 }
		 else if(selectedButton.getText().equals("짝수")) {
			 backGround.setIcon(new ImageIcon("images/background1.png"));
			 isStart = false;
			 backGround.setLayout(new FlowLayout(FlowLayout.CENTER));
			 backGround.remove(button2);
			 backGround.remove(button3);
			 backGround.add(button4);
			 
		 }
		 else if(selectedButton.getText().equals("다시하기")) {
			 backGround.setIcon(new ImageIcon("images/cazino.png"));
			 backGround.setLayout(new FlowLayout(FlowLayout.CENTER));
			 backGround.add(button2);
			 backGround.add(button3);
			 backGround.remove(button4);
			 isStart = true;
		 }
		 
		// 이벤트가 일어나면 그림을 다시 그려라
		repaint(); // 다시 그림을 그려라 요청하는 명령어 // F5
		}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		Font f= new Font("궁서체",Font.BOLD,20);
		g.setFont(f);

		if (isStart) {
			g.drawString("홀수가 많을지 짝수가 많을지 선택하세요", 110, 120);
		} else if (isStart == false){
			int[] getNumbers = lottomaker.createNumber();
			for (int i = 0; i < getNumbers.length; i++) {
				g.drawString(getNumbers[i] + " ", 150 + i * NUMBER_DISTANCE, 200);
			}
			int odd = 0;
			int even = 0;
			for(int i = 0; i<getNumbers.length; i++) {
				if((getNumbers[i] % 2) == 1) {
					odd++;
				} else if((getNumbers[i] % 2) == 0) {
					even++;
				}
			}
			if(odd > even) {
				g.drawString("홀수가 더 많습니다", 220, 300);
			} else if(even > odd) {
				g.drawString("짝수가 더 많습니다", 220, 300);
			} else if(even == odd) {
				g.drawString("갯수가 똑같습니다", 220, 300);
			}
		}
	} 
	// 메인 함수
	public static void main(String[] args) {
		new LottoFrame();
	} // end of main

} // end of class
