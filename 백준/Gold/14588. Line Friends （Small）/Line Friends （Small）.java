import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static class Point {
        int start, end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static Point[] points;
    static int[][] dist;
    static final Integer MAX = 300_000_001;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);
        }

        points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            points[i] = new Point(start, end);
        }

        for (int i = 0; i < N - 1; i++) {
            Point curr = points[i];
            for (int j = i + 1; j < N; j++) {
                Point comp = points[j];
                if (isConnect(curr.start, curr.end, comp.start, comp.end)) {
                    dist[i][j] = 1;
                    dist[j][i] = 1;
                }
            }
        }

        for (int k = 0; k < N; k++) { // 경
            for (int i = 0; i < N; i++) { // 출
                for (int j = 0; j < N; j++) { // 도
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dist[from][to]==MAX ? -1:dist[from][to]).append('\n');
        }

        System.out.println(sb);
    }

    private static boolean isConnect(int start, int end, int compStart, int compEnd) {
        return (start <= compStart && compStart <= end) || (start <= compEnd && compEnd <= end)
                || (compStart <= start && start <= compEnd) || (compStart <= end && end <= compEnd);
    }
}