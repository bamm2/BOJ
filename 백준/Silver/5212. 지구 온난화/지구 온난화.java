import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int R=Integer.parseInt(st.nextToken());
        int C=Integer.parseInt(st.nextToken());
        char[][] map=new char[R+2][C+2];
        boolean[][] chkmap=new boolean[R+2][C+2];

        for(int i=0;i< R+2;i++){
            for(int j=0;j<C+2;j++){
                map[i][j]='.';
            }
        }

        for(int i=1;i<=R;i++){
            String s =br.readLine();
            for(int j=1;j<=C;j++){
                map[i][j]=s.charAt(j-1);
                if(map[i][j]=='X') chkmap[i][j]=true;
            }
        }

        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++) {
                int cnt = 0;
                if (map[i][j] == 'X') {
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dir[d][0];
                        int nc = j + dir[d][1];
                        if (map[nr][nc] == '.') cnt++;
                    }
                    if (cnt >= 3) chkmap[i][j]=false;
                }
            }
        }

        int Rcol=Integer.MIN_VALUE; int Lcol=Integer.MAX_VALUE;
        int Rrow=Integer.MIN_VALUE; int Lrow=Integer.MAX_VALUE;

        for(int i=0;i<R+2;i++){
            for(int j=0;j<C+2;j++){
                if(chkmap[i][j]){
                    Lcol=Lcol>j?j:Lcol;
                    Rcol=Rcol>j?Rcol:j;
                    Lrow=Lrow>i?i:Lrow;
                    Rrow=Rrow>i?Rrow:i;
                }
            }
        }

        StringBuilder sb= new StringBuilder();
        for(int i=Lrow;i<=Rrow;i++){
            for(int j=Lcol;j<=Rcol;j++){
                if(chkmap[i][j]) sb.append('X');
                else sb.append('.');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}