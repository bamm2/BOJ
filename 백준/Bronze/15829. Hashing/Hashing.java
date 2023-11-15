import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final Integer R = 31;
    private static final Integer primeNumber = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());
        String s =br.readLine();
        double sum = 0;
        for(int i=0;i<N;i++){
            int alphabet=s.charAt(i)-'a'+1;
            double pow = alphabet * Math.pow(R, i);
            sum+=pow;
        }

        System.out.println((long)(sum % primeNumber));

    }
}