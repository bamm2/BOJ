import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());

        int[] arr =new int[N];

        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        List<Integer> list =new ArrayList<>();
        for(int i=0;i<arr.length-1;i++){
            list.add(arr[i+1]-arr[i]);
        }

        Collections.sort(list);

        int ans = 0 ;
        for(int i=0;i<N-K;i++){
            ans+=list.get(i);
        }

        System.out.println(ans);

    }

}
