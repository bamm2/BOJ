import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        boolean[] chk =new boolean[42];
        int cnt=0;
        for(int i=0;i<10;i++){
            int num=Integer.parseInt(br.readLine());
            if(!chk[num%42]){
                chk[num%42]=true;
                cnt++;
            }
        }
        System.out.println(cnt);
        br.close();
    }
}
