import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int twoCnt = 0;
        int fiveCnt =0;
        int num = 1;
        while(num++<N){
            int two = count(num,2);
            int five = count(num,5);
            twoCnt+=two;
            fiveCnt+=five;
        }
        int ans = Math.min(twoCnt, fiveCnt);

        System.out.println(ans);

    }
    private static int count(int num, int twoOrFive){
        int cnt = 0 ;
        while(num%twoOrFive==0){
            cnt++;
            num/=twoOrFive;
        }
        return cnt;
    }
}