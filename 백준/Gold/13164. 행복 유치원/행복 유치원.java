import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        int[] arr=new int[N-1]; // 인접한 높이의 차 , 커질수록 그룹에서의 격차가 커진다.

        st=new StringTokenizer(br.readLine()," ");
        int number=0;
        for(int i=0;i<N;i++){
            if(i==0) number=Integer.parseInt(st.nextToken());
            else {
                int num=Integer.parseInt(st.nextToken());
                arr[i-1]=num-number;
                number=num;
            }
        }

        Arrays.sort(arr); // 격차가 작은 순으로 나열 , K개의 조로 나누려면 K-1만큼 나눠야함

       int sum=0;
       for(int i=0,size=N-1-(K-1);i<size;i++){ // N-1개의 높이의 차 중 K-1개 만큼 나눠야함 
           sum+=arr[i];
       }

        System.out.println(sum);

    }
}