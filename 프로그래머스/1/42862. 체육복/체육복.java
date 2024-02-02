import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        PriorityQueue<Integer> q =new PriorityQueue<>();
        for(int i=0;i<reserve.length;i++){
            q.offer(reserve[i]);
        }
        
        Arrays.sort(lost);
        
        int cnt = 0;
        int prevLostNumber =0;
        for(int i=0;i<lost.length;i++){
            int number = lost[i];
            cnt +=lost[i] - prevLostNumber - 1;
            prevLostNumber = lost[i];
            while(!q.isEmpty()){
                int poll = q.poll();
                if(poll < number-1) continue; // 빌려줄 수 없는 친구 
                if(number-1 == poll || poll == number){ // 이미 체육복있거나 내 여유분
                     cnt++;
                    int check = -1;
                    if(!q.isEmpty()) check = q.poll();
                    if(check != number) q.offer(check);
                    break;
                }else if(poll == number+1){ // 내 다음 친구가 체육복 여유분이 있을 경우 
                    if(i+1 != lost.length){ // 다음 친구가 잃어버릴 가능성이 존재한다면 
                        if(lost[i+1] == poll){ // 다음 친구가 잃어버렸다면 
                            q.offer(poll); // 다시 넣어주기 
                        }else{ // 다음 친구가 잃어버리지 않았다면 
                            cnt++;
                        }
                    }else{ // 다음 친구가 잃어버릴 가능성이 없음 
                       cnt++; 
                    }
                    break;
                }else{ // 내 번호보다 큰 친구만 여유분 있을 경우 
                    q.offer(poll); // 다시 넣어주기 
                    break;
                }
            }
        }
        cnt+= n-prevLostNumber;
        
        return cnt;
    }
}