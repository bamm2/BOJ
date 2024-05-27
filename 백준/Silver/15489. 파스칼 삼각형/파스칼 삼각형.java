import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] map = new int[31][31];
        for (int i = 1; i <= 30; i++) {
            for (int j = 1; j <= i; j++) {
                if (j==1 || i==j) map[i][j] = 1;
                else map[i][j] = map[i - 1][j - 1] + map[i - 1][j];

            }
        }

        long sum = 0;
        int plus = 0;
        for (int i = R; ; i++) {
            for (int j = C; j <= C + plus; j++) {
                sum += map[i][j];
            }
            plus++;
            if (plus==W) break;
        }
        
        System.out.println(sum);
        br.close();
    }
}