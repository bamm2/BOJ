import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                Integer.parseInt(st.nextToken());
                Integer.parseInt(st.nextToken());
            }

            sb.append(V - 1).append('\n');

        }

        System.out.println(sb);

    }
}