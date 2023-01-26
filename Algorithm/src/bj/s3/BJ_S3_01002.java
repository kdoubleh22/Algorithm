package bj.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -문제
 * 두 원의 접점 개수 구하기
 * -풀이
 * 무한대일 때(같을 때)
 * 2개일 때(두 중심의 거리가 r1+r2보다 작을 때 && 내접하기 전)
 * 1개일 때(외접,내접)
 * 0개일 때(두 중심의 거리가 r1+r2보다 클 때) - 나머지
 */

public class BJ_S3_01002 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _t;
	// -10,000 <= x1,y1,x2,y2 <= 10,000, r1,r2는 10,000보다 작은 자연수.
	static int _x1, _y1, _r1;
	static int _x2, _y2, _r2;

	public static void main(String[] args) throws Exception {
		_t = Integer.parseInt(input.readLine());

		while (_t-- > 0) {
			tokens = new StringTokenizer(input.readLine());
			_x1 = Integer.parseInt(tokens.nextToken());
			_y1 = Integer.parseInt(tokens.nextToken());
			_r1 = Integer.parseInt(tokens.nextToken());
			_x2 = Integer.parseInt(tokens.nextToken());
			_y2 = Integer.parseInt(tokens.nextToken());
			_r2 = Integer.parseInt(tokens.nextToken());

			// 무한대
			if (_x1 == _x2 && _y1 == _y2 && _r1 == _r2) {
				output.append(-1).append("\n");
				continue;
			}

			// 두 점 사이의 기리
			int squareOfDistanceBetweenTwoPoints = (_x1 - _x2) * (_x1 - _x2) + (_y1 - _y2) * (_y1 - _y2);
			// 반지름의 합
			int squareOfSumOfRadius = (_r1 + _r2) * (_r1 + _r2);
			int squareOfSubOfRadius = (_r1 - _r2) * (_r1 - _r2);

//			System.out.println("squareOfDistanceBetweenTwoPoints:"+squareOfDistanceBetweenTwoPoints);
//			System.out.println("squareOfSumOfRadius:"+squareOfSumOfRadius);

			// 2개
			if (squareOfDistanceBetweenTwoPoints < squareOfSumOfRadius
					&& squareOfDistanceBetweenTwoPoints > squareOfSubOfRadius) {
				output.append(2).append("\n");
				continue;
			}

			// 1개
			// 외접
			if (squareOfDistanceBetweenTwoPoints == squareOfSumOfRadius) {
				output.append(1).append("\n");
				continue;
			}
			// 내접
			if (squareOfDistanceBetweenTwoPoints == squareOfSubOfRadius) {
				output.append(1).append("\n");
				continue;
			}

			// 0개
			output.append(0).append("\n");
		}

		System.out.println(output);
	}

}
