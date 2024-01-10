import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static String[] arr;
    static int N, K, max;
    static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        K -= 5; // a,c,i,n,t
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            arr[i] = arr[i].replaceAll("[actin]", ""); // a,c,t,i,n 제거
        }

        int ans;
        if (K < 0) {
            ans = 0;
        } else if (K==21) {
            ans = N;
        } else {
            visited['a' - 'a'] = true;
            visited['c' - 'a'] = true;
            visited['t' - 'a'] = true;
            visited['i' - 'a'] = true;
            visited['n' - 'a'] = true;
            comb(0, 0);
            ans = max;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void comb(int start, int cnt) {
        if (cnt==K) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                boolean flag = false;
                for (int j = 0; j < arr[i].length(); j++) {
                    if (!visited[arr[i].charAt(j) - 'a']) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) count++;
            }
            if (max < count) max = count;
            return;
        }

        for (int i = start; i < 26; i++) {
            if (visited[i]) continue; // a,c,t,i,n
            visited[i] = true;
            comb(i + 1, cnt + 1);
            visited[i] = false;
        }

    }
}