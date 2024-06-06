import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N==0 && M==0) break;

            int maxCnt = 0;
            Map<Integer, Integer> hm = new HashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    int value = hm.getOrDefault(num, 0) + 1;
                    maxCnt = Math.max(maxCnt, value);
                    hm.put(num, value);
                }
            }

            List<Integer> result = new ArrayList<>();
            int maxValue = -1;
            for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                Integer currKey = entry.getKey();
                Integer currValue = entry.getValue();
                if (currValue==maxCnt) continue;
                if (currValue > maxValue) {
                    result = new ArrayList<>();
                    maxValue = currValue;
                    result.add(currKey);
                } else if (currValue==maxValue) {
                    result.add(currKey);
                }
            }
            Collections.sort(result);

            for (Integer element : result) {
                sb.append(element).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
        br.close();
    }
}