import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int from, to, N, M ;
    static int ans=Integer.MAX_VALUE;
    static boolean[] visited;
    static List<Integer>[] list;
    static class Point{
        int next,cnt;
        Point(int next,int cnt){
            this.next=next;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        from =Integer.parseInt(st.nextToken())-1;
        to =Integer.parseInt(st.nextToken())-1;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        list=new ArrayList[N];
        visited=new boolean[N];
        for(int i=0;i<N;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from = Integer.parseInt(st.nextToken())-1;
            int to =Integer.parseInt(st.nextToken())-1;
            list[from].add(to);
            list[to].add(from);
        }

        bfs();

        if(ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void bfs(){
        Queue<Point> q= new ArrayDeque<>();
        q.offer(new Point(from,0));
        visited[from]=true;

        while(!q.isEmpty()){
            Point curr =q.poll();
            if(curr.next==to){
              ans= curr.cnt;
              break;
            }
            for(int i=0;i<list[curr.next].size();i++){
                if(visited[list[curr.next].get(i)]) continue;
                visited[list[curr.next].get(i)]=true;
                q.offer(new Point(list[curr.next].get(i),curr.cnt+1));
            }
        }
    }
}