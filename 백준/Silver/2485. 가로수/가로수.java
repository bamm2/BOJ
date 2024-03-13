import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> diffs = new ArrayList<>();

        int prev = -1;
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if (prev==-1) prev = number;
            else {
                int diff = number - prev;
                diffs.add(diff);
                prev = number;
            }
        }
        Collections.sort(diffs);
        int gcd = findGCD(diffs);
        int cnt = 0;
        for (Integer number : diffs) {
            int remain = number / gcd - 1;
            if (remain!=0) cnt += remain;
        }

        System.out.println(cnt);
        br.close();
    }

    private static int findGCD(List<Integer> list) {
        int max = list.get(0);
        int gcd = 1;
        int div = 1;
        while (true) {
            boolean flag = true;
            for (Integer number : list) {
                if (number % div!=0) {
                    flag = false;
                    break;
                }
            }
            if (max < div) break;
            if (flag) {
                gcd = div;
            }
            div++;
        }
        return gcd;
    }
}