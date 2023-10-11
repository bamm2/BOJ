import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();

        int N =Integer.parseInt(br.readLine());
        map=new char[N][N];

        makeStar(0,0,N,false);

        for(char[] chars : map){
            for(char c : chars){
                sb.append(c);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void makeStar(int r,int c,int n,boolean isBlank) {

        if(isBlank){
            for(int i=r;i<r+n;i++){
                for(int j=c;j<c+n;j++){
                    map[i][j]=' ';
                }
            }
            return;
        }

        if(n==1) {
            map[r][c]='*';
            return;
        }

        int size = n/3;
        int cnt =0 ;
        for(int i=r;i<r+n;i+=size){
            for(int j=c;j<c+n;j+=size){
                cnt++;
                if(cnt==5){
                    makeStar(i,j,size,true);
                }else{
                    makeStar(i,j,size,false);
                }
            }
        }
    }

}