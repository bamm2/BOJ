import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V;
    static boolean[] visited;
    static List<Point>[] list;
    static class Point implements Comparable<Point>{
        int from,to,weight;
        Point(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Point o) {
            return  Integer.compare(this.weight,o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        V=Integer.parseInt(br.readLine());
        list=new ArrayList[V];
        visited=new boolean[V];
        for(int i=0;i<V;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<V;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<V;j++){
                int w = Integer.parseInt(st.nextToken());
                if(i!=j) list[i].add(new Point(i,j,w));
            }
        }

        PriorityQueue<Point> pq =new PriorityQueue<>();

        pq.addAll(list[0]);
        visited[0]=true;

        int count = 1;
        long sum =0;
        while (count!=V){
            Point curr = pq.poll();

            if(visited[curr.to]) continue;
            visited[curr.to]=true;

            sum+=curr.weight;
            pq.addAll(list[curr.to]);
            count++;
        }

        System.out.println(sum);


    }
}