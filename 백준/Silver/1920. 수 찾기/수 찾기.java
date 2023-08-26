import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int[] arr= new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<M;i++){
            int num =Integer.parseInt(st.nextToken());

            int left=0;
            int right=N-1;
            boolean flag =false;
            while(left<=right){
                int mid =(left+right)/2;
                if(num>arr[mid]){
                    left=mid+1;
                }else{
                    if(arr[mid]==num){
                        flag=true;
                        break;
                    }
                    right=mid-1;
                }
            }
            if(flag) sb.append(1).append('\n');
            else sb.append(0).append('\n');
        }

        System.out.println(sb);

    }
}