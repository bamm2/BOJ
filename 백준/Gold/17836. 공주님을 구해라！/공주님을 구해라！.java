import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R , C ,map[][],Time;
    static int[][] dir = { {-1,0},{1,0},{0,1},{0,-1} };
    static boolean[][][] visited;

    static class Person{
        int r,c,cnt;
        boolean getWeapon;
        Person(int r,int c,int cnt, boolean getWeapon){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
            this.getWeapon=getWeapon;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken()); // 행
        C=Integer.parseInt(st.nextToken()); // 열
        Time=Integer.parseInt(st.nextToken()); // 제한 시간

        map=new int[R][C];
        visited=new boolean[2][R][C];

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        if(map[R-1][C-1]==0) System.out.println("Fail");
        else System.out.println(map[R-1][C-1]);

    }

    private static void bfs(){
        Queue<Person> q =new ArrayDeque<>();
        q.offer(new Person(0,0,0,false));
        visited[0][0][0]=true;

        while(!q.isEmpty()){
            Person curr =q.poll();
            if(curr.r==R-1 && curr.c==C-1){
                map[curr.r][curr.c]=curr.cnt;
                break;
            }
            if(curr.cnt>Time)  continue;
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];

                if(isOut(nr,nc)) continue;
                if(!curr.getWeapon){
                    if(visited[0][nr][nc]) continue;
                    visited[0][nr][nc]=true;
                    if(map[nr][nc]==0){
                        q.offer(new Person(nr,nc,curr.cnt+1,false));
                    }else if(map[nr][nc]==2){
                        q.offer(new Person(nr,nc,curr.cnt+1,true));
                    }
                }else{
                    if(visited[1][nr][nc]) continue;
                    visited[1][nr][nc]=true;
                    q.offer(new Person(nr,nc,curr.cnt+1,true));
                }
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}
