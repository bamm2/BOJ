import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int aNumerator = Integer.parseInt(st.nextToken());
        int aDenominator = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int bNumerator = Integer.parseInt(st.nextToken());
        int bDenominator = Integer.parseInt(st.nextToken());

        int numerator = aNumerator * bDenominator + bNumerator * aDenominator;
        int denominator = aDenominator * bDenominator;

        int gcd = numerator> denominator ? gcd(numerator,denominator) : gcd(denominator,numerator);

        System.out.println(numerator/gcd+" "+denominator/gcd);
    }

    private static int gcd(int a, int b) {
        if(b==0) return a;
        return gcd( b, a%b);
    }
}