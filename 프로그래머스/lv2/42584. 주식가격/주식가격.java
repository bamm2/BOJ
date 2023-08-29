import java.util.Stack;

class Solution {
    
    static class Point {
            int idx, price;

            Point(int idx, int price) {
                this.idx = idx;
                this.price = price;
            }
        }
    
    public int[] solution(int[] prices) {
        Stack<Point> stack = new Stack<>();
            int[] ans = new int[prices.length];
            for (int i = 0; i < prices.length; i++) {
                if (stack.isEmpty()) stack.push(new Point(i, prices[i]));
                else {
                    if (stack.peek().price <= prices[i]) stack.push(new Point(i, prices[i]));
                    else {
                        while(!stack.isEmpty() && stack.peek().price>prices[i]){
                            Point pop = stack.pop();
                            ans[pop.idx]=i-pop.idx;
                        }
                        stack.push(new Point(i,prices[i]));
                    }
                }
            }
            while (!stack.isEmpty()){
                int idx = stack.pop().idx;
                ans[idx] = prices.length-idx-1;
            }

            return ans;
    }
}