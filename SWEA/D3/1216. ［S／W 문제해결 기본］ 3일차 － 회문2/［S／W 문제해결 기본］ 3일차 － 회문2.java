import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for(int tc=1;tc<=10;tc++) {
            br.readLine();

            map=new char[100][100];

            for(int i=0;i<100;i++) {
                String s =br.readLine();
                for(int j=0;j<100;j++) {
                    map[i][j]=s.charAt(j);
                }
            }

            int maxlen=0;

            //회문인 길이가 홀수개 - 행 체크
            for(int i=0;i<100;i++){
                for(int j=0;j<100;j++){
                    int len=1;  // 길이
                    int range=0; // 한칸씩 늘어나는 범위
                    while(true){
                        if(isOut(j,range+1)) break; // 갈수 있는지 먼저 체크
                        if(map[i][j-(range+1)]==map[i][j+(range+1)]){
                            len+=2;
                            range++;
                        }else break;
                    }
                    maxlen=maxlen>len?maxlen:len;
                }
            }

            //회문인 길이가 홀수개 - 열 체크
            for(int i=0;i<100;i++){
                for(int j=0;j<100;j++){
                    int len=1;
                    int range=0;
                    while(true){
                        if(isOut(j,range+1)) break;
                        if(map[j-(range+1)][i] == map[j+(range+1)][i]){
                            len+=2;
                            range++;
                        }else break;
                    }
                    maxlen=maxlen>len?maxlen:len;
                }
            }

            //회문인 길이가 짝수개 - 행 체크
            for(int i=0;i<100;i++){
                for(int j=0;j<99;j++) {
                    if (map[i][j] == map[i][j + 1]) { // 연속되는 두 수가 같은 경우만 체크 , 홀수개는 완탐
                        int len = 2;
                        int range = 0;
                        while (true) {
                            if (isOut(j, range+1,range+2)) break; //이미 j를 체크하고 왔기때문에 +1,+2해서 그 다음을 체크 
                            if (map[i][j-(range+1)]==map[i][j+(range+2)]){
                                len+=2;
                                range++;
                            }else break;
                        }
                        maxlen=maxlen>len?maxlen:len;
                    }
                }
            }

            //회문인 길이가 짝수개 - 열 체크
            for(int i=0;i<100;i++){
                for(int j=0;j<99;j++){
                    if(map[j][i]==map[j+1][i]) {
                        int len = 2;
                        int range = 0;
                        while (true) {
                            if (isOut(j, range+1,range+2)) break;
                            if (map[j-(range+1)][i]==map[j+(range+2)][i]){
                                len+=2;
                                range++;
                            }else break;
                        }
                        maxlen=maxlen>len?maxlen:len;
                    }
                }
            }
            System.out.println("#"+tc+" "+maxlen);
         }// tc for
    }
    private static boolean isOut(int n,int range){
        if( n-range < 0 || n+range >= 100 ) return true;
        return false;
    }
    private static boolean isOut(int n,int range,int range2){
        if( n-range < 0 || n+range2 >= 100 ) return true;
        return false;
    }
}

