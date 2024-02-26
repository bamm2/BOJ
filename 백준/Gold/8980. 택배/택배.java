import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> { // 도착시간 빠른순으로 정렬 , 같으면 가장 일찍 출발 먼저 체크
        int from, to, weight;

        public Point(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            if (this.to==o.to) return Integer.compare(this.from, o.from);
            return Integer.compare(this.to, o.to);
        }
    }

    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());

        weights = new int[N + 1]; // 현재 위치에서 무게

        int E = Integer.parseInt(br.readLine());
        Point[] points = new Point[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            points[i] = new Point(from, to, weight);
        }

        Arrays.sort(points);

        int sum = 0;
        for (int i = 0; i < E; i++) {
            Point curr = points[i];
            int max = 0;
            for (int j = curr.from; j < curr.to; j++) { // 출발부터 도착전까지 가장 무거운 무게 체크
                max = Math.max(max, weights[j]);
            }
            if (maxWeight - max > 0) { // 아직 여유공간이 있다면
                int remain = maxWeight - max; // 남아있는 공간 체크
                int add = Math.min(remain, curr.weight); // 넣을 수있는 만큼 최대한 넣기
                sum += add;
                for (int j = curr.from; j < curr.to; j++) {
                    weights[j] += add; // 추가한 무게 갱신
                }
            }
        }

        System.out.println(sum);
        br.close();

    }
}