import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a==0 && b==0 && c==0) break;
            int max = 0;
            int mid = 0;
            int min = 0;
            max = Math.max(Math.max(a, b), c);
            mid = Math.min(Math.max(a, b), c);
            min = Math.min(Math.min(a, b), c);
            if (max >= mid + min) {
                sb.append("Invalid").append('\n');
                continue;
            }
            HashSet<Integer> hs = new HashSet<>();
            hs.add(a);
            hs.add(b);
            hs.add(c);
            if (hs.size()==3) sb.append("Scalene");
            else if (hs.size()==2) sb.append("Isosceles");
            else sb.append("Equilateral");
            sb.append('\n');
        }
        System.out.println(sb);
        br.close();
    }
}
