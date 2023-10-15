import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int T =Integer.parseInt(br.readLine());
        while (T-->0){
            int num =Integer.parseInt(br.readLine());
            int left = num/2;
            int right = num/2;
            while (left>1 && right<=num) {
                if (isPrime(left) && isPrime(right)) {
                    break;
                }
                left--;
                right++;
            }
            sb.append(left).append(" ").append(right).append('\n');
        }

        System.out.println(sb);

    }

    private static boolean isPrime(int num) {
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0) return false;
        }
        return true;
    }
}