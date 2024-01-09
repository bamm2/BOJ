import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int round = 0;
        while (a!=0 && b!=0) {
            round++;
            if (isSatisfied(a, b)) break;
            a = findNextNumber(a);
            b = findNextNumber(b);
        }

        if (a==0 || b==0) round = -1; // 가능한가 ?

        bw.write(String.valueOf(round));
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isSatisfied(int a, int b) {
        return (a > b && a % 2==0 && b % 2==1 && (b + 1) / 2==a / 2)
                || (a < b && a % 2==1 && b % 2==0 && (a + 1) / 2==b / 2);
    }

    private static int findNextNumber(int number) {
        return number % 2==0 ? number / 2:(number / 2) + 1;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= 5 || c >= 5;
    }
}