import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static List<Point>[] list;
    static boolean[] isSatisfied;

    static class Point {
        int v, cost;

        public Point(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int candidateCnt = Integer.parseInt(st.nextToken());

            list = new ArrayList[V + 1];
            for (int i = 0; i <= V; i++) {
                list[i] = new ArrayList<>();
            }
            isSatisfied = new boolean[V + 1];

            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list[from].add(new Point(to, cost));
                list[to].add(new Point(from, cost));
            }

            initCandidates( g, h, search(start), search(g), search(h));

            List<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < candidateCnt; i++) {
                int v = Integer.parseInt(br.readLine());
                if (isSatisfied[v]) candidates.add(v);
            }

            Collections.sort(candidates);
            for (Integer candidate : candidates) {
                sb.append(candidate).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static void initCandidates(int g, int h, int[] searchDist, int[] gDist, int[] hDist) {
        for (int i = 1; i <= V; i++) {
            int sumA = searchDist[g] + gDist[h] + hDist[i];
            int sumB = searchDist[h] + hDist[g] + gDist[i];
            int min = Math.min(sumA, sumB);
            if (searchDist[i]==min) isSatisfied[i] = true;
        }
    }

    private static int[] search(int start) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});

        int[] dist = new int[V + 1];
        Arrays.fill(dist, 987_654_321);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (Point next : list[curr[0]]) {
                if (dist[next.v] > curr[1] + next.cost) {
                    dist[next.v] = curr[1] + next.cost;
                    q.offer(new int[]{next.v, dist[next.v]});
                }
            }
        }
        return dist;
    }

}
