import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int arr[], N,firstAns,secondAns;
    static int sumClosedZero = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr=new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N-1;
        while (left<right){
            int sum = Math.abs(arr[left]+arr[right]);
            ansCheck(sum,left,right);
            if(arr[left]<0 && arr[right]<0) { // 둘다 음수 일 경우 , left++
                left++;
            }else if( arr[left]>0 && arr[right]>0){ // 둘다 양수 일 경우, right--
                right--;
            }else { // 하나는 양수 , 하나는 음수인 경우
                if(right-1==left) break; // 전부 비교한 경우
                if(Math.abs(arr[left+1]+arr[right]) > Math.abs(arr[left]+arr[right-1])){
                    right--;
                }else{
                    left++;
                }
            }
        }
        System.out.println(firstAns+" "+secondAns);
    }

    private static void ansCheck(int sum,int left,int right){
        if(sum<sumClosedZero){
            sumClosedZero=sum;
            firstAns=arr[left];
            secondAns=arr[right];
        }
    }
}