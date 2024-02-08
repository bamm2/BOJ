import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String S;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        String T = br.readLine();

        StringBuilder tBuilder =new StringBuilder(T);
        solve(tBuilder);

        System.out.println(flag ? 1 : 0 );
        br.close();
    }

    private static void solve(StringBuilder t) {
        if (flag || S.length() > t.length()) return;
        if (S.equals(t.toString())) {
            flag = true;
            return;
        }

        if(t.charAt(t.length()-1) == 'A'){
            solve(t.deleteCharAt(t.length()-1));
            t.append('A');
        }
        if(t.charAt(t.length()-1) == 'B'){
            solve(t.deleteCharAt(t.length()-1).reverse());
        }

    }
}