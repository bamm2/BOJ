import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static class Point {
        double r, c;

        Point(double r, double c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Star implements Comparable<Star> {
        int to;
        double weight;

        Star(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Star o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static Point[] points;
    static boolean[] visited;
    static List<Star>[] weightList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        points = new Point[N];
        visited = new boolean[N];

        weightList=new ArrayList[N];
        for(int i=0;i<N;i++){
            weightList[i]=new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            double r = Double.parseDouble(st.nextToken());
            double c = Double.parseDouble(st.nextToken());
            points[i] = new Point(r, c);
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                double diff = round(diff(points[i], points[j]));
                weightList[i].add(new Star(j,diff));
                weightList[j].add(new Star(i,diff));
            }
        }

        double sum = 0;

        PriorityQueue<Star> pq =new PriorityQueue<>();
        pq.offer(new Star(0,0));

        while (!pq.isEmpty()){
            Star curr = pq.poll();
            if(visited[curr.to]) continue;
            visited[curr.to]=true;

            for(Star next: weightList[curr.to]){
                if(!visited[next.to]) pq.offer(next);
            }

            sum+=curr.weight;
        }

        System.out.println(sum);

    }

    private static double round(Double num) {
        return (double) ((int) (num * 100)) / 100;
    }

    private static double diff(Point x, Point y) {
        return Math.sqrt(Math.pow(Math.abs(x.r - y.r), 2) + Math.pow(Math.abs(x.c - y.c), 2));
    }
}