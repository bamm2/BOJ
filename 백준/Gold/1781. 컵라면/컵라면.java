import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int deadline, count;

        public Point(int deadline, int count) {
            this.deadline = deadline;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Point> pq = new PriorityQueue<>(sortByDeadlineDescAndRamenDesc());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int deadline = Integer.parseInt(st.nextToken());
            int cupRamenCnt = Integer.parseInt(st.nextToken());
            pq.offer(new Point(deadline, cupRamenCnt));
        }

        PriorityQueue<Point> candidatePq = new PriorityQueue<>(sortByRemainRamenDesc());

        int sum = 0;
        int time = pq.peek().deadline; // 가장 먼 타임라인에서 -> 1 까지 체크

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (time < curr.deadline) { // 현재 시간보다 데드라인이 큰 경우 후보안에 들어갈 수 있음
                candidatePq.offer(curr); // 후보군에 넣어놓기
            } else if (time > curr.deadline) { // 아직 못들어가는 경우
                if (!candidatePq.isEmpty()) sum += candidatePq.poll().count; // 후보군이 있다면 꺼내서 컵라면 추가
                pq.offer(curr); // curr은 아직 가능한 시간대에 도달 안했으니 원래 큐에 다시 넣어주기
                --time; // 시간 줄여주기
            } else { // 현재시간대일 경우
                if (candidatePq.isEmpty()) { // 후보군이 없다면
                    sum += curr.count;
                } else { // 후보군이 있다면
                    if (curr.count >= candidatePq.peek().count) { // 현재값이 더 크거나 같다면
                        sum += curr.count;
                    } else { // 후보군이 더 크다면
                        sum += candidatePq.poll().count;
                        candidatePq.offer(curr); // 현재 포인트를 후보군에 넣어주기
                    }
                }
                --time; // 시간 줄여주기
            }
            if (time==0) break;
        }

        // 아직 1초 이상일 경우 ( 0초 일경우 , -1 로 되어 바로 while문 종료 )
        while (--time >= 0) {
            if(candidatePq.isEmpty()) break; // 후보군이 없다면 바로 종료 
            sum+=candidatePq.poll().count;
        }

        System.out.println(sum);
        br.close();
    }

    // 데드라인 먼 쪽부터 정렬 , 컵라면 많 -> 적
    private static Comparator<Point> sortByDeadlineDescAndRamenDesc() {
        return (o1, o2) -> {
            if (o1.deadline==o2.deadline) {
                return Integer.compare(o2.count, o1.count);
            }
            return Integer.compare(o2.deadline, o1.deadline);
        };
    }

    // 해당시간에 넣을 수 있으면서 아직 안들어간 경우 , 컵라면 많 -> 적 정렬
    private static Comparator<Point> sortByRemainRamenDesc() {
        return (o1, o2) -> Integer.compare(o2.count, o1.count);
    }
}