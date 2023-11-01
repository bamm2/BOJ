import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int from, to;

        Point(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Point o) {
            if (this.from==o.from) return Integer.compare(o.to, this.to);
            return Integer.compare(this.from, o.from);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            pq.offer(new Point(from, to));
        }

        int distanceSum = 0;
        Point first = pq.poll();
        int start = first.from;
        int end = first.to;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (curr.from==start) continue; // 끝지점은 내림차순 정렬
            if (start < curr.from && curr.from <= end) {
                if (end < curr.to) end = curr.to;
            } else {
                distanceSum += (end - start);
                start = curr.from;
                end = curr.to;
            }
        }

        distanceSum += (end - start); // 계산 안된 선의 길이 추가

        System.out.println(distanceSum);

    }
}