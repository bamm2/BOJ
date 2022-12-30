import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Stack<Integer> stack=new Stack<>();

        int N=Integer.parseInt(br.readLine());

        int[] arr=new int[N];
        int[] LIS=new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int max=0;
        for(int i=0;i<N;i++){
            LIS[i]=1;
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i] && LIS[i]<LIS[j]+1){
                    LIS[i]=LIS[j]+1;
                }
            }
            if(max<LIS[i]) max=LIS[i];
        }

        StringBuilder sb= new StringBuilder();
        int idx=max;
        for(int i=N-1;i>=0;i--){
            if(LIS[i]==idx){
                stack.push(arr[i]);
                idx--;
            }
            if(idx<0) break;
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(max);
        System.out.println(sb);

    }
}