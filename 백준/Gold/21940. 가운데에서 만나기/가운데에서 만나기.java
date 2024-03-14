import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] cost = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(cost[i], 10_000_000);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            cost[from][to] = w;
        }

        int K = Integer.parseInt(br.readLine());
        int[] friends = new int[K];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 1; k <= V; k++) { // 경유지
            for (int i = 1; i <= V; i++) { // 출발지
                for (int j = 1; j <= V; j++) { // 도착지
                    if (i==j || i==k || k==j) continue;
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        List<Integer> candidates = new ArrayList<>();
        int standard = Integer.MAX_VALUE;
        for (int goalV = 1; goalV <= V; goalV++) {
            int max = 0;
            for (int j = 0; j < K; j++) {
                int startV = friends[j];
                if (startV==goalV) continue;
                max = Math.max(max, cost[startV][goalV] + cost[goalV][startV]);
            }
            if (max < standard) {
                standard = max;
                candidates.clear();
                candidates.add(goalV);
            } else if (max==standard) {
                candidates.add(goalV);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer candidate : candidates) {
            sb.append(candidate).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}