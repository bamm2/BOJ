
import java.io.*;
import java.util.*;

public class Main {

    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;


        int V= Integer.parseInt(br.readLine()); // 정점의 수
        int E =Integer.parseInt(br.readLine()); // 간선의 수

        int[][] Edges = new int[E][3];


        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            Edges[i][0]=Integer.parseInt(st.nextToken()); // 시작 정점
            Edges[i][1]=Integer.parseInt(st.nextToken()); // 도착 정점
            Edges[i][2]=Integer.parseInt(st.nextToken()); // 가중치
        }

        Arrays.sort(Edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });

         p =new int[V+1]; // 대표자 찾기 배열


        for(int i=0;i<V;i++){ // 기본 대표자 설정
            p[i]=i;
        }

        int ans=0;
        int chk=0; // V-1 개 간선 찾으면 종료

        for(int i=0;i<E;i++){
            if(findSet(Edges[i][0])!=findSet(Edges[i][1])){
                union(findSet(Edges[i][0]),findSet(Edges[i][1]));
                ans+=Edges[i][2];
                chk++;
            }
            if(chk==V-1) break;
        }
        System.out.println(ans);

    }

    private static int findSet(int x){
        if(p[x]!=x)
            p[x]=findSet(p[x]);
            return p[x];

    }
    private static void union(int x, int y){
         p[findSet(y)] = findSet(x);
    }

}