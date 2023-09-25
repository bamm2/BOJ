import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,map[][],whitePaper,bluePaper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        divide(0,0,N);

        System.out.println(whitePaper);
        System.out.println(bluePaper);

    }

    private static void divide(int r,int c,int range) {
        if(range==1 || isSame(r,c,range) ){
            if(map[r][c]==0) whitePaper++;
            else bluePaper++;
            return;
        }

        int nextRange =range/2;
        divide(r,c,nextRange);
        divide(r,c+nextRange,nextRange);
        divide(r+nextRange,c,nextRange);
        divide(r+nextRange,c+nextRange,nextRange);

    }

    private static boolean isSame(int r, int c, int range) {
        for(int i=r;i<r+range;i++){
            for(int j=c;j<c+range;j++){
                if(map[r][c]!=map[i][j]) return false;
            }
        }

        return true;
    }
}

