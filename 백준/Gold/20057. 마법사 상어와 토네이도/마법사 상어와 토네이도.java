import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N ,map[][],sum;
    static int[][] direction = { {0,-1}, {1,0} , {0,1},{-1,0}}; // 좌 하 우 상
    static int[][] raiseDir = {{-1,-1},{1,-1},{1,1},{-1,1} };// 11시 , 7시 , 5시 ,1시

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        start(N/2,N/2,1,0,0);

        System.out.println(sum);
    }

    private static void start(int r, int c, int range , int cnt , int dir ) {
        if(r==0 && c==-1) return;

        move(r,c,range,dir);
        int nr = r + direction[dir][0]*range;
        int nc = c + direction[dir][1]*range;

        if(cnt==1){ // 0 , 1  2번 실행하고 크기 증가
            start(nr,nc,range+1,0,(dir+1)%4);
        }else{
            start(nr,nc,range,cnt+1,(dir+1)%4);
        }
    }

    private static void move(int r, int c, int range, int dir) {
         int plus = 0 , curR= r , curC= c;
         while(plus!=range){
             if(isOut(curR+direction[dir][0],curC+direction[dir][1])) break;
             curR+=direction[dir][0];
             curC+=direction[dir][1];
             raiseDust(curR,curC,dir);
             plus++;
         }
    }

    private static void raiseDust(int curR, int curC ,int dir) {

        int dustTotal = map[curR][curC];
        int fivePercent = calculatePercent(dustTotal, 5);
        int tenPercent = calculatePercent(dustTotal, 10);
        int sevenPercent = calculatePercent(dustTotal, 7);
        int twoPercent = calculatePercent(dustTotal, 2);
        int onePercent = calculatePercent(dustTotal, 1);
        int remain = dustTotal - ( fivePercent+2*(twoPercent+sevenPercent+onePercent+tenPercent));

        int remainR = curR+direction[dir][0];
        int remainC = curC+direction[dir][1];
        check(remainR,remainC,remain);
        int fivePercentR = curR+direction[dir][0]*2;
        int fivePercentC = curC+direction[dir][1]*2;
        check(fivePercentR,fivePercentC,fivePercent);
        int tenPercentR = curR+ raiseDir[dir][0];
        int tenPercentC = curC+raiseDir[dir][1];
        check(tenPercentR,tenPercentC,tenPercent);
        int tenPercentTwoR =curR+raiseDir[(dir+1)%4][0];
        int tenPercentTwoC =curC+raiseDir[(dir+1)%4][1];
        check(tenPercentTwoR,tenPercentTwoC,tenPercent);
        int onePercentR = curR+raiseDir[(dir+2)%4][0];
        int onePercentC = curC+raiseDir[(dir+2)%4][1];
        check(onePercentR,onePercentC,onePercent);
        int onePercentTwoR =curR+raiseDir[(dir+3)%4][0];
        int onePercentTwoC =curC+raiseDir[(dir+3)%4][1];
        check(onePercentTwoR,onePercentTwoC,onePercent);
        int sevenPercentR= curR+direction[(dir+1)%4][0];
        int sevenPercentC= curC+direction[(dir+1)%4][1];
        check(sevenPercentR,sevenPercentC,sevenPercent);
        int sevenPercentTwoR= curR+direction[(dir+3)%4][0];
        int sevenPercentTwoC= curC+direction[(dir+3)%4][1];
        check(sevenPercentTwoR,sevenPercentTwoC,sevenPercent);
        int twoPercentR= curR+direction[(dir+1)%4][0]*2;
        int twoPercentC= curC+direction[(dir+1)%4][1]*2;
        check(twoPercentR,twoPercentC,twoPercent);
        int twoPercentTwoR= curR+direction[(dir+3)%4][0]*2;
        int twoPercentTwoC= curC+direction[(dir+3)%4][1]*2;
        check(twoPercentTwoR,twoPercentTwoC,twoPercent);
    }

    private static void check(int r, int c,int num) {
        if(isOut(r,c)) sum+=num;
        else map[r][c]+=num;
    }

    private static int calculatePercent(int num,int percent) {
        return (num*percent/100);
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0|| r>=N || c>=N;
    }

}