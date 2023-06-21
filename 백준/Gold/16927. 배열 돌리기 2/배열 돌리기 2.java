import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R,C,round, arr[],map[][];
    static int[][] dir ={ {1,0}, {0,1}, {-1,0},{0,-1}}; // 하 우 상 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        round=Integer.parseInt(st.nextToken());

        map=new int[R][C];

        // 배열 한 겹이 벗겨질때마다 R-2 , C-2의 새로운 배열 존재
        // 배열 바깥은 2*R+(C-2)*2 만큼의 원소가 존재한다

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j] =Integer.parseInt(st.nextToken());
            }
        }

        int cycle = Math.min(R,C)/2; //  R-2,C-2만큼 벗겨내다보면 마지막 겹은 X * 2 형태를 띄고 있음

        int r=R , c=C ;
        for(int i=0;i<cycle;i++){
            turn(i,2*r+(c-2)*2);
            r-=2; c-=2;
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    private static void turn(int start,int size) { // start,start에서 출발 , 2*R+(C-2)*2 만큼 원소가 존재
       int curRound = round % size; // 모듈러 연산으로 최소의 회전 수를 체크

        // 시작은 무조건 0,0 1,1 ,2.2 , ...
        for(int i=0;i<curRound;i++){ // 최소 회전 수 만큼 회전
            int r = start;
            int c = start;
            int direct = 0;
            int curr = map[r][c];

            int temp = 0; // 교환
            while (direct < 4) { // 4가 될 경우 다시 하 부터 시작 ( 원점 )
                int nextR = r + dir[direct][0];
                int nextC = c + dir[direct][1];
                if(isOut(nextR,nextC,start)) { // 탐색해야할 범위를 벗어나는 경우 방향 전환
                    direct++;
                    continue;
                }
                temp = map[nextR][nextC];
                map[nextR][nextC]= curr;
                curr=temp;
                r=nextR;
                c=nextC;
            }
        }
    }

    private static boolean isOut(int r,int c,int range){
        return r<0+range || c<0+range || r>=R-range || c>=C-range ;
    }
}