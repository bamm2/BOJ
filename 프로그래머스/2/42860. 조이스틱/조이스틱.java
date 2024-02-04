import java.util.*;

class Solution {
    public int solution(String name) {
        char[] arr = name.toCharArray();
        int size = name.length();
        
        int alphabetMove = 0;
        List<Integer> list =new ArrayList<>();
        for(int i=0;i<size;i++){
            if(arr[i]!='A'){
                alphabetMove += Math.min(arr[i]-'A','Z'-arr[i]+1);
                list.add(i);
            }
        }
        
        int answer = list.size()!=0 ? list.get(list.size()-1) : 0; // 정방향으로만 갔을 때의 경우 (최악의 경우)
        for(int i=0;i<list.size();i++) { // get(i) 가 기준점
            int front;
            int back;
            if(i+1==list.size()){ // A가 아닌 마지막 알파벳  
                if(list.size()==1){  // A가 아닌 알파벳이 하나라면 
                    front = list.get(i);
                    back = size-list.get(i); 
                }else{ // 2개 이상일 경우 
                    front = list.get(i-1)*2 + size -list.get(i);
                    back = list.get(i-1) + (size -list.get(i))*2;
                }
            }else{
                front = list.get(i)*2 + size - list.get(i+1); // 앞에서 왔다 다시 돌아가서 뒤로 가는 경우 
                back = list.get(i) + (size-list.get(i+1))*2; // 뒤로 갈만큼 갔다가 다시 돌아와서 앞으로 가는 경우 
            }
            answer = Math.min(answer,Math.min(front,back)); // 그 중 최 소 ! 
        }
        
        answer+=alphabetMove;
        
        return answer;
    }
}