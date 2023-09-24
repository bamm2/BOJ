import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, map[][];
    static int[][] dir = { {0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
    static Queue<Cloud> q= new ArrayDeque<>();
    static boolean[][] visited;
    static class Cloud{
        int r,c;
        Cloud(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        init();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while (K-->0){
            visited=new boolean[N][N];
            st=new StringTokenizer(br.readLine()," ");
            move(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
            magic();
            makeCloud();
        }

        System.out.println(findAns());

    }

    private static void init() {
        map=new int[N][N];
        q.offer(new Cloud(N-1,0));
        q.offer(new Cloud(N-1,1));
        q.offer(new Cloud(N-2,0));
        q.offer(new Cloud(N-2,1));
    }

    private static void move(int d, int move) {
        int size = q.size();
        while (size-->0){
            Cloud curr = q.poll();
            int nr = mod(curr.r+dir[d][0]*move);
            int nc = mod(curr.c+dir[d][1]*move);
            visited[nr][nc]=true;
            map[nr][nc]++;
            q.offer(new Cloud(nr,nc));
        }
    }

    private static int mod(int num){
        if(num<0){
            int multiple = Math.abs(num)/N;
            num += multiple*N;
            if(num==0) return 0;
            else return N+num;
        }else{
            return num%N;
        }
    }

    private static void magic() {
        while(!q.isEmpty()){
            Cloud curr = q.poll();
            int cnt = 0 ;
            for(int d=1;d<8;d+=2){
                int nr = curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]!=0) cnt++;
            }
            map[curr.r][curr.c]+=cnt;
        }
    }

    private static void makeCloud() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]>=2 && !visited[i][j]){
                    map[i][j]-=2;
                    q.offer(new Cloud(i,j));
                }
            }
        }
    }

    private static int findAns() {
        int sum = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=map[i][j];
            }
        }
        return sum;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
}