import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long A,B;
    static long ans=100_000_001L;
    static class Point {
        long num;
        int cnt;
        Point(long num,int cnt){
            this.num=num;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =new StringTokenizer(br.readLine()," ");
        A=Long.parseLong(st.nextToken());
        B=Long.parseLong(st.nextToken());

        solve(A);
        if(ans==100_000_001L) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void solve(long num){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(num,1));

        while(!q.isEmpty()) {
            Point curr = q.poll();
            if(curr.num==B && ans>curr.cnt){
                ans=curr.cnt;
            }
            long[] move ={curr.num*2,curr.num*10+1};
            for(int d=0;d<2;d++){
                long moveNum=move[d];
                if(isOut(moveNum)) continue;
                q.offer(new Point(moveNum,curr.cnt+1));
            }
        }
    }

    private static boolean isOut(long num){
        return num>=10_000_000_000L;
    }

}