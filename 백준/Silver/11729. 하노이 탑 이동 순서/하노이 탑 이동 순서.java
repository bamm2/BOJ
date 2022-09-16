import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine()); // 원판의 개수
        sb.append( (int)(Math.pow(2,N)-1)).append('\n');
       /* 가장 큰 n번째 원판을 옮기기 위해 n-1개 원판 옮기기 , 시작->임시로 a_n-1
                n번째원판 시작 -> 목적기둥으로 1가지 경우
                임시에 있는 원판을 목적으로 옮기는 경우 a_n-1

                수식으로 a_n= a_n-1 + 1 + a_n-1
                a_n=2*a_n-1 + 1 , a1= 1
         수학적 귀납법에 의해 a_n+1 + 1 = 2(a_n + 1)
         b_n = a_n + 1  이라고 하면
          b1 =   1+1 = 2
          b_n+1 = 2*b_N ; 공비가 2 인 등비수열
          b_n = a_n + 1 = 2^n
          a_n = 2^n-1
       */

        hanoi(N,1,2,3);
        System.out.println(sb);
    }
    public static void hanoi(int N,int start,int temp,int end){
        if(N==1){
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        hanoi(N-1,start,end,temp); // N-1개 원판이 모두 가운데로 가야 N이 목적기둥에 갈 수 있으므로
        sb.append(start+" "+end+"\n");
        hanoi(N-1,temp,start,end); // 가운데 있는 원판들이 목적기둥으로 가야되므로


    }
}