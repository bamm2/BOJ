import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, size;
    static long ans;
    static long[] AB, CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        size = (int) Math.pow(N, 2);
        AB = new long[size];
        CD = new long[size];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[i * N + j] = A[i] + B[j];
                CD[i * N + j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        twoPointer();

        System.out.println(ans);
        br.close();
    }

    private static void twoPointer() {
        int left = 0;
        int right = size - 1;

        while (left < size && right >= 0) {
            long l = AB[left];
            long r = CD[right];
            long sum = l + r;
            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                long lCnt = 0;
                while (left < size && l==AB[left]) {
                    left++;
                    lCnt++;
                }
                long rCnt = 0;
                while (right >= 0 && r==CD[right]) {
                    right--;
                    rCnt++;
                }
                ans += lCnt * rCnt;
            }
        }
    }

}