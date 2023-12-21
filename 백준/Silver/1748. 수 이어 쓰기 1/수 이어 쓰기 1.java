import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int multiple = 1;
        int standard = 9;
        int sum = 0;
        while (true) {
            if (N <= standard) {
                sum += N * multiple;
                break;
            }
            N -= standard;
            sum += multiple * standard;
            standard *= 10;
            multiple++;
        }

        System.out.println(sum);

    }
}