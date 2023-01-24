import javafx.geometry.Pos;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N,R,C,ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        N=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        solve(0,0,(int)Math.pow(2,N));
        br.close();
    }
    private static void solve(int r,int c,int size) {
        if (size == 1) {
            System.out.println(ans);
            return;
        }

        int newSize = size/2; // 반으로 접었을 경우 어느사분면에 있는지 체크
        if(R < r+newSize && C<c+newSize ){ // 1사분면
            solve(r,c, newSize);
        }
        if(R < r+newSize && C>=c+newSize) { // 2사분면
            ans += (size * size) / 4;
            solve(r, c+newSize, newSize);
        }
        if(R>=r+newSize && C<c+newSize){ // 3사분면
            ans +=( (size*size) /4 ) * 2;
            solve(r+newSize,c,newSize);
        }
        if(R>=r+newSize && C>=c+newSize){ // 4사분면
            ans += ( (size*size)/4) * 3;
            solve(r+newSize,c+newSize,newSize);
        }

    }
}
