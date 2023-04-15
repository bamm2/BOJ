import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Flower implements Comparable<Flower> {
        int start,end;

        Flower(int start,int end){
            this.start=start;
            this.end=end;
        }

        @Override
        public int compareTo(Flower o) {
            if(this.start==o.start){
                return o.end-this.end;
            }else{
                return this.start-o.start;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int N=Integer.parseInt(br.readLine());

        Flower[] flowers=new Flower[N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            int SH=Integer.parseInt(st.nextToken());
            int SM=Integer.parseInt(st.nextToken());
            int EH=Integer.parseInt(st.nextToken());
            int EM=Integer.parseInt(st.nextToken());

            int startTime=SH*100+SM;
            int endTime=EH*100+EM;

            flowers[i]=new Flower(startTime,endTime);
        }

        Arrays.sort(flowers);

        int start=301;
        int end=1201;
        int curr=0;
        int idx=0;
        int cnt=0;

        while(start<end){
            boolean sign=false;

            for(int i=idx;i<N;i++){
                if(flowers[i].start>start){
                    break;
                }

                if(curr < flowers[i].end){
                    sign=true;
                    curr=flowers[i].end;
                    idx=i+1;
                }
            }

            if(sign){
                start=curr;
                cnt++;
            }else{
                break;
            }
        }

        if(curr<end){
            System.out.println(0);
        }else{
            System.out.println(cnt);
        }

    }
}