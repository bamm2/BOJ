import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static Queue<FireBall> q = new ArrayDeque<>();
    static PriorityQueue<FireBall> pq = new PriorityQueue<>();

    static class FireBall implements Comparable<FireBall> {
        int r, c, m, d, s;

        FireBall(int r, int c, int m, int d, int s) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public int compareTo(FireBall o) {
            if (this.r == o.r) {
                return Integer.compare(this.c, o.c);
            } else {
                return Integer.compare(this.r, o.r);
            }
        }

        @Override
        public String toString() {
            return "FireBall{" + "r=" + r + ", c=" + c + ", m=" + m + ", d=" + d + ", s=" + s + '}' + '\n';
        }
    }

    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.offer(new FireBall(r, c, m, d, s));
        }

        gameStart();

        int ans = 0;
        while (!q.isEmpty()) {
            FireBall poll = q.poll();
            ans += poll.m;
        }

        System.out.println(ans);

    }

    private static void gameStart() {

        while (K-- > 0) {
            moveFireBall();
            findSamePos();
        }

    }

    private static void moveFireBall() {
        int size = q.size();
        while (size-- > 0) {
            FireBall curr = q.poll();
            int nr = mod(curr.r + dir[curr.d][0] * curr.s);
            int nc = mod(curr.c + dir[curr.d][1] * curr.s);
            pq.offer(new FireBall(nr, nc, curr.m, curr.d, curr.s));
        }
    }

    private static int mod(int num) {
        if (num < 0) {
            int multiple = Math.abs(num)/N;
            num += N*multiple;
            if(num == 0) return 0;
            else return N + num;
        }
        else return num % N;
    }

    private static void findSamePos() {
        int size = pq.size();

        List<FireBall> list = new ArrayList<>();

        while (size-- > 0) {
            FireBall poll = pq.poll();

            if (list.size() == 0) list.add(poll);
            else {
                if (poll.r == list.get(0).r && poll.c == list.get(0).c) { // 같으면 리스트 넣어주기
                    list.add(poll);
                } else { // 같은 위치가 아닐 경우
                    if (list.size() == 1) { // 다를 경우 같은 위치의 다른 파이어볼이 없으면 분할 X
                        q.add(list.get(0)); // 다음 탐색을 위해 큐에 넣어주기
                        list.clear(); // 리스트 비워주고
                        list.add(poll); // 다음 탐색을 위해 현재 파이어볼을 넣어주기
                    } else { // 여러개가 같은 위치일 경우 분할 진행 !
                        divideFireBall(list, poll);
                    }
                }
            }
        }

        if (list.size() == 1) {
            q.offer(list.remove(0));
        } else if (list.size() >= 2) {
            divideFireBall(list, null);
        }
    }

    private static void divideFireBall(List<FireBall> list, FireBall poll) {
        int mSum = 0;
        int sSum = 0;
        int dirCheck = 0; // 홀,짝 판별용
        boolean isSameDir = true;
        for (FireBall fireBall : list) {
            mSum += fireBall.m;
            sSum += fireBall.s;
            if (dirCheck == 0) dirCheck = (fireBall.d % 2) + 1; // 1이면 짝, 2이면 홀
            else {
                // 짝일 때 홀이거나 홀일 때 짝일 경우
                if ((dirCheck == 1 && fireBall.d % 2 == 1) || (dirCheck == 2 && fireBall.d % 2 == 0)) isSameDir = false;
            }
        }
        // 동일한 위치
        int r = list.get(0).r;
        int c = list.get(0).c;
        int divM = mSum / 5; // 질량
        int divS = sSum / list.size(); // 속력
        if (divM != 0) { // 질량이 0이 아니면 분할
            if (isSameDir) { // 0,2,4,6
                for (int d = 0; d < 8; d += 2) {
                    q.offer(new FireBall(r, c, divM, d, divS));
                }
            } else {// 1,3,5,7
                for (int d = 1; d < 8; d += 2) {
                    q.offer(new FireBall(r, c, divM, d, divS));
                }
            }
        }

        list.clear(); // 기존 존재하던 동일한 위치들 다 버려주고
        list.add(poll); // 새로운 원소 넣어주기
    }
}