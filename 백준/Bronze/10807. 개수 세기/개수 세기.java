import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       int N=Integer.parseInt(br.readLine());

       int[] arr=new int[201];
        StringTokenizer st =new StringTokenizer(br.readLine()," ");
       for(int i=0;i<N;i++){
           arr[Integer.parseInt(st.nextToken())+100]++;
       }

       int M=Integer.parseInt(br.readLine());

        System.out.println(arr[M+100]);


    }
}