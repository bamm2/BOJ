import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int pos;
        int cnt;

        public Point(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.pos, o.pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(points);

        long sum = 0L;
        for (int i = 0; i < points.size(); i++) {
            sum += points.get(i).cnt;
        }

        long standard = (sum+1) / 2;
        long cnt = 0L;
        for (int i = 0; i < points.size(); i++) {
            cnt += points.get(i).cnt;
            if (cnt >= standard) {
                System.out.println(points.get(i).pos);
                return;
            }
        }
        br.close();
    }
}
