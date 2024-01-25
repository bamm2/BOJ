import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, max, maps[];
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        selected = new int[K];
        visited = new boolean[R * C];

        maps = new int[R * C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                maps[i * C + j] = Integer.parseInt(st.nextToken());
            }
        }

        max = Integer.MIN_VALUE;
        comb(0, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void comb(int cnt, int start) {
        if (cnt==K) {
            int sum =0;
            for (int i = 0; i < K; i++) {
                sum+=maps[selected[i]];
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = start; i < R * C; i++) {
            if(isClosed(i)) continue;
            selected[cnt] = i;
            visited[i] = true;
            comb(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    private static boolean isClosed(int idx) {
        for(int d=0;d<4;d++){
            int curR = idx/C;
            int curC = idx%C;
            int nr = curR+dir[d][0];
            int nc = curC+dir[d][1];
            if(isOut(nr,nc)) continue;
            if(visited[nr*C+nc]) return true;
        }
        return false;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}