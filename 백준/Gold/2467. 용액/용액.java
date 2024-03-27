import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] result = new int[3];
        result[2] = Integer.MAX_VALUE;
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int mixedValue = Math.abs(arr[left] + arr[right]);
            if (result[2] > mixedValue) {
                result[2] = mixedValue;
                result[0] = arr[left];
                result[1] = arr[right];
            }

            int leftComp = Math.abs(arr[left] + arr[right - 1]);
            int rightComp = Math.abs(arr[right] + arr[left + 1]);
            if (leftComp <= rightComp) {
                right--;
            } else {
                left++;
            }

        }

        System.out.println(result[0] + " " + result[1]);
        br.close();
    }
}