import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준 2108

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int[][] paper = new int[102][102]; // 1~100 [0] 비워주고 [101] 비워주고

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int i = x + 1; i <= x + 10; i++) { // 1번인덱스부터 시작 0번은 0 101번 0
				for (int j = y + 1; j <= y + 10; j++) { // 1번 인덱스부터 시작 0번은 0 101번 0
					paper[i][j] = 1;

				}
			}
		}

		int[] dr = { -1, 1, 0, 0 };// 상 하 좌 우
		int[] dc = { 0, 0, -1, 1 };// 상 하 좌 우
 
		int nr = 0; // i
		int nc = 0; // j

		int ans = 0;

		for (int i =0; i < 102; i++) { // 1 ~ 100
			for (int j = 0; j < 102; j++) { // 1 ~ 100
				if(paper[i][j]==1) {
				for (int d = 0; d < 4; d++) {
					nr = i + dr[d];
					nc = j + dc[d];

					if (nr >= 0 && nc >= 0 && nr < 102 && nc < 102) {
						if ( paper[nr][nc] == 0) {
							ans++;
						}
					}

				}
				}
			}
		}
		System.out.println(ans);
	}// main
}