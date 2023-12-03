import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
    파란색은 움직이는 말만 방향전환 ! 문제 잘 읽기 
*/

public class Main {

    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 우 좌 상 하
    static int N, K, map[][], count;

    static class Horse {
        int number, r, c, dir;

        public Horse(int number, int r, int c, int dir) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

    }

    static Deque<Integer>[][] horsesMap;
    static Horse[] horses;
    static final Integer GAME_END = 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N]; // 흰색,빨간,파란 표시 지도
        horses = new Horse[K];  // 1~K 번 말의 현재 위치 ( index : 0 ~ K-1 )
        horsesMap = new ArrayDeque[N][N]; // 말 쌓기 표시 지도

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                horsesMap[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            Horse horse = new Horse(i, r, c, dir);
            horsesMap[r][c].offer(i);
            horses[i] = horse;
        }

        while (true) {
            count++;
            if (move()) break;
            if (count > 1000) break;
        }

        if (count > 1000) count = -1;

        System.out.println(count);
    }

    private static boolean move() {

        for (Horse horse : horses) {  // 0 ~ K-1 번말 순차적으로 이동
            int nr = horse.r + dir[horse.dir][0]; // 이동할 위치
            int nc = horse.c + dir[horse.dir][1]; // 이동할 위치

            List<Integer> upper = findUpper(horse); // 내 말 위에 있는 모든 말들 번호

            if (isOut(nr, nc) || map[nr][nc]==2) { // 나가거나 파란색 일 경우
                int changeDir = dirChange(horse.dir); // 뒤돌기
                int cr = horse.r + dir[changeDir][0]; // 뒤 돌아서 한칸
                int cc = horse.c + dir[changeDir][1]; // 뒤 돌아서 한칸
                if (isOut(cr, cc) || map[cr][cc]==2) { // 뒤 돌아도 나가거나 파란색 일 경우
                    unAvailMoveBlue(horse.r, horse.c, upper, horse.number); // 이동 못하니 현재 위치에서 방향만 전환
                } else if (map[cr][cc]==1) { //  빨간색일 경우
                    redMove(cr, cc, upper, true, horse.number);
                    if (gameOverChecker(cr, cc)) return true;
                } else { // 흰색일 경우
                    whiteMove(cr, cc, upper, true, horse.number);
                    if (gameOverChecker(cr, cc)) return true;
                }
            } else if (map[nr][nc]==1) { // 빨간색 인 경우
                redMove(nr, nc, upper, false, horse.number);
                if (gameOverChecker(nr, nc)) return true;
            } else { // 흰색 인 경우
                whiteMove(nr, nc, upper, false, horse.number);
                if (gameOverChecker(nr, nc)) return true;
            }
        }
        return false;
    }

    private static boolean gameOverChecker(int r, int c) {
        return horsesMap[r][c].size() >= GAME_END;
    }

    private static void unAvailMoveBlue(int r, int c, List<Integer> upper, int target) {
        Deque<Integer> deque = horsesMap[r][c];
        for (int i = upper.size() - 1; i >= 0; i--) {
            Integer horseNumber = upper.get(i);
            Horse hors = horses[horseNumber];
            if (horseNumber==target) horses[horseNumber] = new Horse(horseNumber, r, c, dirChange(hors.dir));
            else horses[horseNumber] = new Horse(horseNumber, r, c, hors.dir);
            deque.offerFirst(horseNumber);
        }
    }

    private static void redMove(int r, int c, List<Integer> upper, boolean isBlue, int target) {
        Deque<Integer> deque = horsesMap[r][c];
        for (int i = 0; i < upper.size(); i++) {
            int horseNumber = upper.get(i);
            Horse hors = horses[horseNumber];
            if (isBlue && horseNumber==target) horses[horseNumber] = new Horse(horseNumber, r, c, dirChange(hors.dir));
            else horses[horseNumber] = new Horse(horseNumber, r, c, hors.dir);
            deque.offerFirst(horseNumber);
        }
    }

    private static void whiteMove(int r, int c, List<Integer> upper, boolean isBlue, int target) {
        Deque<Integer> deque = horsesMap[r][c];
        for (int i = upper.size() - 1; i >= 0; i--) {
            int horseNumber = upper.get(i);
            Horse hors = horses[horseNumber];
            if (isBlue && horseNumber==target) horses[horseNumber] = new Horse(horseNumber, r, c, dirChange(hors.dir));
            else horses[horseNumber] = new Horse(horseNumber, r, c, hors.dir);
            deque.offerFirst(horseNumber);
        }
    }

    private static List<Integer> findUpper(Horse curr) {
        List<Integer> list = new ArrayList<>();

        Deque<Integer> currHorsePos = horsesMap[curr.r][curr.c];
        while (!currHorsePos.isEmpty()) {
            int horseNumber = currHorsePos.pollFirst();
            list.add(horseNumber);
            if (horseNumber==curr.number) break;
        }

        return list;
    }

    private static int dirChange(int d) {
        switch (d) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return -1;
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }

}