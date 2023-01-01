package bj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_B2_3040 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();

	static int[] nums;
	static int[] choosed;
	static boolean flag;

	static void comb(int cnt, int start) {
		if (cnt == 7) {
			int sum = 0;
			for (int num : choosed) {
				sum += num;
			}
			if (sum == 100) {
				for (int num : choosed) {
					System.out.println(num);
				}
				flag = false;
			}
			return;
		}

		for (int i = start; i < nums.length; i++) {
			choosed[cnt] = nums[i];
			if (flag) {
				comb(cnt + 1, i + 1);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		nums = new int[9];
		for (int i = 0; i < 9; i++) {
			nums[i] = Integer.parseInt(input.readLine());
		}

		choosed = new int[7];
		flag = true;
		comb(0, 0);
	}

}
