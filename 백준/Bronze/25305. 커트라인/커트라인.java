import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 백준

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int tmp = 0;
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
				}
			}
		}
		int ans =0;
		
		for(int i=0; i<N;i++) {
			ans = arr[N-k];
		}
		System.out.println(ans);
	}// main
}
