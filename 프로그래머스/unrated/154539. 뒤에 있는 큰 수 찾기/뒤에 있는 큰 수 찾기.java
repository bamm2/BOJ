import java.util.Stack;


class Solution {
    
     static class Point {
            int idx, number;

            Point(int idx, int number) {
                this.idx = idx;
                this.number = number;
            }
        }
    
    public int[] solution(int[] numbers) {
       Stack<Point> stack = new Stack<>();

            int[] ans = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                if (stack.isEmpty()) stack.push(new Point(i, numbers[i]));
                else {
                    while (!stack.isEmpty() && stack.peek().number < numbers[i]) {
                        Point pop = stack.pop();
                        ans[pop.idx] = numbers[i];
                    }
                    stack.push(new Point(i, numbers[i]));
                }
            }

            while (!stack.isEmpty()) {
                ans[stack.pop().idx] = -1;
            }

            return ans;
    }
}