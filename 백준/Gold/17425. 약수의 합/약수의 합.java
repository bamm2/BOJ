import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        long[] arr = new long[1_000_001]; // f(x)
        long[] sumArr = new long[1_000_001]; // g(x)

        for (int i = 1; i <= 1_000_000; i++) {
            int num = i;
            for (int j = i; j <= 1_000_000; j += num) {
                arr[j] += num;
            }
        }

        for (int i = 1; i <= 1_000_000; i++) {
            sumArr[i] += sumArr[i - 1] + arr[i];
        }

        while (T-- > 0) {
            int num = Integer.parseInt(br.readLine());
            sb.append(sumArr[num]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}