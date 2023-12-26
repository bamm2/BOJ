import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        double[] arr = new double[N];

        double max = 0;
        for (int i = 0; i < arr.length; i++) {
            double number = Double.parseDouble(br.readLine());
            if (i==0) arr[i] = number;
            else {
                arr[i] = Math.max(number, arr[i - 1] * number);
            }
            max = Math.max(arr[i], max);
        }

        System.out.println(String.format("%.3f", max));

    }
}
