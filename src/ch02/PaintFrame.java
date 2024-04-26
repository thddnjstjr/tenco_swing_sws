package ch02;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 내부 클래스 활용해서 코드를 완성해주세요

public class PaintFrame extends JFrame {

	Innerclass innerclass;

	public PaintFrame() {
		InitData();
		setInitLayout();
	}


	private void InitData() {

		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		innerclass = new Innerclass();
	}

	private void setInitLayout() {
		
		add(innerclass);
		setVisible(true);
		
	}
	class Innerclass extends JPanel {

		public void paint(Graphics g) {
			super.paint(g);
			g.drawLine(180, 190, 220, 160);
			g.drawLine(220, 160, 260, 190);
			g.drawLine(300, 190, 340, 160);
			g.drawLine(340, 160, 380, 190);
			g.drawOval(260, 210, 40, 40);
			g.drawLine(280, 250, 280, 280);
			g.drawLine(220, 280, 340, 280);
			g.drawOval(140, 90, 270, 270);
			g.drawLine(160, 150, 190, 50);
			g.drawLine(190, 50, 210, 110);
			g.drawLine(320, 100, 350, 50);
			g.drawLine(350, 50, 380, 140);
			g.drawLine(400, 100, 430, 70);
			g.drawLine(400, 100, 450, 90);
			g.drawLine(450, 90, 530, 90);
			g.drawLine(430, 70, 430, 50);
			g.drawLine(430, 50, 530, 50);
			g.drawLine(530, 50, 530, 90);
			g.drawString("어 형이야", 460, 70);
			g.drawLine(100, 190, 220, 225);
			g.drawLine(100, 230, 220, 230);
			g.drawLine(100, 270, 220, 235);
			g.drawLine(320, 225, 440, 190);
			g.drawLine(320, 230, 440, 230);
			g.drawLine(320, 235, 440, 270);
			g.drawLine(0, 370, 190, 330);
			g.drawLine(360, 330, 600, 370);
			g.drawLine(200, 120, 250, 150);
			g.drawLine(300, 150, 350, 120);
		}

	}

	
}
