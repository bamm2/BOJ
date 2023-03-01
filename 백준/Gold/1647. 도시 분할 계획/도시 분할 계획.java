import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static int[] parents;
    static class Point implements Comparable<Point>{
        int from,to,w;
        Point(int from,int to,int w){
            this.from=from;
            this.to=to;
            this.w=w;
        }

        @Override
        public int compareTo(Point o){
            return Integer.compare(this.w,o.w);
        }
    }
    static Point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());

        points=new Point[E];

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int start=Integer.parseInt(st.nextToken())-1;
            int end=Integer.parseInt(st.nextToken())-1;
            int weight=Integer.parseInt(st.nextToken());
            points[i]=new Point(start,end,weight);
        }

        make();
        Arrays.sort(points);

        int result=0;
        int pick=0;
        int max=0;
        for(Point point:points){
            if(union(point.from,point.to)){
                result+=point.w;
                max=max>point.w?max:point.w;
                if(++pick==V-1) break;
            }
        }
        System.out.println(result-max);
    }

    private static void make(){
        parents=new int[V];

        for(int i=0;i<V;i++){
            parents[i]=i;
        }
    }

    private static int findset(int a){
        if(parents[a]==a) return a;
        return parents[a]=findset(parents[a]);
    }

    private static boolean union(int a ,int b){
        int aRoot = findset(a);
        int bRoot = findset(b);
        if(aRoot == bRoot) return false;

         parents[bRoot]=aRoot;
         return true;
    }


}