import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());

        int day=13;
        int ans=0;
        // 1일 화요일 -> 4일 금요일
        for(int i=2019;i<=N;i++){
            int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
            if( i%4==0 && i%100!=0 || i%400==0 ) days[2]+=1;
            for(int j=1;j<=12;j++){
                if(day%7 ==4) ans++;
                day+= days[j];
            }
        }
        System.out.println(ans);
    }
}