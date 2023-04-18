import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,K;
    static int ans=Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        visited=new boolean[200001];
        number=new int[200001];

        solve(N);

        System.out.println(ans);

    }
    private static void solve(int pos){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(pos);
        visited[pos]=true;

        while(!q.isEmpty()){
            int num =q.poll();
            if(num==K && ans>number[num]){
                ans=number[num];
                break;
            }
            int[] dir={num+1,num-1,num*2};
            for(int d=2;d>=0;d--) {
                int curr=dir[d];
                if( curr > 200000 || curr < 0) continue ;
                if(visited[curr]) continue ;
                visited[curr] = true;
                if(d==2) {
                    number[curr]=number[num];
                }else{
                    number[curr]=number[num]+1;
                    visited[curr]=true;
                }
                q.offer(curr);
            }
        }
    }
}