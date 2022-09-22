import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] input,numbers;
    static boolean[] isSelected;
    static StringBuilder sb =new StringBuilder();
    static HashSet<String> hs= new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

         N =Integer.parseInt(st.nextToken());
         M =Integer.parseInt(st.nextToken());
         input= new int[N];
         numbers=new int[M];
         isSelected=new boolean[N];
         st=new StringTokenizer(br.readLine()," ");
         for(int i=0;i<N;i++){
             input[i]=Integer.parseInt(st.nextToken());
         }
        Arrays.sort(input);
        solve(0,0);
        System.out.println(sb);
       }
       private static void solve(int num,int sel){
            if(num==M){
                StringBuilder tmp =new StringBuilder();
                for(int i=0;i<M;i++){
                    tmp.append(numbers[i]).append(' ');
                }
                String s =tmp.toString();
                if(!hs.contains(s)){
                    hs.add(s);
                    sb.append(s).append('\n');
                }
                return;
            }

            for(int i=sel;i<N;i++){
                    numbers[num]=input[i];
                    solve(num+1,i+1);

            }

       }
}