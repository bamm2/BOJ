import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine()," ");

        int N=Integer.parseInt(st.nextToken()); // 문자열 길이
        int H=Integer.parseInt(st.nextToken()); // 세로로 번진 글자 수
        int W=Integer.parseInt(st.nextToken()); // 가로로 번진 글자 수

        char[][] map =new char[H][N*W];
        StringBuilder sb= new StringBuilder();
        char[] ans =new char[N];

        for(int i=0;i<H;i++){
            String s =br.readLine();
            for(int j=0;j<N*W;j++){
                map[i][j]=s.charAt(j);
            }
        }

        for(int i=0;i<H;i++){
            for(int j=0;j<N*W;j++){
                if(map[i][j]!='?'){
                    ans[j/W]=map[i][j];
                }
            }
        }

        for(int i=0;i<ans.length;i++){
            if(ans[i]=='\u0000')
                ans[i]='?';
        }

        String answer="";
        for(int i=0;i<ans.length;i++){
            answer+=ans[i];
        }
        System.out.println(answer);
    }//main
}