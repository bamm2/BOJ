import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N , map[][] ,headR,headC,curDir,curTime, ans;
    static boolean[][] apple, visited;
    static boolean gameOver = false;
    static class Snake{
        int r,c;
        Snake(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    static Deque<Snake> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        visited=new boolean[N][N];
        apple=new boolean[N][N];

        deque=new ArrayDeque<>();

        int K=Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine()," ");
            int r =Integer.parseInt(st.nextToken())-1;
            int c =Integer.parseInt(st.nextToken())-1;
            apple[r][c]=true;
        }

        visited[0][0]=true;
        deque.offer(new Snake(0,0));

        int L =Integer.parseInt(br.readLine());
        while(L-->0){
            st=new StringTokenizer(br.readLine()," ");
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            
            if(gameOver) continue;
            while(curTime++!=time){
                goStright();
                if(gameOver) break;
            }
            curTime--;
            turn(dir);
        }
        if(!gameOver){
            while(!gameOver){
              goStright();
            }
        }

        System.out.println(ans);
    }

    private static void goStright() {
        switch (curDir){
            case 0:
                headC++;
                break;
            case 1:
                headR++;
                break;
            case 2:
                headC--;
                break;
            case 3:
                headR--;
                break;
        }

        moving();
    }

    private static void turn(char c){
        switch (curDir){
            case 0:
                if(c=='D') curDir=1;
                else curDir=3;
                break;
            case 1:
                if(c=='D') curDir=2;
                else curDir=0;
                break;
            case 2:
                if(c=='D') curDir=3;
                else curDir=1;
                break;
            case 3:
                if(c=='D') curDir=0;
                else curDir=2;
                break;
        }
    }

    private static void moving(){
        ans++;
        if(isOut(headR,headC)){
            gameOver=true;
            return;
        }

        if(apple[headR][headC]){ // 사과 먹은 경우
            apple[headR][headC]=false;
        }else{ // 사과 없는 경우
            Snake tail = deque.pollLast();
            visited[tail.r][tail.c]=false;
        }
        visited[headR][headC]=true; // 이동한 곳 체크
        deque.offerFirst(new Snake(headR,headC)); // 머리
    }

    private static boolean isOut(int r,int c){
        return r<0|| c<0|| r>=N || c>=N || visited[r][c];
    }
}