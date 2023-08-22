import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st ;

        int T=Integer.parseInt(br.readLine());

        while(T-->0){
            st=new StringTokenizer(br.readLine()," ");
            int start= Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int diff = end - start;

            sb.append(findAns(diff)).append('\n');

        }

        System.out.println(sb);

    }

//    private static int findAns(int num){
//        int addNum = 1;
//        int sum = 1;
//        int cnt = 1;
//        boolean isTwice = false;
//
//        while(sum<num){
//            sum+=addNum;
//            isTwice=!isTwice;
//            cnt++;
//            if(isTwice) addNum++;
//        }
//        return cnt;
//    }

    private static int findAns(int num){

        int multiple =(int)Math.sqrt(num);

        if(multiple*multiple == num) return multiple*2-1;
        else if( multiple*multiple + multiple >= num) return multiple*2;
        else return multiple*2+1;

    }

}