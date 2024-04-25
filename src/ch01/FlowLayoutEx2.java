package ch01;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

// Swing -> 배치 관리자 : FlowLayout
// 컴포넌트들을 (버튼, 라벨) 등을 수평, 수직으로 배치를 해주는 클래스 이다.
public class FlowLayoutEx2 extends JFrame{

	// 배열 활용
	private JButton[] button = new JButton[6];
	
	// 4개 더 만들어서 추가 하기
	
	// 생성자
	public FlowLayoutEx2() {
		super.setTitle("FlowLayout 연습");
		super.setSize(500,500);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 생성자에서 메서드 호출 가능하다.
		initData();
		setInitLayout();
	}
	// 멤버 변수를 초기화 하는 기능
	public void initData() {
		// 반복문의 활용
		for(int i = 0; i < button.length; i++) {
			button[i] = new JButton("buntton"+i);
		}
	}
	
	// 컴포넌트들을 배치하는 기능
	public void setInitLayout() {
		// 배치 관리자 --> BorderLayout 이라는 배치관리자가 기본으로 활용 된다.
		// FlowLayout flowlayout = new FlowLayout();
		
		// 배치 관리자 생성 및 JFrame 셋팅
		super.setLayout(new FlowLayout(FlowLayout.LEADING,50,50)); // 배치관리자 --> FlowLayout
		
		
		// 컴포넌트들을 붙이다.
		// 반복문 활용
		for(int i = 0; i < button.length; i++) {
			super.add(button[i]);
		}
	}
	
	// 코드 테스트
	public static void main(String[] args) {
		new FlowLayoutEx2(); // <---- 익명 클래스 다시접근해서 사용할 일 없으면 ...
		
		
		
		
	} // end of main
	
	
	
	
}

