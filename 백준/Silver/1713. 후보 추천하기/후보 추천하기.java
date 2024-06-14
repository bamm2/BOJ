import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final Integer HIGH = 1_001;

    static class Point {
        int idx, cnt, number;

        public Point(int idx, int cnt, int number) {
            this.idx = idx;
            this.cnt = cnt;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            int cnt = HIGH;
            int removeIdx = -1;
            int compIdx = -1;
            for (int j = 0; j < N; j++) {
                if (points[j]==null) {
                    points[j] = new Point(i, 1, num);
                    compIdx = -1;
                    break;
                } else {
                    if (points[j].number==num) {
                        points[j].cnt++;
                        compIdx = -1;
                        break;
                    }
                    if (cnt > points[j].cnt) {
                        cnt = points[j].cnt;
                        compIdx = points[j].idx;
                        removeIdx = j;
                    } else if (cnt==points[j].cnt && points[j].idx < compIdx) {
                        compIdx = points[j].idx;
                        removeIdx = j;
                    }
                }
            }
            if (compIdx!=-1) points[removeIdx] = new Point(i, 1, num);
        }

        int[] result = new int[N];
        Arrays.fill(result,HIGH);
        for (int i = 0; i < N; i++) {
            if (points[i]==null) break;
            result[i] = points[i].number;
        }

        Arrays.sort(result);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(result[i] == HIGH) break;
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

}