package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

// 이벤트 리스너를 사용하는 방법
// 1. implements ActionListener 사용 하는 방법
// ActionListener --> 운영 체제가 제어하는 이벤트를 등록할 수 있다.
public class ColorChangeFrame2 extends JFrame implements ActionListener{

	private JPanel panel;
	private JButton button1;
	private JButton button2;
	
	public ColorChangeFrame2() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); // borderLayout
		panel = new JPanel();
		panel.setBackground(Color.yellow);
		button1 = new JButton("click1");
		button2 = new JButton("click2");
	}
	private void setInitLayout() {
		add(button1, BorderLayout.NORTH);
		add(button2, BorderLayout.SOUTH);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		
	}
	// 이 메서드에 책임은 이벤트 리스너만 등록
	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
	}
	
	// 오버라이드 : 이벤트가 일어나면 호출 되어지는 메서드
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		
		JButton selectedButton = (JButton)e.getSource();
		
		if(selectedButton.getText().equals("click1")) {
			panel.setBackground(Color.black);
		} 
		if(selectedButton.getText().equals("click2")) {
			panel.setBackground(Color.yellow);
		} 
	}
	
	// 코드 테스트
	public static void main(String[] args) {
		new ColorChangeFrame2();
	} // end of main

	
} // end of class
