import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] asc = new int[N];
        int[] desc = new int[N];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            asc[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && asc[i] < asc[j] + 1) {
                    asc[i] = asc[j] + 1;
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            desc[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j] && desc[i] < desc[j] + 1) {
                    desc[i] = desc[j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, asc[i] + desc[i] - 1);
        }

        System.out.println(max);
        br.close();
    }
}