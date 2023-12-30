import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[100_001];
        int left = 0;
        int right = 0;
        int max = 0;
        while (true) {
            int number = arr[right];
            count[number]++;
            if (count[number] > K) {
                while (true) {
                    count[arr[left]]--;
                    left++;
                    if (right==left || count[number] <= K) break;
                }
            }

            max = Math.max(max, right - left + 1);

            right++;

            if (right==N) break;
        }

        System.out.println(max);

    }
}