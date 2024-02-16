import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] sumArr = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sumArr[i] = sumArr[i - 1] + arr[i];
        }

        Map<Integer, Integer> hm = new HashMap<>();
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            if (sumArr[i]==goal) ans++;
            ans += hm.getOrDefault(sumArr[i] - goal, 0);
            hm.put(sumArr[i], hm.getOrDefault(sumArr[i], 0) + 1);
        }

        System.out.println(ans);
        br.close();
    }
}