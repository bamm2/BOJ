import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K,chk[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        visited=new boolean[100001];
        chk=new int[100001];

        if(N==K) System.out.println(0);
        else solve(N);

    }

    private static void solve(int num){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(num);
        visited[num]=true;

        while(!q.isEmpty()){
            int curr=q.poll();

            int[] dir={ curr+1,curr-1,curr*2};
            for(int find:dir){
                if(find<0 || find>100000) continue;

                if(chk[find]==0) {
                    q.offer(find);
                    visited[find]=true;
                    chk[find]=chk[curr]+1;
                }

                if(find==K){
                    System.out.println(chk[find]);
                    return;
                }
            }
        }
    }

}