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
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int goalMoney = Integer.parseInt(br.readLine());

            int[] count = new int[goalMoney + 1];

            for (int coinUnit : coins) {
                for (int money = 1; money <= goalMoney; money++) {
                    if (money - coinUnit < 0) continue;
                    if (money - coinUnit==0) count[money]++;
                    else count[money] += count[money - coinUnit];
                }
            }

            sb.append(count[goalMoney]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }
}
