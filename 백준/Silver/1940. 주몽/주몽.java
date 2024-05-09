import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);


        int ans = 0;
        int left = 0;
        int right = N - 1;
        while (left < right) {
            if (arr[left] + arr[right]==M) {
                ans++;
                left++;
                right--;
            } else if (arr[left] + arr[right] > M) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(ans);
        br.close();
    }
}
