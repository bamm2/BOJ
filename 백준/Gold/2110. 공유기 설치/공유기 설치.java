import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C, arr[], ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 집의 개수
        C = Integer.parseInt(st.nextToken()); // 공유기 개수

        arr = new int[N]; // N개의 집

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        bs();

        System.out.println(ans);
    }

    private static void bs() {
        int left = 0;
        int right = arr[N - 1];

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

    }

    private static boolean check(int mid) {
        int cnt = 1;
        int idx = 0;

        for (int i = 0; i < N - 1; i++) {
            if (arr[idx] + mid <= arr[i + 1]) {
                cnt++;
                idx = i + 1;
            }
        }


        if (cnt >= C) return true;

        return false;
    }

}