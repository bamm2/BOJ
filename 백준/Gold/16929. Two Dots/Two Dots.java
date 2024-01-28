import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[][] visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        loop:
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j]!=0) continue;
                bfs(i, j);
                if(flag) break loop;
            }
        }

        bw.write(flag ? "Yes":"No");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = 1;

        List<int[]> memos;
        while (!q.isEmpty()) {
            int size = q.size();
            memos =new ArrayList<>();
            while (size-- > 0) {
                int[] curr = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dir[d][0];
                    int nc = curr[1] + dir[d][1];
                    if (isOut(nr, nc) || map[nr][nc]!=map[r][c]) continue;
                    if(visited[nr][nc]!=0) continue;
                    memos.add(new int[]{nr,nc});
                    q.offer(new int[]{nr,nc});
                }
            }
            for(int[] memo : memos){
                visited[memo[0]][memo[1]]++;
                if(visited[memo[0]][memo[1]]==2) {
                    flag=true;
                    return;
                }
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}