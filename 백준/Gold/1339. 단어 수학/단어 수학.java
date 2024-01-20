import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N, ans;
    static String[] arr;
    static List<Character> list = new ArrayList<>();
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr =new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            for (int j = 0; j < arr[i].length(); j++) {
                char c = arr[i].charAt(j);
                if (!list.contains(c)) list.add(c);
            }
        }

        score = new int[list.size()];

        solve(0, 0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve(int cnt, int flag) {
        if (cnt==list.size()) {
            ans = Math.max(ans,sum());
            return;
        }

        for (int i = 0; i < 10; i++) {
            if ((flag & (1 << i))!=0) continue;
            score[cnt] = i;
            solve(cnt + 1, flag | (1 << i));
        }
    }

    private static int sum() {
        int sum = 0;
        for (int i = 0; i < N; i++) { // 문자열
            String curr = arr[i];
            int num = 0;
            for (int j = 0; j<curr.length();j++){
                char c = curr.charAt(j);
                int charIdx = list.indexOf(c);
                int point = score[charIdx];
                num = num*10 + point;
            }
            sum+=num;
        }

        return sum;
    }

}