import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());

        int[] product =new int[N];

        for(int i=0;i<N;i++){
            product[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(product);

        boolean[] visited=new boolean[N];

        int chk=0;
        for(int i=N-1;i>=0;i--){
            if(++chk==3){
                visited[i]=true;
                chk=0;
            }
        }

        int sum=0;
        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            sum+=product[i];
        }

        System.out.println(sum);

    }
}