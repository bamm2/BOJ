import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static int ans = Integer.MAX_VALUE;
    static int[] paper = {0,5,5,5,5,5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map=new int[10][10];

        for(int i=0;i<10;i++){
            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            for(int j=0;j<10;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0,0);

        if(ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);

    }
    private static void solve(int r, int c,int cnt){
        if(r==9 && c==10){
            ans=ans>cnt?cnt:ans;
            return;
        }

        if(ans<=cnt) return;

        if(c>9) {
            solve(r+1, 0, cnt);
            return;
        }

        if(map[r][c]==1){
            for(int i=5;i>=1;i--){
                if(paper[i]>0 && attachChk(r,c,i)){
                    attach(r,c,i,0);
                    paper[i]--;
                    solve(r,c+1,cnt+1);
                    attach(r,c,i,1);
                    paper[i]++;
                }
            }
        }else{
            solve(r,c+1,cnt);
        }
    }

    private static void attach(int r,int c,int size,int num) {
        for(int i=r;i<r+size;i++){
            for(int j=c;j<c+size;j++){
                map[i][j]=num;
            }
        }
    }

    private static boolean attachChk(int r,int c ,int size){
       for(int i=r;i<r+size;i++){
           for(int j=c;j<c+size;j++){
               if(isOut(i,j)) {
                   return false;

               }
               if(map[i][j]!=1){
                   return false;

               }
           }
       }
       return true;
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=10 || c>=10 ;
    }
}