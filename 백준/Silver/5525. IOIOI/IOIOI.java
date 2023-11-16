import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String s = br.readLine();

        String pattern = getFindStr(N);
        makeTable(pattern);

        int count = 0;

        int idx = 0;
        for (int i = 0; i < M; i++) {

            while (idx > 0 && s.charAt(i)!=pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (s.charAt(i)==pattern.charAt(idx)) {
                if (idx==pattern.length() - 1) {
                    idx = table[idx];
                    count++;
                } else {
                    idx++;
                }
            }
        }

        System.out.println(count);

    }

    private static void makeTable(String pattern) {
        table = new int[pattern.length()];

        int idx = 0;
        for (int i = 1; i < table.length; i++) {

            while (idx > 0 && pattern.charAt(idx)!=pattern.charAt(i)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(idx)==pattern.charAt(i)) {
                idx += 1;
                table[i] = idx;
            }
        }
    }

    private static String getFindStr(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0; i < n; i++) {
            sb.append("OI");
        }
        return sb.toString();
    }
}