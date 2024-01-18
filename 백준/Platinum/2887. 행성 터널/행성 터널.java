import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V,parents[];
    static class Points implements Comparable<Points> {
        int from, to, abs;

        public Points(int from, int to, int abs) {
            this.from = from;
            this.to = to;
            this.abs = abs;
        }

        @Override
        public int compareTo(Points o) {
            return Integer.compare(this.abs, o.abs);
        }
    }

    static class Point implements Comparable<Point> {
        int v, cost;

        public Point(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        List<Point> a = new ArrayList<>();
        List<Point> b = new ArrayList<>();
        List<Point> c = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a.add(new Point(i, Integer.parseInt(st.nextToken())));
            b.add(new Point(i, Integer.parseInt(st.nextToken())));
            c.add(new Point(i, Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Points> pq =new PriorityQueue<>();

        Collections.sort(a);
        Collections.sort(b);
        Collections.sort(c);
        for(int i=1;i<V;i++){
            pq.offer(new Points(a.get(i).v,a.get(i-1).v, Math.abs(a.get(i).cost-a.get(i-1).cost)));
            pq.offer(new Points(b.get(i).v,b.get(i-1).v, Math.abs(b.get(i).cost-b.get(i-1).cost)));
            pq.offer(new Points(c.get(i).v,c.get(i-1).v, Math.abs(c.get(i).cost-c.get(i-1).cost)));
        }

        make();
        
        int pick=1;
        int sum =0;
        while (!pq.isEmpty()){
            Points curr = pq.poll();
            if(union(curr.from,curr.to)){
                pick++;
                sum+=curr.abs;
            }
            if(pick==V) break;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void make(){
        parents=new int[V];
        for(int i=0;i<V;i++){
            parents[i]=i;
        }
    }

    private static int find(int a){
        if(parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a,int b){
        int aRoot =find(a);
        int bRoot =find(b);
        if(aRoot==bRoot) return false;

        parents[bRoot]= aRoot;
        return true;
    }

}