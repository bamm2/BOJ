import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int subin,goal,same;
    static int[] arr ;
    static int ans=Integer.MAX_VALUE;
    static class Point{
        int num,cnt;
        Point(int num,int cnt){
            this.num=num;
            this.cnt=cnt;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        subin=Integer.parseInt(st.nextToken());
        goal=Integer.parseInt(st.nextToken());

        arr =new int[200_001];
        Arrays.fill(arr,Integer.MAX_VALUE);
        solve();
        
            System.out.println(ans);
            System.out.println(same);

    }

    private static void solve(){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(subin,0));

        while(!q.isEmpty()){
            Point curr =q.poll();
            if(goal==curr.num) {
                if (ans > curr.cnt) {
                    ans=curr.cnt;
                    same=1;
                } else if(ans == curr.cnt) same++;
            }

            if(curr.cnt>ans) continue;

            int[] dir ={curr.num+1,curr.num-1,curr.num*2};
            for(int d=0;d<3;d++){
                if(dir[d]<0 || dir[d]>=200_001) continue;
                if(arr[dir[d]]<curr.cnt+1) continue;
                arr[dir[d]]=curr.cnt+1;
                q.offer(new Point(dir[d],curr.cnt+1));
            }
        }
    }

}