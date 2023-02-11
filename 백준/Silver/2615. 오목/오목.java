import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][] dir={{0,-1},{-1,0},{-1,-1},{-1,1}}; // 가로 세로 11시->5시 7시->2시 의 한칸 전 상태

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb =new StringBuilder();

         map =new int[19][19];

        for(int i=0;i<19;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<19;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        boolean sign =false;
        loop:
        for(int i=0;i<19;i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) { // 0 가로 1 세로 2 11시->5시 3 7시->2시
                    if (dirNumCnt(i, j, map[i][j], d) == 5) { // 5목이라고 하면 , 6목인지 체크
                        int nr = i + dir[d][0];
                        int nc = j + dir[d][1];
                        if (isOut(nr,nc) || map[nr][nc] != map[i][j]) {
                            if(d==3){ // 7시 -> 2시 방향은 가장 왼쪽 점을 찾아줘야 함
                                sign=true;
                                sb.append(map[i][j]).append('\n');
                                sb.append((i+5)).append(" ").append((j-3)); // (i+1)+4 , (j+1)-4
                                break loop;
                            }else {
                                sign=true;
                                sb.append(map[i][j]).append('\n');
                                sb.append((i + 1)).append(" ").append((j + 1));
                                break loop;
                            }
                        }
                    }
                }
            }
        }
        if(!sign) sb.append('0').append('\n'); // 승부가 나지 않았을 경우 
        System.out.println(sb.toString().trim());

    }

    private static int dirNumCnt(int r,int c,int num,int mode){
        int idx=0;
        int cnt=0;
        switch (mode) {
            case 0:
            while (true) {
                cnt++;
                idx++;
                if (isOut(r,c+idx) || map[r][c + idx] != num) break;
            }
              return cnt;
            case 1:
                while(true){
                    cnt++;
                    idx++;
                    if(isOut(r+idx,c) || map[r+idx][c]!=num) break;
                }
                return cnt;
            case 2:
                while(true){
                    cnt++;
                    idx++;
                    if(isOut(r+idx,c+idx) || map[r+idx][c+idx]!=num) break;
                }
                return cnt;
            case 3:
                while(true){
                    cnt++;
                    idx++;
                    if(isOut(r+idx,c-idx) || map[r+idx][c-idx]!=num) break;
                }
                return cnt;
        }
        return 0;
    }

    private static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=19 || c>=19;
    }
}
