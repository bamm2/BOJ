import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static String[] arr;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            min = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine());
            arr = new String[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = st.nextToken();
            }

            loop:
            for(int i=0;i<N;i++){
                for(int j=i+1;j<N;j++){
                    for(int k=j+1;k<N;k++){
                        int sum = getDistance(arr[i],arr[j]) + getDistance(arr[i],arr[k])
                                +getDistance(arr[j],arr[k]);
                        min = Math.min(min,sum);
                        if(min == 0 )break loop;
                    }
                }
            }
            sb.append(min).append('\n');
        }

        System.out.println(sb);
    }

    private static int getDistance(String a, String b){
        int count =0 ;
        for(int i=0;i<4;i++){
            if(a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }

}