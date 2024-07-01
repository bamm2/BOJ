import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_HEIGHT = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] heights = new int[MAX_HEIGHT];

        int N = Integer.parseInt(br.readLine());

        int result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (heights[num]==0) {
                ++result;
            } else {
                --heights[num];
            }
            ++heights[num - 1];
        }

        System.out.println(result);
        br.close();
    }
}