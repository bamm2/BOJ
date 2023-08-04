import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Meeting{
        int time,price;
        Meeting(int time,int price){
            this.time=time;
            this.price=price;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N=Integer.parseInt(br.readLine());

        Meeting[] meetings =new Meeting[N+1];
        int[] dp =new int[N+1];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            meetings[i]=new Meeting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        meetings[N] = new Meeting(0,0);

        int ans = 0;
        for(int i=0;i<=N;i++){

            if(ans < dp[i]) ans =dp[i];

            Meeting curr =meetings[i];

            if(i+curr.time<=N){
                dp[i+curr.time] = Math.max(dp[i+curr.time],ans+curr.price);
            }
        }

        System.out.println(ans);

    }
}