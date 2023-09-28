import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K,rArr[],cArr[];
    static class Point{
        int r,c;
        Point(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }
    static Point[] goals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rArr = new int[K];
        cArr = new int[K];
        goals = new Point[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken()); // 이동 시키려는 숫자
            int r = Integer.parseInt(st.nextToken()) - 1; // 목표 r
            int c = Integer.parseInt(st.nextToken()) - 1; // 목표 c
            goals[i] = new Point(r,c);

            int curR = (num - 1) / N;
            int curC = (num - 1) % N;
            rArr[i] = curR;
            cArr[i] = curC;
        }

        for(int i=0;i<K;i++){
            int moveR = goals[i].r-rArr[i];
            int moveC = goals[i].c-cArr[i];

            if(moveR<0) moveR+=N;
            if(moveC<0) moveC+=N;

            moveRight(rArr[i],moveC);
            moveDown(cArr[i],moveR);

            sb.append(moveR+moveC).append('\n');
        }
        System.out.println(sb);

    }
    private static void moveRight(int r,int move){
        for(int i=0;i<K;i++){
            if(rArr[i]==r){
                cArr[i]= (cArr[i]+move)%N;
            }
        }
    }
    private static void moveDown(int c,int move){
        for(int i=0;i<K;i++){
            if(cArr[i]==c){
                rArr[i]= (rArr[i]+move)%N;
            }
        }
    }

}