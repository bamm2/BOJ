import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        int[][] map = new int[V + 1][V + 1];
        int[][] moveDatas = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                moveDatas[i][j]=INF;
                if (i == j) continue;
                map[i][j]  = INF;
            }
        }


        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (map[from][to] > cost) {
                map[from][to] = cost;
                moveDatas[from][to] = from;
            }
        }


        for (int k = 1; k <= V; k++) { // 경
            for (int i = 1; i <= V; i++) { // 출
                if (i == k) continue;
                for (int j = 1; j <= V; j++) { // 도
                    if (i == j || k == j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        moveDatas[i][j] = moveDatas[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (map[i][j] == INF) map[i][j] = 0;
                sb.append(map[i][j]).append(" ");
            }
            sb.append('\n');
        }


        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (moveDatas[i][j] == INF) sb.append(0).append(" ");
                else {
                    Stack<Integer> stack = new Stack<>();
                    int idx = j;
                    stack.push(idx);
                    while ( i != moveDatas[i][idx]) {
                        stack.push(moveDatas[i][idx]);
                        idx = moveDatas[i][idx];
                    }
                    sb.append(stack.size()+1).append(" ").append(i).append(" ");
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop()).append(" ");
                    }
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);

    }
}