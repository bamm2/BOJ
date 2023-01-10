import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C,ans;
    static boolean sign;
    static char[][] map;
    static int[][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
    static class J{
        int r,c,cnt;
        J(int r,int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }
    static class F{
        int r,c;
        F(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    static Queue<J> ji_q=new ArrayDeque<>();
    static Queue<F> fire_q=new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new char[R][C];
        for(int i=0;i<R;i++){
            String s =br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]=='J'){
                   ji_q.offer(new J(i,j,0));
                }
                if(map[i][j]=='F'){
                    fire_q.offer(new F(i,j));
                }
            }
        }
        ans=Integer.MAX_VALUE;
        bfs();
        if(sign) System.out.println(ans+1); // 밖으로 나가는 거 카운트 +1
        else System.out.println("IMPOSSIBLE");
    }
    private static void bfs(){

        while(!ji_q.isEmpty()){
            int Fsize=fire_q.size();
            for(int i=0;i<Fsize;i++){
                F curr=fire_q.poll();
                for(int d=0;d<4;d++){
                    int nr=curr.r+dir[d][0];
                    int nc=curr.c+dir[d][1];
                    if(isOut(nr,nc)) continue;
                    if(map[nr][nc]=='.' || map[nr][nc]=='J'){
                        map[nr][nc]='F';
                        fire_q.offer(new F(nr,nc));
                    }
                }
            }
            int Jsize=ji_q.size();
            for(int i=0;i<Jsize;i++){
                J curr=ji_q.poll();
                for(int d=0;d<4;d++){
                    int nr=curr.r+dir[d][0];
                    int nc=curr.c+dir[d][1];
                    if(isOut(nr,nc)){
                        sign=true;
                        ans=ans>curr.cnt?curr.cnt:ans;
                        break;
                    }else if(map[nr][nc]=='.'){
                        map[nr][nc]='J';
                        ji_q.offer(new J(nr,nc,curr.cnt+1));
                    }
                }
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}