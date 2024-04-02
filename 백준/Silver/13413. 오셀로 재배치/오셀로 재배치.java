import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            char[] from = br.readLine().toCharArray();
            char[] to = br.readLine().toCharArray();

            int bCnt = 0;
            int wCnt = 0;
            for (int i = 0; i < N; i++) {
                if (from[i]==to[i]) continue;
                if (from[i]=='W') wCnt++;
                else bCnt++;
            }
            int changePos = Math.abs(wCnt - bCnt);
            int result = Math.max(wCnt, bCnt) - changePos;
            sb.append(result + changePos).append('\n');
        }
        
        System.out.println(sb);
        br.close();
    }
}