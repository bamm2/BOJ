import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] vowel = { 'a', 'e', 'i', 'o', 'u' };
		// 모음 하나 무조건 포함
		// 모음 3개 혹은 자음 3개가 연속 오면 안됨
		// 같은 글자 연속적으로 두번 오면 안되나 ,ee, oo 는 가능
		// 조건 만족하면 <문자>+ is acceptable.
		// 조건 만족 안될 경우 <조건>+ is not acceptable.

		while (true) {

			String s = br.readLine();
			if (s.equals("end"))
				break;

			int vowel_cnt = 0;
			boolean[] chk = new boolean[s.length()];
			boolean nope = false;

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				for (int j = 0; j < 5; j++) {
					if (c == vowel[j]) {
						vowel_cnt++;
						chk[i] = true;
					}
				}
			}
			for (int j = 0; j < s.length() - 1; j++) {
				char ch = s.charAt(j);
				if (!(ch == 'e' || ch == 'o')) {
					if (ch == s.charAt(j + 1)) {
						System.out.println("<" + s + ">" + " is not acceptable.");
						nope = true;
						break;
					}
				}
			}
			for (int j = 0; j < chk.length - 2; j++) {
				if (chk[j] == chk[j + 1] && chk[j + 1] == chk[j + 2]) {
					System.out.println("<" + s + ">" + " is not acceptable.");
					nope = true;
					break;
				}
			}

			if (vowel_cnt == 0 && nope== false ) {
				System.out.println("<" + s + ">" + " is not acceptable.");
			}

			if (vowel_cnt > 0 && nope == false)
				System.out.println("<" + s + ">" + " is acceptable.");
		}//while문 

	}// main
}