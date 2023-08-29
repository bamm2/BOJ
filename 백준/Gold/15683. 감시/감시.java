import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ans = Integer.MAX_VALUE;
    static int R,C,map[][];
    static boolean[][] visited;
    static int[] dir;
    static class CCTV{
        int r,c,mode;
        CCTV(int r,int c,int mode){
            this.r=r;
            this.c=c;
            this.mode=mode;
        }
    }
    static List<CCTV> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new int[R][C];
        visited=new boolean[R][C];
        list=new ArrayList<>();

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]!=0 ) visited[i][j]=true;
                if(map[i][j]!=0 && map[i][j]!=6) list.add(new CCTV(i,j,map[i][j]));
            }
        }

        dir=new int[list.size()];

        solve(0);

        if(list.size()==0) ans=findAns(visited);

        System.out.println(ans);
    }

    private static void solve(int cnt){

        if(cnt==list.size()){
            boolean[][] copy = copyVisited();
            for(int i=0;i<list.size();i++){
                CCTV cctv = list.get(i);
                int mode = cctv.mode;
                setVisited(cctv.r,cctv.c,mode,dir[i],true, copy);
                ans=Math.min(ans,findAns(copy));
            }
            return;
        }

        for(int i=0;i<4;i++) {
            dir[cnt] = i;
            solve(cnt + 1);
        }

    }

    private static boolean[][] copyVisited(){
        boolean[][] copy =new boolean[R][C];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                copy[i][j]=visited[i][j];
            }
        }
        return copy;
    }

    private static void setVisited(int r,int c,int mode ,int dir , boolean flag ,boolean[][] visited){
        int curR = r; int curC =c ;
        switch (mode){
            case 1:
                switch (dir){
                    case 0:
                        while(!isOut(--curR,curC)) visited[curR][curC]=flag;
                        break;
                    case 1:
                        while(!isOut(curR,++curC)) visited[curR][curC]=flag;
                        break;
                    case 2:
                        while(!isOut(++curR,curC)) visited[curR][curC]=flag;
                        break;
                    case 3:
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        break;
                }
                break;
            case 2:
                switch (dir){
                    case 0: case 2:
                        while (!isOut(curR,++curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        break;
                    case 1: case 3:
                        while (!isOut(++curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(--curR,curC)) visited[curR][curC]=flag;
                }
                break;
            case 3:
                switch (dir){
                    case 0:
                        while (!isOut(--curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,++curC)) visited[curR][curC]=flag;
                        break;
                    case 1:
                        while (!isOut(curR,++curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(++curR,curC)) visited[curR][curC]=flag;
                        break;
                    case 2:
                        while (!isOut(++curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        break;
                    case 3:
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(--curR,curC)) visited[curR][curC]=flag;
                        break;
                }
                break;
            case 4:
                switch (dir){
                    case 0:
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(--curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,++curC)) visited[curR][curC]=flag;
                        break;
                    case 1:
                        while (!isOut(--curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,++curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(++curR,curC)) visited[curR][curC]=flag;
                        break;
                    case 2:
                        while (!isOut(curR,++curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(++curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        break;
                    case 3:
                        while (!isOut(++curR,curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(curR,--curC)) visited[curR][curC]=flag;
                        curR=r;curC=c;
                        while (!isOut(--curR,curC)) visited[curR][curC]=flag;
                        break;
                }
                break;
            case 5:
                switch (dir){
                    case 0: case 1: case 2: case 3:
                    while (!isOut(curR, ++curC)) visited[curR][curC] = flag;
                    curR=r ; curC =c;
                    while (!isOut(++curR, curC)) visited[curR][curC] = flag;
                    curR=r ; curC =c;
                    while (!isOut(--curR, curC)) visited[curR][curC] = flag;
                    curR=r ; curC =c;
                    while (!isOut(curR, --curC)) visited[curR][curC] = flag;

                    break;
                }
                break;
        }
    }

    private static int findAns(boolean[][] visited){
        int cnt = 0 ;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j]) cnt++;
            }
        }
        return cnt;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C || map[r][c]==6 ;
    }
}