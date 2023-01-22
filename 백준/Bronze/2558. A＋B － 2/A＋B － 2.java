import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int A = Integer.parseInt(input.readLine());
		int B = Integer.parseInt(input.readLine());

		System.out.println(A + B);
	}

}