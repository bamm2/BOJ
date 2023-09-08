import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M,ans;
    static List<Integer>[] heavyList,lightList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        heavyList=new ArrayList[N+1];
        lightList=new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            heavyList[i]=new ArrayList<>();
            lightList[i]=new ArrayList<>();
        }


        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());
            heavyList[heavy].add(light); // 무거운 친구와 연결된 덜 무거운 친구
            lightList[light].add(heavy); // 가벼운 친구와 연결된 덜 가벼운 친구
        }

        search();

        System.out.println(ans);
    }

    private static void search() {

        for(int i=1;i<=N;i++){
            if(searchList(i,heavyList) || searchList(i,lightList)) {
                ans++;
            }
        }

    }

    private static boolean searchList(int curNum,List<Integer>[] list) {
        HashSet<Integer> hs =new HashSet<>();
        Queue<Integer> q= new ArrayDeque<>();
        q.offer(curNum);

        while(!q.isEmpty()){
            Integer curr =q.poll();
            if(list[curr].size()==0) continue;
            for(int i=0;i<list[curr].size();i++){
                int next = list[curr].get(i);
                if(hs.contains(next)) continue;
                hs.add(next);
                q.offer(next);
            }
        }
         if(hs.size()>N/2) return true;
         else return false;
    }

}