import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
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

        int max = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> hm = new HashMap<>();

        while (right!=N) {
            if (hm.size() <= 1) {
                hm.put(arr[right], right);
                right++;
            } else {
                if (!hm.containsKey(arr[right])) {
                    max = Math.max(right - left, max);
                    Integer key = 0;
                    int value = 200_001;
                    for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                        if (value > entry.getValue()) {
                            key = entry.getKey();
                            value = entry.getValue();
                        }
                    }
                    hm.remove(key);
                    hm.put(arr[right], right);
                    left = value + 1;
                } else {
                    hm.put(arr[right], right);
                    right++;
                }
            }
        }

        System.out.println(Math.max(max, right - left));
        br.close();
    }
}