package bj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_11053 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _memo, _path;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_memo = new int[_n + 1];
		Arrays.fill(_memo, 1000000001); // 오름차순 정렬을 위해 나올 수 없는 수인 가장 큰 값 +1을 채워줌.
		_memo[0] = -1000000001; // 나올 수 없는 작은 수. 정렬을 위해

		_path = new int[_n + 1];

		int len = 0;
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			int ai = Integer.parseInt(tokens.nextToken());
			if (ai > _memo[len]) {
				len += 1;
				_memo[len] = ai;
				// 자기 자신을 붙임.
				_path[len] = ai;
			} else {
				int bsr = Arrays.binarySearch(_memo, ai); // binary search result
				if (bsr >= 0) { // 똑같은 것이니까, path 없데이트 필요없음.
//					_memo[bsr] = ai;
				} else { // path 업데이트 필요.
					_memo[-bsr - 1] = ai;
					// 복사해오고,
//					if (_path[-bsr - 1 - 1] != null) { // 위치가 1일 때는 0이 null임.
//						for (int j = 0; j < -bsr - 1 - 1; j++) {
//							_path[-bsr - 1][j] = _path[-bsr - 1 - 1][j];
//						}
//					}
//					// 자기 자신을 붙임.
//					_path[-bsr - 1][-bsr - 1 - 1] = ai;
				}
			}
		}

		System.out.println(len);
		for(int i=1;i<=len;i++) {
			System.out.print(_path[i]+" ");
		}
	}

}
