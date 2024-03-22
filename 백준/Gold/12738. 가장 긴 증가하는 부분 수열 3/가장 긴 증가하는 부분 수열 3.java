import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;

	static int _n;
	static int[] _memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		_n = Integer.parseInt(input.readLine());

		_memo = new int[_n + 1];
		Arrays.fill(_memo, 1000000000 + 1); // 오름차순 정렬을 위해 나올 수 없는 수인 가장 큰 값 +1을 채워줌.
		_memo[0] = -1000000001; // 나올 수 없는 작은 수. 정렬을 위해

		int len = 0;
		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < _n; i++) {
			int ai = Integer.parseInt(tokens.nextToken());
			if (ai > _memo[len]) {
				len += 1;
				_memo[len] = ai;
			} else {
				int bsr = Arrays.binarySearch(_memo, ai); // binary search result
				if (bsr >= 0) {
					_memo[bsr] = ai;
				} else {
					_memo[-bsr - 1] = ai;
				}
			}
		}

		System.out.println(len);
	}

}