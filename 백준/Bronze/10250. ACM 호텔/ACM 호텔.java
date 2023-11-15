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
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int floor = 0;
            int ho = 1;
            while (N-- > 0) {
                if (++floor>H) {
                    floor = 1;
                    ho++;
                }
            }
            if(ho<10) sb.append(floor).append("0").append(ho).append('\n');
            else sb.append(floor).append(ho).append('\n');
        }

        System.out.println(sb);
    }
}