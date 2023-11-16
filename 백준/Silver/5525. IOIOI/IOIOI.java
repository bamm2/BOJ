import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String s = br.readLine();

        String findStr = getFindStr(N);
        int count = 0;

        for (int i = 0; i < M; i++) {
            if (s.startsWith(findStr, i)) count++;
        }

        System.out.println(count);
    }

    private static String getFindStr(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0; i < n; i++) {
            sb.append("OI");
        }
        return sb.toString();
    }
}