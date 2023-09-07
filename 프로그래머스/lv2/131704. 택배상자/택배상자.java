import java.util.Stack;

class Solution {
    public int solution(int[] order) {
   Stack<Integer> stack = new Stack<>();
            int ans = 0;
            int idx = 0;
            while (idx != order[0]) {
                stack.push(idx++);
            }
            int standard = order[0];
            for (int i = 0; i < order.length; i++) {
                int curr = order[i];
                if(curr> standard){
                    while (curr>standard){
                        stack.push(standard);
                        standard++;
                    }
                    ans++;
                    standard++;
                }else{
                    if(standard==curr){
                        standard++;
                        ans++;
                    }else if(stack.peek()==curr){
                        stack.pop();
                        ans++;
                    }else{
                        break;
                    }
                }
            }
        
            return ans;
    }
}