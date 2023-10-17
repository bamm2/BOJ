import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int from,to,weight;
        Edge(int from,int to,int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N=Integer.parseInt(br.readLine());
        List<Edge> list =new ArrayList<>();

        int num;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                num=Integer.parseInt(st.nextToken());
                list.add(new Edge(i,j,num));
            }
        }

        make();
        Collections.sort(list);

        int check = 0;
        long sum = 0;
        for(Edge edge : list){
            if(union(edge.from,edge.to)){
                sum+=edge.weight;
                if(++check==N-1) break;
            }
        }

        System.out.println(sum);

    }

    private static void make(){
        parent=new int[N];

        for(int i=0;i<N;i++){
            parent[i]=i;
        }
    }

    private static int find(int a){
        if(parent[a] == a) return a;
        return parent[a]=find(parent[a]);
    }

    private static boolean union(int a , int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parent[bRoot]=aRoot;
        return true;
    }


}