package ch05;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test extends JFrame implements ActionListener {

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel panel1;
	private JPanel panel2;

	public Test() throws MalformedURLException {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1 = new JButton("click1");
		button2 = new JButton("click2");
		button3 = new JButton("click3");
		Icon icon = new ImageIcon("images/kirara.gif");
		panel1 = new JLabel(icon);
		panel1.setSize(500, 500);
		panel1.setLocation(0, 0);
		panel2 = new JPanel();
		panel2.setBackground(Color.yellow);
	}

	private void setInitLayout() {
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		setVisible(true);

	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	}

	public static void main(String[] args) {
		try {
			new Test();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		 JButton selectedButton = (JButton) e.getSource();

		if (selectedButton.getText().equals("click1")) {
			panel1.setIcon(new ImageIcon("images/kirara.gif"));
		}
		if (selectedButton.getText().equals("click2")) {
			panel1.setIcon(new ImageIcon("images/set.gif"));
		}
		if (selectedButton.getText().equals("click3")) {
			panel1.setIcon(new ImageIcon("images/sitpo.gif"));
		}
	}
}
