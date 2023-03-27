import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N,map[][],garden[][];
    static int ans=Integer.MAX_VALUE;
    static int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
    static class Point {
        int r,c,cost;

        Point(int r, int c,int cost) {
            this.r=r;
            this.c=c;
            this.cost=cost;
        }
    }

    static Point[] points;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        garden= new int[N][N];

        points=new Point[3];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                garden[i][j]=map[i][j];
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==0 || j==0 || i==N-1 || j==N-1) continue;
                for(int d=0;d<4;d++){
                    int nr=i+dir[d][0];
                    int nc=j+dir[d][1];
                    garden[i][j]+=map[nr][nc];
                }
            }
        }


        comb(0,0);

        System.out.println(ans);

    }

    private static void comb(int start,int cnt){
        if(cnt==3){
            int sum=0;
            if(isOk()) {
                for (Point curr : points) sum += curr.cost;
                if (ans > sum) ans = sum;
            }
            return;
        }


        for(int i=start,size=N*N;i<size;i++){
            int r=i/N;
            int c=i%N;
            if(r==0 || c==0 || r==N-1 || c==N-1) continue;
            points[cnt]=new Point(r,c,garden[r][c]);
            comb(i+1,cnt+1);
        }
    }


    private static boolean isOk(){
        boolean flag=true;

        int r1=points[0].r; int c1=points[0].c;
        int r2=points[1].r; int c2=points[1].c;
        int r3=points[2].r; int c3=points[2].c;

        int oneTwo=Math.abs(r1-r2)+Math.abs(c1-c2);
        int twoThree=Math.abs(r2-r3)+Math.abs(c2-c3);
        int threeOne=Math.abs(r3-r1)+Math.abs(c3-c1);

        if(oneTwo<=2 || twoThree<=2 || threeOne<=2){
            flag=false;
        }
        return flag;
    }
}