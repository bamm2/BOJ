import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][], min, curMax, curMin;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;

        for (int r = 0; r < N; r++) {
            for (int c = 1; c < N - 1; c++) {
                for (int d1 = 1; d1 <= N - 2; d1++) {
                    for (int d2 = 1; d2 <= N - 2; d2++) {
                        if (isOut(r + d1 + d2) || isOut(c - d1) || isOut(c + d2)) continue;
                        search(r, c, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);
        br.close();
    }

    private static void search(int r, int c, int d1, int d2) {
        visited = new boolean[N][N];
        curMin = Integer.MAX_VALUE;
        curMax = Integer.MIN_VALUE;
        int fiveSum = searchFive(r, c, d1, d2);
        curMin = fiveSum;
        curMax = fiveSum;
        int oneSum = searchOne(r, c, d1);
        if (isUnAvail(oneSum)) return;
        int twoSum = searchTwo(r, c, d2);
        if (isUnAvail(twoSum)) return;
        int threeSum = searchThree(r, c, d1, d2);
        if (isUnAvail(threeSum)) return;
        int fourSum = searchFour(r, c, d1, d2);
        findMax(fourSum);
        findMin(fourSum);

        min = Math.min(min, curMax - curMin);
    }

    private static int searchFive(int r, int c, int d1, int d2) {
        int sum = 0;
        Queue<int[]> q = new ArrayDeque<>(); // 5번 구역 경계 안쪽 구간 멤버들

        int cnt = 0;
        while (cnt++!=d1) {
            visited[++r][--c] = true;
            sum += map[r][c];

            q.offer(new int[]{r, c + 1}); // 왼쪽 아래로 내려가면서 한칸 오른쪽의 애들(안에 존재하는 애들) 큐에 넣어주기
            sum += map[r][c + 1];
            visited[r][c + 1] = true;
        }

        cnt = 0;
        while (cnt++!=d2) {
            visited[++r][++c] = true;
            sum += map[r][c];

            if (cnt!=d2) { // 맨 마지막 c+1 은 범위 밖이라 큐에 넣을 필요 없음
                q.offer(new int[]{r, c + 1}); // 오른쪽 아래로 내려가면서 한칸 오른쪽의 애들(안에 존재하는 애들) 큐에 넣어주기
                sum += map[r][c + 1];
                visited[r][c + 1] = true;
            }
        }

        cnt = 0;
        while (cnt++!=d1) {
            visited[--r][++c] = true;
            sum += map[r][c];
        }

        cnt = 0;
        while (cnt++!=d2) {
            visited[--r][--c] = true;
            sum += map[r][c];
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                sum += map[nr][nc];
                q.offer(new int[]{nr, nc});
            }
        }

        return sum;
    }

    private static boolean isUnAvail(int sum) {
        findMax(sum);
        findMin(sum);
        if (curMax - curMin > min) return true;
        return false;
    }

    private static void findMin(int comp) {
        curMin = comp > curMin ? curMin:comp;
    }

    private static void findMax(int comp) {
        curMax = comp > curMax ? comp:curMax;
    }

    private static int searchOne(int r, int c, int d1) {
        int sum = 0;
        for (int i = 0; i < r + d1; i++) {
            for (int j = 0; j <= c; j++) {
                if (visited[i][j]) break;
                sum += map[i][j];
            }
        }
        return sum;
    }

    private static int searchTwo(int r, int c, int d2) {
        int sum = 0;
        for (int i = 0; i <= r + d2; i++) {
            for (int j = c + 1; j < N; j++) {
                if (visited[i][j]) continue;
                sum += map[i][j];
            }
        }
        return sum;
    }

    private static int searchThree(int r, int c, int d1, int d2) {
        int sum = 0;
        for (int i = r + d1; i < N; i++) {
            for (int j = 0; j < c - d1 + d2; j++) {
                if (visited[i][j]) break;
                sum += map[i][j];
            }
        }
        return sum;
    }

    private static int searchFour(int r, int c, int d1, int d2) {
        int sum = 0;
        for (int i = r + d2 + 1; i < N; i++) {
            for (int j = c - d1 + d2; j < N; j++) {
                if (visited[i][j]) continue;
                sum += map[i][j];
            }
        }
        return sum;
    }

    private static boolean isOut(int v) {
        return v < 0 || v >= N;
    }
}