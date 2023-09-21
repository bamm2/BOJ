import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K,map[][],score,zeroCnt;
    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
    static List<Point> deleteList;
    static boolean[][] visited;
    static class Point{
        int r,c,num;
        Point(int r,int c,int num){
            this.r=r;
            this.c=c;
            this.num=num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");

        N= Integer.parseInt(st.nextToken()); // 맵의 크기
        K=Integer.parseInt(st.nextToken()); // 일반블록의 색상 수

        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        gameStart();

        System.out.println(score);

    }

    private static void gameStart() {
        while(true){
            if(!findBlock()) break; // 블록 못찾을 경우 종료
            delete();
            down();
            turn();
            down();
        }
    }

    private static boolean findBlock() {
        deleteList=new ArrayList<>();
        visited=new boolean[N][N];
        zeroCnt=0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j] || map[i][j]==-1 || map[i][j]==0 || map[i][j]==K+1) continue;
                bfs(i,j);
            }
        }

        if(deleteList.size()<2) return false;

        score+= (int)Math.pow(deleteList.size(),2);

        return true;
    }

    private static void bfs(int r, int c) {
        Queue<Point> q=new ArrayDeque<>();
        q.offer(new Point(r,c,map[r][c]));
        visited[r][c]=true;

        List<Point> avail =new ArrayList<>();
        List<Point> zeroList =new ArrayList<>();

        avail.add(new Point(r,c,map[r][c]));

        while (!q.isEmpty()){
            Point curr = q.poll();
            for(int d=0;d<4;d++){
                int nr= curr.r+dir[d][0];
                int nc = curr.c+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc]) continue;
                if(map[nr][nc]==0 || map[nr][nc]==map[r][c]){
                    visited[nr][nc]=true;
                    Point availPoint = new Point(nr,nc,map[nr][nc]);
                    if(map[nr][nc]==0) zeroList.add(availPoint);

                    avail.add(availPoint);
                    q.offer(availPoint);
                }
            }
        }

        if(avail.size() > deleteList.size() || ( avail.size() == deleteList.size() && zeroCnt <= zeroList.size() ) ){
            copyList(avail);
            zeroCnt = zeroList.size();
        }

        setZeroVisited(zeroList);
    }

    private static void copyList(List<Point> list){
        deleteList.clear();
        for(Point curr : list){
            deleteList.add(curr);
        }
    }

    private static void setZeroVisited(List<Point> list){
        for(Point curr : list){
            visited[curr.r][curr.c]=false;
        }
    }

    private static void delete() {
        for(Point curr : deleteList){
            map[curr.r][curr.c]=K+1;
        }
    }

    private static void down(){
        for(int i=0;i<N;i++){
            Stack<Point> stack =new Stack<>();
            for(int j=0;j<N;j++){
                stack.push(new Point(j,i,map[j][i]));
            }
            int r = N-1, c= i;
            while (!stack.isEmpty()){
                Point curr =stack.pop();
                if(curr.num==K+1) continue;
                if(curr.num==-1) {
                    while(true){
                        if(r==curr.r) break;
                        map[r][c]=K+1;
                        r--;
                    }
                    r=curr.r-1;
                } else {
                    map[r][c]=curr.num;
                    r--;
                }
            }
            while(r>=0){
                map[r--][c]=K+1;
            }
        }
    }

    private static void turn(){
        int[][] tmp =new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tmp[i][j]=map[j][N-1-i];
            }
        }
        copyMap(tmp);
    }

    private static void copyMap(int[][] arr){
           for(int i=0;i<N;i++){
               for(int j=0;j<N;j++){
                   map[i][j]=arr[i][j];
               }
           }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
}