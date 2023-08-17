import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (M > N) {
                sb.append(solve(M, N, x, y)).append('\n');
            } else {
                sb.append(solve(N, M, y, x)).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int solve(int maxTime, int minTime, int bigTime, int smallTime) {

        int cnt = 0;
        int lastFindCnt = 1;
        int modNum = maxTime % minTime;
        int diff = maxTime - minTime;

        while (modNum != 0) {
            modNum = (modNum + diff) % minTime;
            lastFindCnt++;
        }

        modNum = bigTime >= minTime ? bigTime % minTime : bigTime;
        if(bigTime%minTime ==0 ) modNum = minTime;

        while (modNum != smallTime) {
            modNum = (modNum + diff) % minTime;
            if (modNum == 0) modNum = minTime;
            cnt++;
            if(cnt>lastFindCnt) break;
        }

        if (cnt > lastFindCnt) return -1;
        else return cnt * maxTime + bigTime;

    }
}