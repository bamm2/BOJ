import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        String pattern = "^(100+1+|01)+$";

        boolean matches = Pattern.matches(pattern,s);

        System.out.println(matches ? "SUBMARINE" : "NOISE");

    }
}