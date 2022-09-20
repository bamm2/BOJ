import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int[] arr;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr=new int[9];
        isSelected=new boolean[9];
        for(int i=0;i<9;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        solve(0,0,0);
    }
    private static void solve(int cnt, int seven,int sum){
        if(cnt==9){
            if(seven==7 && sum==100){
                for(int i=0;i<9;i++){
                    if(isSelected[i]) System.out.println(arr[i]);
                }
            }
            return;
        }
            isSelected[cnt]=true;
            solve(cnt+1,seven+1,sum+arr[cnt]);
            isSelected[cnt]=false;
            solve(cnt+1,seven,sum);


        }

}
