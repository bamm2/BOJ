import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, bag, map[][], time, height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        bag = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        time = Integer.MAX_VALUE;
        height = 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (min > map[i][j]) min = map[i][j];
                if (max < map[i][j]) max = map[i][j];
            }
        }

        for (int k = min; k <= max; k++) { // 기준 높이
            int needBlock = 0; // 블록 위에 쌓기 위해 필요한 블록 수
            int plusBlock = 0; // 높은데서 빼고 인벤토리에 넣은 블록 수
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] < k) {
                        needBlock += k - map[i][j];
                    } else {
                        plusBlock += map[i][j] - k;
                    }
                }
            }
            int timeResult;
            if (needBlock <= plusBlock + bag) {
                timeResult = plusBlock * 2 + needBlock * 1;
                if (time >= timeResult) {
                    time = timeResult;
                    height = k;
                }
            }
        }

        System.out.println(time + " " + height);
    }
}