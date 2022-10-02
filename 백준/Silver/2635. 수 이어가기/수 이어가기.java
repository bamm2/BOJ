import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int maxcnt=Integer.MIN_VALUE;
        int chkidx=0;
        // 0 ~ N까지 수 모두 체크해보기
        for(int i=1;i<=N;i++){
            int start=N;
            int next=i;
            int idx=i;
            int cnt=1;
            while(true){
                int tmp=start-next;
                start=next;
                next=tmp;
                cnt++;
                if(start-next<0){
                    cnt++;
                    break;
                }
            }
            if(cnt>maxcnt){
                maxcnt=cnt;
                chkidx=idx;
            }
        }
        if(maxcnt==Integer.MIN_VALUE) sb.append(0);
        else sb.append(maxcnt).append('\n');
        int start=N;
        int next=chkidx;
        while(true){
            if(start==0 && next==0) break;

            sb.append(start+" ");
            int tmp=start-next;
            start=next;
            next=tmp;
            if(start-next<0){
                sb.append(start+" "+next);
                break;
            }
        }

        System.out.println(sb);



    } // main
}