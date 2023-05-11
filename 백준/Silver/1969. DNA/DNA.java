import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");

        int N=Integer.parseInt(st.nextToken()); // DNA의 수
        int M =Integer.parseInt(st.nextToken()); // 문자열의 길이

        count =new int[4][M];

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<s.length();j++){
                char c = s.charAt(j);
                counting(j,c);
            }
        }

        int[] maxArr =new int[M]; // 인덱스 별 가장 많은 문자의 수

        StringBuilder sb=new StringBuilder();

        int sum = 0;

        for(int i=0;i<M;i++){ // 문자열 길이
            int max=0;
            char c = ' ';
            for(int j=3;j>=0;j--){ // A,C,G,T
                if(count[j][i]>=max){
                   max =count[j][i];
                    c=itoc(j);
                }
            }
            sum+=max;
            sb.append(c);
        }

        sb.append('\n').append(N*M-sum);

        System.out.println(sb.toString().trim());
    }

    private static void counting(int idx,char c ){
        switch (c){
            case 'A':
                count[0][idx]++;
                break;
            case 'C':
                count[1][idx]++;
                break;
            case 'G':
                count[2][idx]++;
                break;
            case 'T':
                count[3][idx]++;
                break;
        }
    }
    private static char itoc(int idx){
        switch (idx){
            case 0 : return 'A';
            case 1 : return 'C';
            case 2 : return 'G';
            case 3 : return 'T';
        }
        return ' ';
    }

}