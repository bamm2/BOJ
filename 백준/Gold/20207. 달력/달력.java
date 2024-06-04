import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final Integer WHOLE_DATES = 366;
    static final Integer INIT = 0;
    static int start, end;
    static List<boolean[]> map;
    static Point[] points;

    static class Point implements Comparable<Point> {
        int from, to, diff;

        public Point(int from, int to, int diff) {
            this.from = from;
            this.to = to;
            this.diff = diff;
        }

        @Override
        public int compareTo(Point o) {
            return this.from==o.from ? Integer.compare(o.diff, this.diff):Integer.compare(this.from, o.from);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        points = new Point[N];
        map = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            points[i] = new Point(from, to, to - from);
            end = Math.max(end, to); // 가장 마지막 날짜
        }

        Arrays.sort(points);

        initCalendar();

        start = points[0].from; // 시작일
        int[] calculatedArr = initSumArr();

        System.out.println(calculate(calculatedArr));
        br.close();
    }

    private static void initCalendar() {
        for (Point point : points) {
            if (map.isEmpty()) {
                boolean[] calendar = mark(point.from, point.to);
                map.add(calendar);
            } else {
                boolean flag = false;
                for (boolean[] calendar : map) {
                    if (isAvailable(calendar, point.from, point.to)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    boolean[] calendar = mark(point.from, point.to);
                    map.add(calendar);
                }
            }
        }
    }

    private static boolean[] mark(int from, int to) {
        boolean[] calendar = new boolean[WHOLE_DATES];
        for (int i = from; i <= to; i++) {
            calendar[i] = true;
        }
        return calendar;
    }

    private static boolean isAvailable(boolean[] cal, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (cal[i]) return false;
        }

        for (int i = from; i <= to; i++) {
            cal[i] = true;
        }

        return true;
    }

    private static int[] initSumArr() {
        int[] arr = new int[WHOLE_DATES];
        for (boolean[] calendar : map) {
            for (int i = start; i <= end; i++) {
                if (calendar[i]) arr[i]++;
            }
        }
        return arr;
    }

    private static int calculate(int[] arr) {
        int result = 0;

        int fromDate = start;
        int multiple = 0;
        for (int i = start; i <= end; i++) {
            if (arr[i]!=0) {
                if (fromDate==0) fromDate = i;
                multiple = Math.max(multiple, arr[i]);
            } else {
                result += multiple * (i - fromDate);
                multiple = INIT;
                fromDate = INIT;
            }
        }
        if (fromDate!=0 && arr[end]!=0) {
            result += multiple * (end - fromDate + 1);
        }

        return result;
    }
}