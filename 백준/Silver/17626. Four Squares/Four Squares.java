import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());

        int max = (int)Math.sqrt(N);

        int ans=Integer.MAX_VALUE;

        for(int i=1;i<=max;i++){
            int one = i*i;
            if(one==N) ans = 1;
            for(int j=i;j<=max;j++){
                int two= j*j;
                if(one+two==N) ans=ans>2?2:ans;
                for(int k=j;k<=max;k++){
                    int three=k*k;
                    if(one+two+three==N) ans=ans>3?3:ans;
                    for(int a=k;a<=max;a++){
                        int four=a*a;
                        if(one+two+three+four==N) ans=ans>4?4:ans;
                    }
                }
            }
        }

        System.out.println(ans);

    }
}