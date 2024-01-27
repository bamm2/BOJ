import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int N, map[][] ,ans;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans=Integer.MAX_VALUE;
        selected = new boolean[N];
        for (int i = 2; i <= N / 2; i++) {
            comb(0, 0, i);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void comb(int cnt, int start, int pickGoal) {
        if (cnt==pickGoal) {
            ans = Math.min(ans,findSum());
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            comb(cnt + 1, i + 1, pickGoal);
            selected[i] = false;
        }
    }

    private static int findSum() {
        int teamOne = 0;
        int teamTwo = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected[i] && selected[j]) {
                    teamOne += map[i][j];
                } else if (!selected[i] && !selected[j]) {
                    teamTwo += map[i][j];
                }
            }
        }
        return Math.abs(teamOne-teamTwo);
    }
}