import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int pos = -1;
        int max = 0;
        for (int i = 0; i < M; i++) {
            int currPos = arr[i];
            if (pos==-1) {
                max = currPos;
                pos = currPos;
            } else {
                max = Math.max(max, (currPos - pos) % 2==0 ? (currPos - pos) / 2:(currPos - pos) / 2 + 1);
                pos = currPos;
            }
        }
        if (pos!=N) max = Math.max(N - pos, max);


        System.out.println(max);
        br.close();
    }
}