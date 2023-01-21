import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer> list=new ArrayList<>();
    static int N,map[][],land;
    static boolean[][] visited;
    static int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        visited=new boolean[N][N];
        for(int i=0;i<N;i++){
            String s=br.readLine();
            for(int j=0;j<N;j++){
                map[i][j]=s.charAt(j)-'0';
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j] && map[i][j]==1){
                    land++;
                    list.add(bfs(i,j));
                }
            }
        }

        sb.append(land).append('\n');
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
           sb.append(list.get(i)).append('\n');
        }

        System.out.println(sb);
    }
    private static int bfs(int r,int c){
        Queue<Point> q= new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;
        int cnt=0;

        while(!q.isEmpty()){
            Point curr=q.poll();
            cnt++;
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc])continue;
                if(map[nr][nc]==0) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }
        return cnt;
    }

    private static boolean isOut(int r,int c){
        return(r<0 ||c<0 || r>=N || c>=N);
    }
}