import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int map[][], N, favoritePerson[][];
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1]; // 자리 배치도
        favoritePerson = new int[N * N + 1][4]; // 입력받은 학생들이 좋아하는 친구들
        visited = new boolean[N + 1][N + 1];
        Queue<Integer> q = new ArrayDeque<>(); // 입력받은 학생 순서대로 자리 배치

        for (int i = 0, size = N * N; i < size; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int person = Integer.parseInt(st.nextToken());
            favoritePerson[person][0] = Integer.parseInt(st.nextToken());
            favoritePerson[person][1] = Integer.parseInt(st.nextToken());
            favoritePerson[person][2] = Integer.parseInt(st.nextToken());
            favoritePerson[person][3] = Integer.parseInt(st.nextToken());
            q.offer(person);
        }

        while (!q.isEmpty()) {
            int num = q.poll(); // 자리를 정해야할 학생 번호
            Point currPos = myPos(num);
            visited[currPos.r][currPos.c] = true;
            map[currPos.r][currPos.c] = num;
        }

        int ans = changeScore();
        System.out.println(ans);
    }

    private static Point myPos(int currPerson) {
        int[][] oneCheck = new int[N + 1][N + 1];
        int[][] twoCheck = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int emptyCnt = 0;
                int favorCnt = 0;
                if (visited[i][j]) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    if (isOut(nr, nc)) continue;
                    if (map[nr][nc] == 0) {
                        emptyCnt++;
                    } else {
                        for (int k = 0; k < 4; k++) {
                            if (map[nr][nc] == favoritePerson[currPerson][k]) {
                                favorCnt++;
                                break;
                            }
                        }
                    }
                }
                oneCheck[i][j] = favorCnt;
                twoCheck[i][j] = emptyCnt;
            }
        }

        Point oneReturnPoint = sameOneCheck(oneCheck);
        Point twoReturnPoint = sameTwoCheck(oneCheck,twoCheck);

        if (oneReturnPoint != null) { // 1번 조건에 부합
            return oneReturnPoint;
        } else if (twoReturnPoint != null) { // 1번 조건에 부합하면서 2번 조건에 부합
            return twoReturnPoint;
        } else {
            return threeCheck(oneCheck, twoCheck); // 1,2번 조건에 부합하면서 3번 조건에 부합하는 위치
        }
    }

    private static boolean isOut(int r, int c) {
        return r <= 0 || c <= 0 || r > N || c > N;
    }

    private static Point sameOneCheck(int[][] arr) {
        Point point = null;
        int max = 0;
        int same = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(visited[i][j]) continue;
                if (arr[i][j] > max) {
                    max = arr[i][j];
                    same = 0;
                    point = new Point(i, j);
                } else if (arr[i][j] == max) {
                    same++;
                }
            }
        }

        if (same != 0) point = null;  // 다음 조건으로 넘어가기

        return point; // 조건에 부합
    }

    private static Point sameTwoCheck(int[][] oneCheck,int[][] twoCheck) {
        Point point = null;
        int max = 0;
        int same = 0;
        int favMax = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(visited[i][j]) continue;
                if (oneCheck[i][j] > favMax) favMax = oneCheck[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(visited[i][j]) continue;
                if(oneCheck[i][j]!=favMax) continue;;
                if (twoCheck[i][j] > max) {
                    max = twoCheck[i][j];
                    same = 0;
                    point = new Point(i, j);
                } else if (twoCheck[i][j] == max) {
                    same++;
                }
            }
        }

        if (same != 0) point = null;  // 다음 조건으로 넘어가기

        return point; // 조건에 부합
    }

    private static Point threeCheck(int[][] oneCheck, int[][] twoCheck) {
        int favMax = 0;
        int emptyMax = 0;
        Point point = null;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(visited[i][j]) continue;
                if (oneCheck[i][j] > favMax) favMax = oneCheck[i][j];
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(visited[i][j]) continue;
                if(oneCheck[i][j]!=favMax) continue;
                if(twoCheck[i][j]>emptyMax){
                    emptyMax = twoCheck[i][j];
                }
            }
        }

        loop:
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(visited[i][j]) continue;
                if (oneCheck[i][j] == favMax && twoCheck[i][j] == emptyMax) {
                    point = new Point(i, j);
                    break loop;
                }
            }
        }

        return point;
    }

    private static int changeScore() {
        int[][] score = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int favCnt = 0;
                int curNum = map[i][j];
                for (int d = 0; d < 4; d++) {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    if (isOut(nr, nc)) continue;
                    for (int k = 0; k < 4; k++) {
                        if (map[nr][nc] == favoritePerson[curNum][k]) {
                            favCnt++;
                        }
                    }
                }
                score[i][j] = favCnt;
            }
        }
        return getSum(score);
    }

    private static int getSum(int[][] arr) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum += realScore(arr[i][j]);
            }
        }
        return sum;
    }

    private static int realScore(int num) {
        if (num == 0) return 0;
        else if (num == 1) return 1;
        else if (num == 2) return 10;
        else if (num == 3) return 100;
        else if (num == 4) return 1000;
        else return -1;
    }
}