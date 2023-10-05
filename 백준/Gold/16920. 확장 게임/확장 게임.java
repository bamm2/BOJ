import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R,C,P;
    static char[][] map;
    static boolean[][][] visited;
    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
    static List<Point>[] list;
    static int[] power,ans;
    static Queue<Point> q;
    static class Point{
        int r,c,cnt;
        char player;
        Point(int r,int c,char player,int cnt){
            this.r=r;
            this.c=c;
            this.player=player;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        P=Integer.parseInt(st.nextToken());

        map=new char[R][C];
        power=new int[P+1];
        visited=new boolean[P+1][R][C];
        ans=new int[P+1];
        list=new ArrayList[P+1];
        for(int i=1;i<=P;i++){
            list[i]=new ArrayList<>();
        }

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<P;i++){
            power[i+1]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
                if(map[i][j]!='.' && map[i][j]!='#'){
                    int player = map[i][j]-'0';
                    list[player].add(new Point(i,j,map[i][j],0));
                }
            }
        }

        while (true) {
            boolean gameOver=true;
            for (int i = 1; i <= P; i++) {
                if (bfs(i)) gameOver = false;
            }
            if(gameOver) break;
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]!='.' && map[i][j]!='#'){
                    ans[map[i][j]-'0']++;
                }
            }
        }

        for(int i=1;i<=P;i++){
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static boolean bfs(int player) {
        boolean flag=false;

        q=new ArrayDeque<>();
        for(int i=0;i<list[player].size();i++){
            Point curr = list[player].get(i);
            visited[player][curr.r][curr.c]=true;
            q.offer(curr);
        }
        list[player].clear();

        while (!q.isEmpty()){
            Point curr = q.poll();
            if(curr.cnt==power[player]){
                list[player].add(new Point(curr.r,curr.c, curr.player, 0));
                continue;
            }
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc) || visited[player][nr][nc] || map[nr][nc]=='#')  continue;
                if(map[nr][nc]=='.'){
                    visited[player][nr][nc]=true;
                    map[nr][nc]=curr.player;
                    q.offer(new Point(nr,nc,curr.player,curr.cnt+1));
                    flag=true;
                }else if(map[nr][nc]==curr.player){
                    visited[player][nr][nc]=true;
                    q.offer(new Point(nr,nc,curr.player,curr.cnt));
                }
            }
        }
        return flag;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}
