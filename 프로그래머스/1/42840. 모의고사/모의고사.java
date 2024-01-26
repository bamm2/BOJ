import java.util.*;

class Solution {
    
    public int[] solution(int[] answers) {
        int[] f = {1,2,3,4,5};
        int[] s = {2,1,2,3,2,4,2,5};
        int[] t = {3,3,1,1,2,2,4,4,5,5};

        int[] cnt=new int[3];
        for(int i=0;i<answers.length;i++){
            if(answers[i] == f[i%f.length]) cnt[0]++;
            if(answers[i] == s[i%s.length]) cnt[1]++;
            if(answers[i] == t[i%t.length]) cnt[2]++;
        }
        
        List<Integer> list =new ArrayList<>();
        list.add(0);
        int max = cnt[0];
        for(int i=1;i<3;i++){
            if(max < cnt[i]){
                list.clear();
                list.add(i);
                max=cnt[i];
            }else if(max==cnt[i]){
                list.add(i);
            }
        }
        
        int[] answer =new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i)+1;
        }
        
        return answer;
    }
}