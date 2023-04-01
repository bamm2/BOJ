import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int map[][];
    static StringBuilder sb=new StringBuilder();

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        map=new int[9][9];

        for(int i=0;i<9;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<9;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0);

        System.out.println(sb.toString().trim());

    }

    private static void solve(int r, int c) {

        if(r==8 && c>=9 ) {
            if (sb.length() == 0) {
                for (int[] a : map) {
                    for (int b : a) {
                        sb.append(b).append(" ");
                    }
                    sb.append('\n');
                }
            }
            return;
        }

        if(c>=9) {
            solve(r+1,0);
            return;
        }

        if(map[r][c]==0){
            for(int i=1;i<=9;i++) {
                if (colChk(r,i) && rowChk(c,i) && boxChk(r,c,i)) {
                    map[r][c]=i;
                    solve(r,c+1);
                }
            }
            map[r][c]=0;
        }else{
            solve(r,c+1);
        }

    }
    private static boolean colChk(int r,int num){
        boolean sign=true;

        for(int i=0;i<9;i++){
            if(map[r][i]==num){
                sign=false;
                break;
            }
        }
        return sign;
    }

    private static boolean rowChk(int c,int num){
        boolean sign=true;

        for(int i=0;i<9;i++){
            if(map[i][c]==num){
                sign=false;
                break;
            }
        }
        return sign;
    }

    private static boolean boxChk(int chkR,int chkC ,int num){
        boolean sign=true;
        int r=(chkR/3)*3;
        int c=(chkC/3)*3;

        loop:
        for(int i=r;i<r+3;i++){
            for(int j=c;j<c+3;j++){
                if(map[i][j]==num){
                    sign=false;
                    break loop;
                }
            }
        }

        return sign;
    }

}
