import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());
        int W=Integer.parseInt(st.nextToken());

        int[] arr =new int[N];
        st=new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Queue<Integer> bridge = new ArrayDeque<>();
        for(int i=0;i<L;i++){
            bridge.offer(0);
        }

        int weightSum = 0;
        int time = 0;
        for(int i=0;i<N;i++){
            int curr = arr[i];
            if(weightSum+curr>W){ // 현재 무게를 추가하면 무게 초과
                while(true){ // 1초씩 움직이면서 올릴 수 있는 지점 찾기
                    Integer poll = bridge.poll();
                    time++;
                    weightSum-=poll;
                    if(weightSum+curr<=W) break; // 올릴수 있으면 종료
                    bridge.offer(0); // 올릴 수 없으면 무게 0 으로 표시하기
                }
            }else{ // 현재 무게를 추가할 수 있을 경우
                Integer poll=bridge.poll(); // 하나 꺼내고
                weightSum-=poll; // 무게 빼주고
                time++; // 시간 추가
            }
            weightSum+=curr;
            bridge.offer(curr); // 현재 무게를 다리에 올려주고
        }

        time+=L; // 마지막 트럭이 움직이는 시간

        System.out.println(time);

    }
}