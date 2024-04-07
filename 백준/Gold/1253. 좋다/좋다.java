import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        Map<Integer, Integer> elements = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            elements.put(arr[i], elements.getOrDefault(arr[i], 0) + 1);
        }

        int ans = 0;
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = arr[i] + arr[j];
                if (arr[i]==sum && elements.get(arr[i])==1) continue;
                if (arr[j]==sum && elements.get(arr[j])==1) continue;
                if (arr[i]==sum && arr[i]==arr[j] && elements.get(arr[i])==2) continue;
                if (elements.get(sum)!=null) {
                    hs.add(sum);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : elements.entrySet()) {
            if (hs.contains(entry.getKey())) {
                ans += entry.getValue();
            }
        }

        System.out.println(ans);
        br.close();
    }
}