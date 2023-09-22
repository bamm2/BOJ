import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,E;
    static boolean flag =false;
    static boolean[] visited;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        list=new ArrayList[V+1];

        for(int i=1;i<=V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from =Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            list[from].add(to);
            list[to].add(from);
        }

        for(int i=1;i<=V;i++){
            if(flag) break;
            bfs(i);
        }

        if(flag) System.out.println("Big World!");
        else System.out.println("Small World!");

    }

    private static void bfs(int i) {
        visited=new boolean[V+1];
        visited[i]=true;
        int cnt = 1;
        int distance = -1;
        Queue<Integer> q =new ArrayDeque<>();
        q.offer(i);

        while (!q.isEmpty()){
            int size = q.size();

            while (size-->0){
                Integer poll = q.poll();
                for(Integer curr: list[poll]){
                    if(visited[curr]) continue;
                    visited[curr]=true;
                    cnt++;
                    q.offer(curr);
                }
            }

            distance++;
            if(distance > 6){
                flag=true;
                break;
            }
        }

        if(cnt!=V) flag=true;

    }
}