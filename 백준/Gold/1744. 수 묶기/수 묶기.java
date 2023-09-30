import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int[] arr =new int[N];
        boolean[] visited= new boolean[N];

        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int plusStartIdx = -1;
        for(int i=0;i<N;i++){
            if(arr[i]>0){
                plusStartIdx=i;
                break;
            }
        }
        if(plusStartIdx == -1) plusStartIdx=N;

        int sum = 0;
        // 양수
        for(int i=N-1;i>=plusStartIdx;i--){
            int cur = arr[i];
            if(visited[i] || cur == 1) continue;
            for(int j=i-1;j>=plusStartIdx;j--){
                if(visited[j] || arr[j]==1) continue;
                visited[i]=true;
                visited[j]=true;
                sum+=cur*arr[j];
                break;
            }
        }

        // 음수
        for(int i=0;i<plusStartIdx;i++){
            int cur = arr[i];
            if(visited[i]) continue;
            for(int j=i+1;j<plusStartIdx;j++){
                if(visited[j] ) continue;
                visited[i]=true;
                visited[j]=true;
                sum+=cur*arr[j];
                break;
            }
        }

        for(int i=0;i<N;i++){
            if(!visited[i]) sum+=arr[i];
        }

        System.out.println(sum);

    }
}