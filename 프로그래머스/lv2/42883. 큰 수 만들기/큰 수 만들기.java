import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
             Stack<Integer> stack =new Stack<>();
            int idx = 0;
            for(int i=0;i<number.length();i++){
                int curr = number.charAt(i)-'0';
                if(stack.isEmpty()) stack.push(curr);
                else{
                    if(stack.peek()>=curr) stack.push(curr);
                    else{
                        while (!stack.isEmpty()){
                            if(stack.peek()>=curr) break;
                            stack.pop();
                            k--;
                            if(k==0) break;
                        }
                        if(k==0){
                            idx=i;
                            break;
                        }
                        stack.push(curr);
                    }
                }
                if(i==number.length()-1) idx=i+1;
            }
            if(k!=0){
                while (!stack.isEmpty()){
                    stack.pop();
                    k--;
                    if(k==0) break;
                }
            }
            String substring = number.substring(idx);
            while (!stack.isEmpty()){
                substring = stack.pop() + substring;
            }
            return substring;
    }
}