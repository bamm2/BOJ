import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int left = 0;
        int right = 0;

        int cnt = 0;
        int sum = 0;
        while (left <= right) {
            if (sum > N) {
                sum -= ++left;
            } else if (sum < N) {
                sum += ++right;
            } else {
                cnt++;
                sum += ++right;
            }
            if (right > N) break;
        }

        System.out.println(cnt);
        br.close();
    }
}