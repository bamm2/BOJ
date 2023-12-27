import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final Integer DIV = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] count = new int[N + 2];
        int[] increase = new int[N + 2];

        count[0] = 1;
        count[1] = 1;

        increase[0] = 0;
        increase[1] = 2;
        for (int i = 2; i <= N; i++) {
            increase[i] = (increase[i - 1]%DIV + increase[i - 2]%DIV)%DIV;
        }

        for (int i = 2; i <= N; i++) {
            count[i]=(count[i-1]%DIV+increase[i-1]%DIV)%DIV;
        }

        System.out.println(count[N]);

    }
}
