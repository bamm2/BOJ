import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine())!=null) {
            int N = Integer.parseInt(input);
            long comp = 1;
            int cnt = 1;
            while (comp%N!=0) {
                comp = (comp * 10 + 1)%N;
                cnt++;
            }
            System.out.println(cnt);
        }

        br.close();
    }

}