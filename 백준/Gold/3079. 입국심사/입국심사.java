import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M ;
    static long ans , arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 입국심사 시간
        M = Integer.parseInt(st.nextToken()); // 상근이 친구 수

        arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        bs();

        System.out.println(ans);
    }

    private static void bs() {
        long left = 0;
        long right = arr[0] * M; // 최대 걸리는 시간

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (isOk(mid)) {
                right = mid - 1 ;
            } else {
                left = mid + 1;
            }
        }
        
        ans = left;
    }

    private static boolean isOk(long time) {
        long people = 0;
        for (int i = 0; i < N; i++) {
            people += time / arr[i];
            if (people >= M) return true;
        }

        return false;
    }

}