import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        String s =br.readLine();
        int[][] count=new int[s.length()+1][26];

        for(int i=1;i<=s.length();i++){
            int pos = s.charAt(i-1)-'a';
            for(int j=0;j<26;j++) {
                if(j==pos)  count[i][pos] = count[i - 1][pos] + 1;
                else  count[i][j] = count[i - 1][j];
            }
        }
        
        int N =Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            char findChar = st.nextToken().charAt(0);
            int findCharIdx = findChar-'a';
            int stIdx= Integer.parseInt(st.nextToken());
            int edIdx= Integer.parseInt(st.nextToken());
            int findCnt = count[edIdx+1][findCharIdx] - count[stIdx][findCharIdx];
            sb.append(findCnt).append('\n');
        }
        System.out.println(sb);
    }
}