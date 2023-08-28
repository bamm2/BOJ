import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            int left = 0;
            int right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if ( arr[mid]<num){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }

            int leftTwo = 0;
            right = N-1;
            while(leftTwo <= right){
                int mid = (leftTwo+right) / 2;
                if( arr[mid] <= num ){
                    leftTwo=mid+1;
                }else{
                    right=mid-1;
                }
            }

            if(left>right) sb.append(0).append(" ");
            else sb.append(right-left+1).append(" ");
        }

        System.out.println(sb);
    }
}