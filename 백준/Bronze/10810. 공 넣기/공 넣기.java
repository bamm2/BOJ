import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
       BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st ;

       st=new StringTokenizer(br.readLine()," ");
       int N =Integer.parseInt(st.nextToken());
       int M =Integer.parseInt(st.nextToken());

       int[] arr =new int[N];

       for(int i=0;i<M;i++){
           st=new StringTokenizer(br.readLine()," ");
           int start =Integer.parseInt(st.nextToken())-1;
           int end =Integer.parseInt(st.nextToken())-1;
           int ballNum =Integer.parseInt(st.nextToken());

           for(int j=start;j<=end; j++){
               arr[j]=ballNum;
           }
       }

       for(int num : arr){
           System.out.print(num+" ");
       }
    }
}