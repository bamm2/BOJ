import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long[] arr = new long[N];
        long turningPoint = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            turningPoint += arr[i];
        }

        int number;
        if (K >= turningPoint) {
            K -=  turningPoint;
            number = N;
            while (true) {
                K -= arr[number - 1];
                if (K < 0) break;
                --number;
            }
        } else {
            number = 1;
            while (true) {
                K -= (int) arr[number - 1];
                if (K < 0) break;
                ++number;
            }
        }

        System.out.println(number);
        br.close();
    }
}