import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, find;
    static List<Integer>[] list; // 번호보다 낮은 순위 모아 놓음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        find = Integer.parseInt(st.nextToken()) - 1;

        list = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken()) - 1;
            int smaller = Integer.parseInt(st.nextToken()) - 1;
            list[v].add(smaller);
        }

        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        visited[find] = true;
        q.offer(find);

        int under = 0;
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            for (Integer next : list[curr]) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
                under++;
            }
        }

        int over =0 ;
        for (int i = 0; i < V; i++) {
            if (visited[i]) continue;
            if (list[i].contains(find)) {
                over++;
                while (!q.isEmpty()) {
                    Integer curr = q.poll();
                    for(int next: list[curr] ){
                        if(visited[next]) continue;
                        visited[next]=true;
                        q.offer(next);
                        over++;
                    }
                }
            }
        }

        int minRank = V-under;
        int maxRank = over+1;
        sb.append(maxRank+" "+minRank);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}