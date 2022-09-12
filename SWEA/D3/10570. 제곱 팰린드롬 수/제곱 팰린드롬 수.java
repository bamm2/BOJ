import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int T =Integer.parseInt(br.readLine());

        for(int tc=1;tc<=T;tc++){
            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            int A=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());

            int ans=0;

            int[] chk =new int[5];// 펠린드롬수 제곱수만 모아놓음 구해서 출력해보고 5이란 크기로 줄였음,,
            int idx=0;
            for(int i=1;i<=1000;i++) {
                boolean TF=false;
                int num=i;
                int revers=0;
                while(true){ // i 팰린드롬 체크
                    revers=revers*10+num%10;
                    num/=10;
                    if(num==0)break;
                }
                if(i==revers) TF=true;

                    int sqrt = i * i;
                    int reverse = 0;
                    if (sqrt <= 1000) { //기준이 1000이니 1000보다 작거나같은
                        while (true) { // 제곱수의 팰린드롬 구하기
                            reverse = reverse * 10 + sqrt % 10;
                            sqrt /= 10;
                            if (sqrt == 0) break;
                        }
                    }
                    if (i * i == reverse && TF==true) { //둘다 펠린드롬 수 일경우
                        chk[idx] = i * i;
                        idx++;
                    }
            }

            for(int i=A;i<=B;i++){
                for(int j=0;j<5;j++){
                    if(i==chk[j])
                        ans++;
                }
            }
            System.out.println("#"+tc+" "+ans);
        }//tc for
    }// main
}
