import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static String[][] map;
    static int[][] markingMap;
    static List<Integer> list = new ArrayList<>();
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new String[R][C];
        markingMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                String dir = Integer.toBinaryString(Integer.parseInt(st.nextToken()));
                map[i][j] = makeLen(dir);
            }
        }

        list.add(0); // 0번 인덱스 버리기
        int land = 1;
        int maxCount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (markingMap[i][j]!=0) continue;
                int count = bfs(i, j, land++);
                list.add(count);
                maxCount = Math.max(maxCount, count);
            }
        }

        int brokenWallMaxCount = brokenWallMaxCount();

        System.out.println(--land);
        System.out.println(maxCount);
        System.out.println(brokenWallMaxCount==0 ? maxCount:brokenWallMaxCount);
        br.close();
    }

    private static int brokenWallMaxCount() {
        int max = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (j>=1 && markingMap[i][j]!=markingMap[i][j - 1]) {
                    max = Math.max(max, list.get(markingMap[i][j]) + list.get(markingMap[i][j - 1]));
                }
                if (i>=1 && markingMap[i][j]!=markingMap[i-1][j]) {
                    max = Math.max(max, list.get(markingMap[i][j]) + list.get(markingMap[i-1][j]));
                }
            }
        }
        return max;
    }

    private static String makeLen(String dir) {
        if (dir.length()==4) return dir;
        return makeLen("0" + dir);
    }

    private static int bfs(int r, int c, int landNumber) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        markingMap[r][c] = landNumber;

        int count = 1;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            String currMoveAvail = map[curr[0]][curr[1]];
            for (int d = 0; d < 4; d++) {
                if (currMoveAvail.charAt(d)!='0') continue;
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || markingMap[nr][nc]==landNumber) continue;
                count++;
                markingMap[nr][nc] = landNumber;
                q.offer(new int[]{nr, nc});
            }
        }
        return count;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}