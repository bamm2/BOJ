import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[][] taste;
    static int N,min,sour,bitter,none;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        N=Integer.parseInt(br.readLine());
        taste = new int[N][2];
        isSelected= new boolean[N];
        min =Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            st= new StringTokenizer(br.readLine()," ");
            taste[i][0]=Integer.parseInt(st.nextToken()); // 신맛
            taste[i][1]=Integer.parseInt(st.nextToken()); // 쓴맛
        }
        solve(0);
        System.out.println(min);
    }

    private static void solve(int cnt){
        if(cnt==N) {
            sour=1;
            bitter=0;
            none=0;
            for (int i = 0; i < N; i++) {
                if(isSelected[i]) {
                    sour *= taste[i][0];
                    bitter += taste[i][1];
                }else{
                    none++;
                }
            }
            if (none==N) return;
            min=Math.min(min,Math.abs(sour-bitter));
            return;
        }
            isSelected[cnt]=true;
            solve(cnt+1);
            isSelected[cnt]=false;
            solve(cnt+1);
    }

}