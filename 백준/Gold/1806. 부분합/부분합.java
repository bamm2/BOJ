import java.io.*;
import java.util.*;

public class Main {

    static int N, goal,ans;
    static Number[] numbers;
    static class Number{
        int num,sum;
        Number(int num,int sum){
            this.num=num;
            this.sum=sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        numbers = new Number[N];

        st = new StringTokenizer(br.readLine(), " ");

        ans=Integer.MAX_VALUE;

        int left = 0;
        int right = 0 ;

        for (int i = 0; i < N; i++) {
            int curNum=Integer.parseInt(st.nextToken());
            if (i != 0) {
                int curSum = numbers[i - 1].sum + curNum;
                numbers[i]=new Number(curNum,curSum);
                if(right==0 && ans==Integer.MAX_VALUE && curSum>=goal){
                    right = i;
                    ans=right+1;
                }
            } else {
                numbers[0]=new Number(curNum,curNum);
            }
            if(curNum>=goal) ans = 1;
        }

        int sum = numbers[N-1].sum;

        if (sum < goal) {
            System.out.println(0);
        }else if(ans == 1){
            System.out.println(1);
        }else{
            while(true){
                if(numbers[right].sum-numbers[left].sum>=goal){
                    if(ans>right-left) ans=right-left;
                    left++;
                }else{
                    right++;
                    if(right==N) break;
                }
            }
            System.out.println(ans);
        }
        br.close();
    }
}