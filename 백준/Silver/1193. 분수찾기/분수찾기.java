import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());

        int plus = 2;
        int start =1;
        int row = 1;

        while(start<N){
            start+=plus;
            plus++;
            row++;
        }

        int cnt =start-row;
        int r,c;
        if (row%2 ==0 ){
            r=1;
            c=row;
            while(++cnt!=N){
                r++;
                c--;
            }
        }else{
            r = row;
            c = 1;
            while(++cnt!=N) {
                r--;
                c++;
            }
        }

        System.out.println(r+"/"+c);
    }
}


