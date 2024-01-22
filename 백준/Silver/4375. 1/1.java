import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine())!=null && !input.isEmpty()) {
            int N = Integer.parseInt(input);
            BigInteger comp = BigInteger.ONE;
            BigInteger div = BigInteger.valueOf(N);
            int cnt = 1;
            while (true) {
                if (comp.remainder(div) == BigInteger.ZERO ) {
                    sb.append(cnt).append('\n');
                    break;
                }
                cnt++;
                comp = comp.multiply(BigInteger.TEN).add(BigInteger.ONE);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}