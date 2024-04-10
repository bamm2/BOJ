import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String s = st.nextToken();
        String str = st.nextToken();

        int min = 99;
        for (int i = 0; i <= str.length() - s.length(); i++) {
            int cnt = 0;
            for (int j = 0; j < s.length(); j++) {
                if(str.charAt(j+i) != s.charAt(j)) cnt++;
            }
            min = Math.min(min,cnt);
        }

        System.out.println(min);
        br.close();
    }
}