import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static String goal;
    static int goalNumber, min;
    static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        goal = br.readLine();
        goalNumber = Integer.parseInt(goal);

        int K = Integer.parseInt(br.readLine());

        broken = new boolean[10];
        if (K!=0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < K; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        min = Math.abs(goalNumber - 100);

        dfs("", 0);

        System.out.println(min);

    }

    private static void dfs(String number, int cnt) {
        if (number.length()!=0 && number.length()<=goal.length()+1) {
            int num = Integer.parseInt(number);
            int move = Math.abs(goalNumber - num);
            min = Math.min(min, move + cnt);
            if(number.length() == goal.length()+1) {
                return;
            }
        }

        if (cnt >= min) return;

        for (int i = 0; i < 10; i++) {
            if (broken[i]) continue;
            dfs(number + i, cnt + 1);
        }

    }
}