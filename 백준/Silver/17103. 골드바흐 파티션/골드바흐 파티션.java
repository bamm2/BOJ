import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 1_000_000; i++) {
            if (isPrime(i)) primes.add(i);
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int num = Integer.parseInt(br.readLine());
            int idx = findIdx(primes, num);
            int count = findCount(primes, idx, num);
            sb.append(count).append('\n');
        }

        System.out.println(sb);

    }

    private static int findIdx(List<Integer> primes, int num) {
        for (int i = 0; i < primes.size(); i++) {
            if (primes.get(i) >= num) return i;
        }
        return 10_000_000;
    }

    private static int findCount(List<Integer> primes, int idx, int num) {
        int count = 0;
        int left = 0;
        int right = idx;
        while (left <= right) {
            int sum = primes.get(left) + primes.get(right);
            if (sum > num) {
                right--;
            } else if (sum < num) {
                left++;
            } else {
                left++;
                count++;
            }
        }

        return count;
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i==0) return false;
        }
        return true;
    }
}
