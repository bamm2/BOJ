import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            String numOne = st.nextToken();
            String numTwo = st.nextToken();
            sb.append(result(numOne, numTwo)).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static String result(String numOne, String numTwo) {
        StringBuilder sb = new StringBuilder();

        int oneIdx = numOne.length();
        int twoIdx = numTwo.length();
        boolean plus = false;
        while (oneIdx >= 0 || twoIdx >= 0) {
            oneIdx--;
            twoIdx--;
            int sum = plus ? 1:0;
            if (oneIdx >= 0 && numOne.charAt(oneIdx)=='1') sum++;
            if (twoIdx >= 0 && numTwo.charAt(twoIdx)=='1') sum++;
            if (sum==3) {
                plus = true;
                sb.insert(0, '1');
            } else if (sum==2) {
                plus = true;
                sb.insert(0, '0');
            } else if (sum==1) {
                plus = false;
                sb.insert(0, '1');
            } else {
                plus = false;
                sb.insert(0, '0');
            }

        }
        while (sb.length()!=1 && sb.charAt(0)=='0') sb.deleteCharAt(0);
        return sb.toString();
    }
}
