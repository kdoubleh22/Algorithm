package bj.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -문제
 * N: 집의 개수 (2<=N<=200,000)
 * C: 공유기의 개수 (2<=C<=N)
 * x: 집의 좌표 (0<=x<=1,000,000,000)
 * -해설
 * N개 중에 C개를 고르고 거리를 다 계산하여 구하면 시간 초과.
 * 집을 정렬 후, 첫번째 집을 선택하고, 거리를 확보하여 공유기를 설치한다.
 * 공유기를 _c개 이상 설치할 수 있으면 거리르 늘리고, 없다면 거리를 줄이고 다시 탐색한다.
 */

public class BJ_G4_02110 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n; // 집의 개수 (2<=N<=200,000)
	static int _c; // 공유기의 개수(2<=C<=N)
	static int[] _houses;

	public static void main(String[] args) throws Exception {
		tokens = new StringTokenizer(input.readLine());
		_n = Integer.parseInt(tokens.nextToken());
		_c = Integer.parseInt(tokens.nextToken());

		_houses = new int[_n];
		for (int i = 0; i < _n; i++) {
			_houses[i] = Integer.parseInt(input.readLine());
		}

		Arrays.sort(_houses);

		int possible = 0;
		int impossible = 1_000_000_001;

		while (possible + 1 != impossible) {
//			System.out.println("possible:" + possible + " impossible:" + impossible);
			int dist = (impossible + possible) / 2;

			int prevHouse = _houses[0]; // 이전 집.
			int cnt = 1; // 공유기 설치 개수.
			for (int i = 1; i < _n; i++) {
				// 거리가 dist 이상이면 공유기 설치 가능.
				if (_houses[i] - prevHouse >= dist) {
					cnt++;
					prevHouse = _houses[i];
				}
			}

			// 공유기를 C개 이상 설치할 수 있으면, 거리를 늘려서 다시 탐색.
			if (cnt >= _c) {
				possible = dist;
			}
			// 거리를 줄여서 다시 탐색.
			else {
				impossible = dist;
			}
		}

		System.out.println(possible);

	}

}
