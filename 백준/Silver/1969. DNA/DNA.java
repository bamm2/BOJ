import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}

		String strans = "";
		int ans = 0;

		for (int i = 0; i < M; i++) { // 문자열의 문자 하나하나 비교
			int[] AtoZ = new int[26];
			int max = 0;
			int maxidx = 0;
			for (int j = 0; j < N; j++) { // 문자열 개수
				AtoZ[arr[j].charAt(i) - 'A']++; // 0번부터 A가 채워짐
			}

			for (int k = 0; k < AtoZ.length; k++) {
				if (max < AtoZ[k]) {
					max = AtoZ[k]; // 가장 많은 값
					maxidx = k; // 가장 많은 값의 인덱스 (0이면 +65해서 A로 만들어주기 위햄)
				}
			}
			if (max == 1) { // max가 1이면 다 같은거니까
				for (int k = 0; k < AtoZ.length; k++) { // 가장 작은 알파벳 찾아주기
					if (AtoZ[k] != 0) { // 앞에서부터 1인 인덱스 찾아주기
						strans += (char) (k + 65); // 문자열 만들어주기
						ans += N - 1; // 개수보다 1 작은 수만큼 바꿔줘야 되니까
						break;
					}
				}
			} else { // max가 1 이상일 경우
				strans += (char) (maxidx + 65); // 가장많은 값의 알파벳
				ans += N - max; // 바꿔야하는 수
				
			}

		}
		System.out.println(strans);
		System.out.println(ans);
	}// main
}