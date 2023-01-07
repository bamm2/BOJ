import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[] arr =new int[N];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long start=0;
        long end =arr[N-1];
        while(start<=end){
            long mid=(start+end)/2;
            long sum=0;
            for(int num : arr){
                if(num>mid){
                    sum+=num-mid;
                }
            }
            if(sum>=M){
                start=mid+1;
            }else if(sum<M){
                end=mid-1;
            }
        }
        System.out.println(end);
    }
}