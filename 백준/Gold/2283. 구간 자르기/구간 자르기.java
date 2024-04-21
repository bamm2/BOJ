import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int idx, number;

        public Point(int idx, int number) {
            this.idx = idx;
            this.number = number;
        }

        public int compareTo(Point o) {
            return Integer.compare(this.number, o.number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int max = 0;
        PriorityQueue<Point> points = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            points.offer(new Point(i, from));
            points.offer(new Point(i, to));
            max = Math.max(max, to);
        }

        HashSet<Integer> hs = new HashSet<>(); // index 시작점 , 끝점 체크
        int[] count = new int[1_000_001];
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right <= max) {
            if (sum==K) {
                System.out.println(left + " " + right);
                return;
            }

            count[right] = hs.size();

            while (!points.isEmpty() && points.peek().number==right) {
                Point curr = points.poll();
                if (!hs.add(curr.idx)) hs.remove(curr.idx);
            }

            if (sum > K) {
                while (sum > K) {
                    sum -= count[left + 1];
                    left++;
                }
                if (sum==K) {
                    System.out.println(left + " " + right);
                    return;
                }
            }

            sum += hs.size();
            right++;
        }

        System.out.println("0 0");
        br.close();
    }
}
