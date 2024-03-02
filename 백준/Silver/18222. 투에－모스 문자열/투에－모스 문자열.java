import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long k = Long.parseLong(br.readLine());

        int turning = 0;
        long number = k;
        while (true) {
            long powNumber = findClosedPowNumber(number);
            number -= (long) Math.pow(2, powNumber);
            turning++;
            if(number==0 || number==1) break; // 1이 1번째, 0으로 가버린 경우 2번째
        }

        if (number==1) { // 첫번째 숫자는 0 이므로, 나머지가 짝수번째 반복이면 0 , 홀수번째면 1
            if (turning % 2==0) System.out.println(0);
            else System.out.println(1);
        } else {  // 두번째 숫자면 1 이므로, 나머지가 짝수번째면 1 , 홀수번째면 0
            if (turning % 2==0) System.out.println(1);
            else System.out.println(0);
        }

        br.close();
    }

    private static long findClosedPowNumber(long number) {
        long compNum = 2;
        int cnt = 0;
        while (true) {
            if (compNum >= number) {
                return cnt;
            } else {
                compNum *= 2;
            }
            cnt++;
        }
    }
}