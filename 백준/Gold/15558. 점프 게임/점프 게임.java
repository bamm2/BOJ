import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, k;
    static boolean[][] visited;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        String left = br.readLine();
        String right = br.readLine();

        map = new char[N][2];
        for (int i = 0; i < N; i++) {
            map[i][0] = left.charAt(i);
            map[i][1] = right.charAt(i);
        }

        System.out.println(go() ? 1:0);

        br.close();
    }

    private static boolean go() {
        visited = new boolean[N][2];
        Queue<int[]> q = new ArrayDeque<>();
        visited[0][0] = true;
        q.offer(new int[]{0, 0}); // 위치 , 왼쪽 또는 오른쪽

        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] curr = q.poll();
                if (curr[0] < time) continue;
                int[] dir = {curr[0] + 1, curr[0] - 1, curr[0] + k}; // 앞으로 이동 ,뒤로 이동, 건너가서 k만큼 이동
                for (int d = 0; d < 3; d++) {
                    if (dir[d] >= N) return true;
                    if (d==2) { // 건너가서 체크
                        if (isMovable(dir[d], (curr[1] + 1) % 2)) {
                            q.offer(new int[]{dir[d], (curr[1] + 1) % 2});
                        }
                    } else {
                        if (dir[d] >= 0 && isMovable(dir[d], curr[1])) {
                            q.offer(new int[]{dir[d], curr[1]});
                        }
                    }
                }
            }
            ++time;
        }
        return false;
    }

    private static boolean isMovable(int pos, int dir) {
        if (!visited[pos][dir] && map[pos][dir]=='1') {
            visited[pos][dir] = true;
            return true;
        }
        return false;
    }

}