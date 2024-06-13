import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int idx, time;
        boolean isEnd;

        public Point(int idx, int time, boolean isEnd) {
            this.idx = idx;
            this.time = time;
            this.isEnd = isEnd;
        }

        public int compareTo(Point o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Point(i, start, false));
            pq.offer(new Point(i, end, true));
        }

        int[] counts = new int[N + 1]; // 사용한 횟수 체크
        Map<Integer, Integer> hm = new HashMap<>(); // 현재 사용중인 컴퓨터 체크
        PriorityQueue<Integer> restComputers = new PriorityQueue<>(); // 쉬고 있는 컴퓨터

        int wholeComputerCnt = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (!curr.isEnd) { // 시작하는 거라면 쉬고 있는 컴퓨터가 있는지 체크 , 있다면 사용 , 없다면 최대 컴퓨터수 증가 후 사용
                if (restComputers.isEmpty()) {
                    ++wholeComputerCnt;
                    hm.put(curr.idx, wholeComputerCnt);
                } else {
                    Integer computerNumber = restComputers.poll();
                    hm.put(curr.idx, computerNumber);
                }
            } else { // 종료할 때 컴퓨터 카운트 증가 + 쉬고 있는 컴퓨터에 넣어주기
                Integer computerNumber = hm.get(curr.idx);
                ++counts[computerNumber];
                restComputers.offer(computerNumber);
            }
        }

        System.out.println(result(counts, wholeComputerCnt));
        br.close();
    }

    private static String result(int[] counts, int computerCnt) {
        StringBuilder sb = new StringBuilder();
        sb.append(computerCnt).append('\n');
        for (int i = 1; i <= computerCnt; i++) {
            sb.append(counts[i]).append(" ");
        }
        return sb.toString();
    }

}