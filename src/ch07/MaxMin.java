package ch07;

import java.util.Random;

public class MaxMin {

	public static int[] createNumber2() {
		Random random = new Random();
		int[] number = new int[6];
		for (int i = 0; i < number.length; i++) {
			number[i] = random.nextInt(45) + 1;
			for (int j = 0; j < i; j++) {
				if (number[j] == number[i]) {
					i--;
				}
			}
		}
		return number;
	}

	public static void main(String[] args) {
		int max = 0;
		int min = 45;
		int maxpos = 0;
		int minpos = 0;
		int[] randomNumber = MaxMin.createNumber2();
		for (int i = 0; i < randomNumber.length; i++) {
			if(randomNumber[i]>max) {
				max = randomNumber[i];
				maxpos = i+1;
			}
			if(randomNumber[i]<min) {
				min = randomNumber[i];
				minpos = i+1;
			}
		}

		for (int i = 0; i < randomNumber.length; i++) {
			System.out.println(randomNumber[i]);
		}
		System.out.println("가장 큰 값은 " + max + "이고, 위치는" + maxpos + "번쨰입니다.");
		System.out.println("가장 작은 값은 " + min + "이고, 위치는" + minpos + "번재입니다.");
	}
}
