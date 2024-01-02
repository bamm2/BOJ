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

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        int cnt = 0;
        int left = 0;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0, size = N + k - 1; i < size; i++) {
            int curr = arr[i % N];
            hm.put(curr, hm.getOrDefault(curr, 0) + 1);
            if (cnt!=k) {
                cnt++;
            } else {
                if (hm.get(arr[left])==1) hm.remove(arr[left]);
                else hm.put(arr[left], hm.get(arr[left]) - 1);
                left++;
            }

            if (cnt==k) {
                if (hm.containsKey(c)) max = Math.max(max,hm.size());
                else max = Math.max(max,hm.size() + 1);
            }
        }

        System.out.println(max);

    }
}