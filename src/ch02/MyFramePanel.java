package ch02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyFramePanel extends JFrame{
	private JButton button1;
	private JButton button2;
	// 패널 추가하기
	private JPanel panel1;
	private JPanel panel2;
	
	public MyFramePanel() {
		initData();
		setInitLayout();
		}
	private void initData() {
		setTitle("패널추가 연습");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1 = new JPanel();
		panel1.setBackground(Color.BLUE);
		panel2 = new JPanel();
		panel2.setBackground(Color.black);
		
		button1 = new JButton("button1");
		button2 = new JButton("button2");
		
		
	}
	private void setInitLayout() {
		// 루트 패널 --> BorderLayout
		add(panel1 , BorderLayout.CENTER);
		add(panel2 , BorderLayout.SOUTH);
		
		// panel1 ---> FlowLayout (이해할 수 있는 동작을 작성)
		panel1.setLayout(new BorderLayout());
		panel1.add(button1, BorderLayout.WEST);
		// panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel2.add(button2);
		// panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MyFramePanel();
		
	}
	
	
}
