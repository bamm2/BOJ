import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        for(int tc=1;tc<=10;tc++){
            int N=Integer.parseInt(br.readLine());
            char[][] map= new char[8][8];

            for(int i=0;i<8;i++){
                String s=br.readLine();
                for(int j=0;j<8;j++){
                    map[i][j]=s.charAt(j);
                }
            }

            int ans=0;

            for(int i=0;i<8;i++){
                for(int j=0;j<=8-N;j++){
                   int cnt=0;
                    for(int k=0;k<N/2;k++){
                        if(map[i][k+j]==map[i][N-k-1+j])
                            cnt++;
                    }
                    if(cnt==N/2) ans++;
                }
            }

            for(int i=0;i<=8-N;i++){
                for(int j=0;j<8;j++){
                    int cnt=0;
                    for(int k=0;k<N/2;k++){
                        if(map[i+k][j]==map[N-k-1+i][j])
                            cnt++;
                    }
                    if(cnt==N/2) ans++;
                }
            }

            System.out.println("#"+tc+" "+ans);


        }//tc for
    }
}