import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if (R==1) {
            ans = 1;
        } else if (R==2) {
            ans = Math.min(4, (C + 1) / 2); // 가로 최대 이동 가능 범위 : 4
        } else { // R이 3보다 크거나 같을 경우
            if (C < 7) { // C가 7보다 작으면
                ans = Math.min(4, C);
            } else { // C가 7보다 크거나 같으면
                ans = C - 2;
            }
        }

        System.out.println(ans);
        br.close();

    }
}