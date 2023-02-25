import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V,E,K,start,distance[];
    static List<Integer> results=new ArrayList<>();
    static List<Integer>[] list;
    static class Point{
        int num;
        Point(int num){
            this.num=num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        V=Integer.parseInt(st.nextToken()); // 도시 개수
        E=Integer.parseInt(st.nextToken()); // 도로 개수
        K=Integer.parseInt(st.nextToken()); // 출발 도시로부터의 거리
        start=Integer.parseInt(st.nextToken()); // 출발 도시 번호

        list=new ArrayList[V+1];
        distance=new int[V+1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        for(int i=1;i<=V;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine()," ");
            int from =Integer.parseInt(st.nextToken());
            int to =Integer.parseInt(st.nextToken());
            list[from].add(to);
        }

        solve(start);

        StringBuilder sb= new StringBuilder();
        boolean flag=false;
        for(int i=1;i<=V;i++){
            if(distance[i]==K){
                sb.append(i).append('\n');
                flag=true;
            }
        }

        if(!flag) sb.append(-1);

//        if(results.size()==0){
//            sb.append(-1);
//        }else {
//            Collections.sort(results);
//            for (int num : results) {
//                sb.append(num).append('\n');
//            }
//        }
        System.out.println(sb.toString().trim());
    }

    private static void solve(int st){

        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(st));
        distance[st]=0;

        while(!q.isEmpty()){
           Point curr= q.poll();

           for(int i=0;i<list[curr.num].size();i++){
               int compNum=list[curr.num].get(i);
               if(distance[compNum]<distance[curr.num]+1) continue;
               distance[compNum]=distance[curr.num]+1;
               q.offer(new Point(compNum));
           }
        }

    }
}