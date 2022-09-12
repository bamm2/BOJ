import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static char[] chk = {'A', 'B', 'C', 'D', 'E', 'F'};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            String ans = "";
            boolean firstchk = false;
            boolean lastchk = false;
            for (int j = 0; j < chk.length; j++) {
                if (s.charAt(0) != chk[j]) {
                    firstchk = true;
                    ans = "Good";
                } else {
                    ans = "Infected!";
                    firstchk = false;
                    break;
                }
            }

            if (firstchk) {
                System.out.println(ans);
                continue;
            }

            for (int j = 0; j < chk.length; j++) {
                if (s.charAt(s.length() - 1) != chk[j]) {
                    ans = "Good";
                    lastchk = true;
                } else {
                    ans = "Infected!";
                    lastchk = false;
                    break;
                }
            }


            if (lastchk) {
                System.out.println(ans);
                continue;
            }

            int aidx = 0;
            int fidx = 0;
            int cidx = 0;

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'A' && aidx == 0)
                    aidx = j;
                if (s.charAt(j) == 'F' && fidx == 0)
                    fidx = j;
                if (s.charAt(j) == 'C' && cidx == 0)
                    cidx = j;
            }
            int cnt = 0;
            int idx = 0;
            for (int j = cidx + 1; j < s.length(); j++) {
                for (int k = 0; k < chk.length; k++) {
                    if (s.charAt(j) == chk[k]) {
                        cnt++;
                    }
                }
                if (cnt == 1) {
                    idx = j;
                    break;
                }
            }
            if (cnt == 1 && idx != s.length() - 1) {
                System.out.println("Good");
                continue;
            }


            if (s.charAt(0) == 'A') aidx = 0;
            if (aidx >= 0 && fidx > 0 && cidx > 0 && aidx < fidx && fidx < cidx) {
                ans = "Infected!";
            } else {
                ans = "Good";
            }


            System.out.println(ans);
        }// tc for
    }//main
}