import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st=new StringTokenizer(br.readLine()," ");

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[] arr =new int[N];

        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left=0;
        int right=0;

        int ans = Integer.MAX_VALUE;

        while(true) {
            if(left>N-1 || right>N-1) break;
            int rightNum= arr[right];
            int leftNum= arr[left];

            if(rightNum-leftNum<M) right++;
            else {
                if (ans > rightNum - leftNum) {
                    ans = rightNum - leftNum;
                }
                left++;
            }
        }

        System.out.println(ans);

    }
}
