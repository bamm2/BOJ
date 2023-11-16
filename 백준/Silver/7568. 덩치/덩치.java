import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] humans = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            humans[i][0] = Integer.parseInt(st.nextToken());
            humans[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int rank = 1;
            for (int j = 0; j < N; j++) {
                if (i==j) continue;
                if (humans[i][0] < humans[j][0] && humans[i][1] < humans[j][1]) {
                    rank++;
                }
            }
            sb.append(rank).append(" ");
        }

        System.out.println(sb);

    }
}