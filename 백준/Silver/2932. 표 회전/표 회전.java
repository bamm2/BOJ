import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,K;
    static class Pos{
        int r,c;
        Pos(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    static Pos[] destPosArr;
    static int[] r_Arr,c_Arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N =Integer.parseInt(st.nextToken());
        K =Integer.parseInt(st.nextToken());

        destPosArr=new Pos[K];
        r_Arr=new int[K];
        c_Arr=new int[K];

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine()," ");
            int number = Integer.parseInt(st.nextToken());
            int destR = Integer.parseInt(st.nextToken())-1;
            int destC = Integer.parseInt(st.nextToken())-1;
            destPosArr[i]=new Pos(destR,destC);
            r_Arr[i] = (number-1)/N;
            c_Arr[i] = (number-1)%N;
        }

        for(int i=0;i<K;i++){
            int moveR,moveC;
            if(destPosArr[i].r>=r_Arr[i]){
                moveR=destPosArr[i].r-r_Arr[i];
            }else{
                moveR=N-r_Arr[i]+destPosArr[i].r;
            }

            if(destPosArr[i].c>=c_Arr[i]){
                moveC=destPosArr[i].c-c_Arr[i];
            }else{
                moveC=N-c_Arr[i]+destPosArr[i].c;
            }

            moveCol(r_Arr[i],moveC);
            moveRow(c_Arr[i],moveR);
            sb.append(moveR+moveC).append('\n');
        }

        System.out.println(sb);
    }

    private static void moveCol(int r, int moveC) {
        for(int i=0;i<K;i++){
            if(r_Arr[i]==r){
                c_Arr[i]= (c_Arr[i]+moveC)%N;
            }
        }
    }

    private static void moveRow(int c, int moveR) {
        for(int i=0;i<K;i++){
            if(c_Arr[i]==c){
                r_Arr[i]= (r_Arr[i]+moveR)%N;
            }
        }
    }
}