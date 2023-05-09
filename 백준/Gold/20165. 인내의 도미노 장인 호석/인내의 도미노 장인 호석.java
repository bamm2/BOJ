import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R,C,round,map[][],dir[][];
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        round=Integer.parseInt(st.nextToken());

        map=new int[R][C];
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<round;i++){
            st=new StringTokenizer(br.readLine()," ");
            int attackR =Integer.parseInt(st.nextToken())-1;
            int attackC =Integer.parseInt(st.nextToken())-1;
            char attackDir =st.nextToken().charAt(0);

            moving(attackR,attackC,attackDir);

            st=new StringTokenizer(br.readLine()," ");
            int defenceR=Integer.parseInt(st.nextToken())-1;
            int defenceC=Integer.parseInt(st.nextToken())-1;

            visited[defenceR][defenceC]=false;
        }



        int cnt=0;
        StringBuilder sb =new StringBuilder();
        for(boolean[] visit : visited){
            for(boolean a : visit){
                sb.append(a?'F':'S').append(" ");
                if(a) cnt++;
            }
            sb.append('\n');
        }

        System.out.println(cnt+round);
        System.out.println(sb.toString().trim());



    }

    private static void moving(int r,int c ,char dir){
        int curR= r;
        int curC= c;
        int moveAvail = map[r][c]-1;

        switch(dir){
            case 'E':
                while(true){
                    visited[curR][curC]=true;
                    if(moveAvail==0) break;
                    if(isOut(curR,curC+1)) break;
                    if(!visited[curR][curC+1]){
                        moveAvail = comp(moveAvail, map[curR][++curC]);
                    }else{
                        ++curC;
                    }

                    moveAvail--;
                }
                break;
            case 'W':
                while(true){
                    visited[curR][curC]=true;
                    if(moveAvail==0) break;
                    if(isOut(curR,curC-1)) break;
                    if(!visited[curR][curC-1]) {
                        moveAvail = comp(moveAvail, map[curR][--curC]);
                    }else{
                        --curC;
                    }

                    moveAvail--;
                }
                break;
            case 'S':
                while(true){
                    visited[curR][curC]=true;
                    if(moveAvail==0) break;
                    if(isOut(curR+1,curC)) break;
                    if(!visited[curR+1][curC]) {
                        moveAvail = comp(moveAvail, map[++curR][curC]);
                    }else{
                        ++curR;
                    }

                    moveAvail--;
                }
                break;
            case 'N':
                while(true){
                    visited[curR][curC]=true;
                    if(moveAvail==0) break;
                    if(isOut(curR-1,curC)) break;
                    if(!visited[curR-1][curC]) {
                        moveAvail = comp(moveAvail, map[--curR][curC]);
                    }else{
                        --curR;
                    }

                    moveAvail--;
                }
                break;
        }
    }

    private static int comp(int a,int b){
        return a>b?a:b;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}