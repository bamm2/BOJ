import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

    static int[] vNumbering;
    static int V, E;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            list = new ArrayList[V + 1];
            vNumbering = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
                list[to].add(from);
            }

            boolean flag=false;
            for (int i = 1; i <= V; i++) {
                if(!isBG(i)) {
                    flag=true;
                    sb.append("NO").append('\n');
                    break;
                }
            }
            if(!flag) sb.append("YES").append('\n');
        }

        System.out.println(sb);

    }

    private static boolean isBG(int v) {
        Queue<Integer> q = new ArrayDeque<>();
        if(vNumbering[v]==0) {
            q.offer(v);
            vNumbering[v]=1;
        }

        while(!q.isEmpty()){
            Integer curr =q.poll();
            for(Integer next : list[curr]){
                if(vNumbering[next]==0){
                    q.offer(next);
                    if(vNumbering[curr]==1) vNumbering[next]=2;
                    else if(vNumbering[curr]==2) vNumbering[next]=1;
                }else{
                    if(vNumbering[curr]==vNumbering[next]) return false;
                }
            }
        }

        return true;
    }
}