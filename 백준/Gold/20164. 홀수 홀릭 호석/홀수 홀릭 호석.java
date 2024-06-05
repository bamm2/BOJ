import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int min, max, length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String number = br.readLine();
        length = number.length();

        int oddCnt = 0;
        for (int i = 0; i < length; ++i) {
            if (isOdd(number.charAt(i))) ++oddCnt;
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        if (length >= 2) {
            divide(number, oddCnt);
        } else if (length==1) {
            if (isOdd(number.charAt(0))) {
                min = 1;
                max = 1;
            } else {
                min = 0;
                max = 0;
            }
        }

        System.out.println(min + " " + max);
        br.close();
    }

    private static void divide(String number, int cnt) {
        String result;
        if (number.length()==1) {
            min = Math.min(min, cnt);
            max = Math.max(max, cnt);
            return;
        }
        if (number.length()==2) {
            result = plus(number);
            cnt += countOdd(result);
            divide(result, cnt);
        } else { // length 가 3 이상
            for (int i = 1, len = number.length(); i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    result = calculateAndConvertToString(
                            number.substring(0, i),
                            number.substring(i, j),
                            number.substring(j)
                    );
                    int add = countOdd(result);
                    cnt += add;
                    divide(result, cnt);
                    cnt -= add;
                }
            }
        }
    }

    private static int countOdd(String number) {
        int cnt = 0;
        for (int i = 0; i < number.length(); i++) {
            if (isOdd(number.charAt(i))) cnt++;
        }
        return cnt;
    }

    private static boolean isOdd(char c) {
        return (c - '0') % 2==1;
    }

    private static String plus(String number) {
        return String.valueOf((number.charAt(0) - '0') + (number.charAt(1) - '0'));
    }

    private static String calculateAndConvertToString(String left, String mid, String right) {
        return String.valueOf(Integer.parseInt(left) + Integer.parseInt(mid) + Integer.parseInt(right));
    }

}