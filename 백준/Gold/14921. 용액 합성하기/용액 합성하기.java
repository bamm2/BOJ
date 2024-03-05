import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, arr[], ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans);
        br.close();
    }

    private static void solve() {
        ans = Integer.MAX_VALUE;

        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = Math.abs(arr[left] + arr[right]);
            if (Math.abs(ans) > Math.abs(sum)) {
                ans = arr[left]+arr[right];
            }
            if (arr[left] < 0 && arr[right] < 0) {
                left++;
            } else if (arr[left] > 0 && arr[right] > 0) {
                right--;
            } else {
                if (Math.abs(arr[left + 1] + arr[right]) > Math.abs(arr[left] + arr[right - 1])) {
                    right--;
                } else {
                    left++;
                }
            }
            if (left==right) break;
        }
    }
}