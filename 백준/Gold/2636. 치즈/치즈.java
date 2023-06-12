import java.io.*;
import java.util.*;

public class Main {

    static int R,C,map[][];
    static int[][] dir ={ {-1,0}, {1,0}, {0,1} ,{0,-1}};
    static boolean[][] visited;
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new int[R][C];

        int cnt = 0 ;
        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cnt++;
            }
        }

        List<Integer> cheezeNums=new ArrayList<>();
        cheezeNums.add(cnt);

        while(true){

            visited=new boolean[R][C];

            bfs();

            removeCheeze();

            if(countingCheeze()==0) break;

            cheezeNums.add(countingCheeze());
        }

        // 맨 처음 치즈의 개수를 넣어서 size-1 해야 하는데 마지막 0을 만드는 경우의 수는 안 세므로 size+1  -> size+ 0
        bw.write(cheezeNums.size()+"\n"+cheezeNums.get(cheezeNums.size()-1));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void removeCheeze(){ // 치즈 제거
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==1 && visited[i][j]) map[i][j]=0;
            }
        }
    }

    private static void bfs(){
        Queue<Point> q=new ArrayDeque<>();
        q.offer(new Point(0,0));
        visited[0][0]=true;

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr= curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==0){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc));
                }
                if(map[nr][nc]==1){ // 테두리 체크
                    visited[nr][nc]=true;
                }
            }
        }
    }



    private static boolean isOut(int r,int c){ // 주어진 판의 크기를 넘어서는지 체크
        return r<0 || c<0 || r>=R || c>=C;
    }

    private static int countingCheeze(){ // 남아 있는 치즈 개수 체크
        int cnt=0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==1) cnt++;
            }
        }
        return cnt;
    }

}
