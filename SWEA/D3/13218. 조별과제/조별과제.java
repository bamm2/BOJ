import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int T =Integer.parseInt(br.readLine());

        for(int tc=1;tc<=T;tc++){
            int N=Integer.parseInt(br.readLine());
            int ans =0;
            ans=N/3;
            System.out.println("#"+tc+" "+ans);
        }//tc for
    }// main
}
