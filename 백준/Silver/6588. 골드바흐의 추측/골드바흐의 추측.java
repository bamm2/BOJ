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

        boolean[] notPrimeArr = new boolean[1_000_001];
        for (int i = 2; i <= 1_000_000; i++) {
            if (notPrimeArr[i]) continue;
            for (int j = i * 2; j <= 1_000_000; j += i) {
                notPrimeArr[j] = true;
            }
        }
        notPrimeArr[2] = true;

        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num==0) break;
            int left = 3;
            int right = num - 3;
            boolean flag = false;
            while (left <= right) {
                if( !notPrimeArr[left] && !notPrimeArr[right] ){
                    flag=true;
                    sb.append(num).append(" = ").append(left).append(" + ").append(right).append('\n');
                    break;
                }
                left++;
                right--;
            }
            if(!flag) sb.append("Goldbach's conjecture is wrong.").append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}