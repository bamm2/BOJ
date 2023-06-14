import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] arr = new int[A];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < A; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int ans = 0;

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < B; i++) {
                int num = Integer.parseInt(st.nextToken());
                for (int j = A - 1; j >= 0; j--) {
                    if (arr[j] > num) ans++;
                    else break;
                }
            }

            sb.append(ans).append('\n');
        }

        System.out.println(sb.toString().trim());

        bw.flush();
        bw.close();
        br.close();
    }
}
