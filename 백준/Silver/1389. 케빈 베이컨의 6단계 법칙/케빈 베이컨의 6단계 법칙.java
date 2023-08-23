import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static int V,E,min,idx;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        list=new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        min=Integer.MAX_VALUE;

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from =Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        for(int i=1;i<=V;i++) {
            findConnect(i);
        }

        System.out.println(idx);

    }
    private static void findConnect(int v){
        boolean[] visited =new boolean[V+1];
        Queue<Integer> q =new ArrayDeque<>();

        q.offer(v);
        visited[v]=true;

        int sum = 0;
        int score = 1;
        while(!q.isEmpty()){
            int size =q.size();
            while(size-->0){
                Integer curr =q.poll();

                for(Integer next : list[curr]){
                    if(visited[next]) continue;
                    visited[next]=true;
                    q.offer(next);
                    sum+=score;
                }
            }
            score++;
        }

        if(min>sum) {
            min=sum;
            idx=v;
        }
        
    }
}