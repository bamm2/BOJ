import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] map = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(map[i], 987_654_321);
            map[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            map[from][to] = 0;
            if (dir==0) map[to][from] = 1;
            else map[to][from] = 0;
        }

        initFloyd(V, map);

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int dest = Integer.parseInt(st.nextToken()) - 1;
            sb.append(map[start][dest]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static void initFloyd(int V, int[][] map) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                if (i==k) continue;
                for (int j = 0; j < V; j++) {
                    if (j==k || i==j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }
    
}