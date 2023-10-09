import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int NANO = 10_000_000;
    static int arr[], goal, N, ansL, ansR, ansDiff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str;

        while ((str = br.readLine()) != null && !str.isEmpty()) {
            goal = Integer.parseInt(str) * NANO;
            N = Integer.parseInt(br.readLine());
            ansDiff = -1;
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);
            int left= 0;
            int right=N-1;
            while (left<right) {
                int sum = arr[left] + arr[right];

                if (sum == goal) {
                    ansDiff = Math.abs(arr[left] - arr[right]);
                    ansL = arr[left];
                    ansR = arr[right];
                    break;
                } else if (sum > goal) {
                    right--;
                } else {
                    left++;
                }
            }

            if (ansDiff != -1) sb.append("yes ").append(ansL).append(" ").append(ansR).append('\n');
            else sb.append("danger").append('\n');
        }

        System.out.println(sb);

    }

}
