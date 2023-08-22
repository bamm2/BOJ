import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int min = Integer.MAX_VALUE;
    static int V,minCnt;
    static List<Member> memberScoreList;
    static List<Integer>[] list;
    static class Member implements Comparable<Member>{
        int v , score;
        Member(int v,int score){
            this.v=v;
            this.score=score;
        }

        @Override
        public int compareTo(Member o) {
            if(this.score==o.score){
                return this.v-o.v;
            }else{
                return this.score-o.score;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (v1 == -1 && v2 == -1) break;
            list[v1].add(v2);
            list[v2].add(v1);
        }

        memberScoreList=new ArrayList<>();

        for(int i=1;i<=V;i++){
            getScore(i);
        }

        Collections.sort(memberScoreList);

        StringBuilder sb =new StringBuilder();
        for(Member member: memberScoreList){
            if(member.score==min) minCnt++;
            else break;
        }
        sb.append(min).append(" ").append(minCnt).append('\n');

        for(Member member: memberScoreList){
            if(member.score==min) sb.append(member.v).append(" ");
            else break;
        }

        System.out.println(sb);

    }
    private static void getScore(int v){
        boolean[] visited= new boolean[V+1];

        Queue<Integer> q =new ArrayDeque<>();
        int count = 0;
        int depth = 0;
        q.offer(v);
        visited[v]=true;

        while(!q.isEmpty()){
            int size = q.size();

            depth++;

            while(size-->0){
                Integer curr =q.poll();

                for(Integer next : list[curr]){
                    if(visited[next]) continue;
                    visited[next]=true;
                    q.offer(next);
                    count++;
                }
            }

            if(count==V-1) break;
        }

        if(depth<min) min=depth;
        memberScoreList.add(new Member(v,depth));
    }

}