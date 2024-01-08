import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int from, to, cost;

        public Point(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Point> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (to - from <= cost || to - from <= 0) continue; // 지름길이 아닌 경우
            if (to <= D) list.add(new Point(from, to, cost)); // 도착 위치 내에 있는 지름길만
        }

        int[] dist = new int[D + 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = i;
        }

        for (int i = 0; i < D; i++) {
            dist[i + 1] = Math.min(dist[i + 1], dist[i] + 1); // 지름길로 이미 최소값을 구해져 있는 경우 vs 현재의 최소값 +1

            for (int j = 0; j < list.size(); j++) {
                Point point = list.get(j);
                if (i==point.from) { // 지름길이 있는 시작 위치라면
                    dist[point.to] = Math.min(dist[point.to], dist[i] + point.cost);
                }
            }
        }

        bw.write(String.valueOf(dist[D]));
        bw.flush();
        bw.close();
        br.close();
    }
}