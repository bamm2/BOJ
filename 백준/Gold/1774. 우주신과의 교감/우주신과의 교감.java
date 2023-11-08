import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static List<int[]> godPosition = new ArrayList<>();

    static class Point implements Comparable<Point> {
        int from, to;
        double distance;

        public Point(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    static PriorityQueue<Point> pq = new PriorityQueue<>();
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int connect = Integer.parseInt(st.nextToken());

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            godPosition.add(new int[]{r, c});
        }

        for (int i = 0; i < connect; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            pq.offer(new Point(from, to, 0));
        }

        for (int i = 0; i < V - 1; i++) {
            int[] from = godPosition.get(i);
            for (int j = i + 1; j < V; j++) {
                int[] to = godPosition.get(j);
                double distance = getDistance(from[0], from[1], to[0], to[1]);
                pq.offer(new Point(i, j, distance));
            }
        }

        make();
        int pick = 0;
        double dis = 0;
        while (!pq.isEmpty()){
            Point curr = pq.poll();
            if(union(curr.from,curr.to)){
                pick++;
                dis+=curr.distance;
            }
            if(pick==V-1) break;
        }
        printAnswer(dis);
    }

    private static void make(){
        parents=new int[V];
        for(int i=0;i<V;i++){
            parents[i]=i;
        }
    }

    private static int find(int a){
        if(parents[a]==a) return a;
        return parents[a]=find(parents[a]);
    }

    private static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB ) return false;

        parents[rootB]= rootA;
        return true;
     }

    private static double getDistance(int r, int c, int r2, int c2) {
        return Math.sqrt(Math.pow(Math.abs(r - r2), 2) + Math.pow(Math.abs(c - c2), 2));
    }

    private static void printAnswer(double sum) {
        sum *= 100;
        System.out.println(String.format("%.2f",(double)Math.round(sum)/100));
    }
}