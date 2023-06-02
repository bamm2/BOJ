import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N , M , start, goal ,ans ;
    static List<Integer>[] list;
    static boolean[] visited;
    static int[] moving;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken()); // 1~N 번의 정거장
        M=Integer.parseInt(st.nextToken());

        list=new ArrayList[N+1];
        visited=new boolean[N+1];
        moving=new int[N+1];

        for(int i=0;i<=N;i++){
            list[i]=new ArrayList<>();
        }

        st=new StringTokenizer(br.readLine()," ");
        start=Integer.parseInt(st.nextToken());
        goal=Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine()," ");
            int from=Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            list[from].add(to); // from -> to
            list[to].add(from);
        }

        bfs(start);

        System.out.println(ans);

    }

    private static void bfs(int start){
        Queue<Integer> q =new ArrayDeque<>();
        q.offer(start);
        visited[start]=true;
        moving[start]=0;

        while(!q.isEmpty()){
            int curr =q.poll();

            if(curr==goal){
                ans=moving[curr];
                break;
            }

            int[] dir ={curr+1,curr-1};
            for (int d = 0; d < 2; d++) {
                int move = dir[d];
                if(isOut(move)) continue;
                if(visited[move]) continue;
                visited[move] =true;
                moving[move]=moving[curr]+1;
                q.offer(move);
            }

            for(int i=0 ; i<list[curr].size() ; i++ ){
                int move = list[curr].get(i);
                if(isOut(move)) continue;
                if(visited[move]) continue;
                visited[move]=true;
                moving[move]=moving[curr]+1;
                q.offer(move);
            }

        }
    }

    private static boolean isOut(int point){
        return point<=0 || point>N ;
    }
}