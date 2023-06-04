import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A,B,start,goal,ans;
    static boolean[] visited;
    static class Point{
        int pos,cnt;

        Point(int pos,int cnt){
            this.pos=pos;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");

        A =Integer.parseInt(st.nextToken()); // 좌 우 이동 가능
        B =Integer.parseInt(st.nextToken()); // 좌 우 이동 가능
        start =Integer.parseInt(st.nextToken()); // 시작 위치
        goal = Integer.parseInt(st.nextToken()); // 목표 위치

        visited = new boolean[100_101];

        solve();

        System.out.println(ans);
    }

    private static void solve(){
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(start,0));
        visited[start]=true;

        while(!q.isEmpty()){
            Point curr =q.poll();
            if(curr.pos==goal){
                ans=curr.cnt;
                break;
            }
            int pos = curr.pos;
            int[] dir = {pos+1,pos-1,pos+A,pos-A,pos+B,pos-B,pos*A,pos*B};
            for(int d=0;d<dir.length;d++){
                if(isOut(dir[d])) continue;
                if(visited[dir[d]]) continue;
                visited[dir[d]]=true;
                q.offer(new Point(dir[d],curr.cnt+1));
            }
        }
    }


    private static boolean isOut(int pos){
        return pos<0 || pos>100_000;
    }
}
