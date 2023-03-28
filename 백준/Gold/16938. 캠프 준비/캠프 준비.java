import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,L,R,X,arr[],numbers[],ans;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        arr=new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i=2;i<=N;i++){
            numbers=new int[i];
            comb(0,0,i);
        }

        System.out.println(ans);
    }

    private static void comb(int start,int idx,int goal){
        if(idx==goal){
            int max=numbers[goal-1];
            int min=numbers[0];
            int sum=0;
            for(int num:numbers){
                sum+=num;
            }
            if(L<=sum && sum<=R && X<=(max-min)){
                ans++;
            }
            return;
        }

        for(int i=start;i<N;i++){
            numbers[idx]=arr[i];
            comb(i+1,idx+1,goal);
        }
    }
}