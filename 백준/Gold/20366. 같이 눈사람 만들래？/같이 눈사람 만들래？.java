import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;

        loop:
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 3; j < N; j++) {
                int sum = arr[i] + arr[j];
                int L = i + 1;
                int R = j - 1;
                int comp = arr[L] + arr[R];
                minDiff = Math.min(minDiff, Math.abs(comp - sum));

                while (L < R) {
                    if (sum < comp) {
                        comp = arr[L] + arr[--R];
                        minDiff = Math.min(minDiff, Math.abs(comp - sum));
                    } else if (sum > comp) {
                        comp = arr[++L] + arr[R];
                        minDiff = Math.min(minDiff, Math.abs(comp - sum));
                    } else {
                        minDiff = 0;
                        break loop;
                    }
                }
            }
        }

        System.out.println(minDiff);
        br.close();
    }
}
