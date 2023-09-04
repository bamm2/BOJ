import java.util.HashSet;

class Solution {
    public int solution(int[] topping) {
        int size =topping.length-1;

            int[] left =new int[size+1];
            int[] right =new int[size+1];
            HashSet<Integer> leftHashSet = new HashSet<>();
            HashSet<Integer> rightHashSet = new HashSet<>();

            for(int i=0;i<topping.length;i++){
                int leftNumber = topping[i];
                int rightNumber = topping[size-i];
                if(!leftHashSet.contains(leftNumber)){
                    leftHashSet.add(leftNumber);
                }
                left[i]=leftHashSet.size();

                if(!rightHashSet.contains(rightNumber)){
                    rightHashSet.add(rightNumber);
                }
                right[size-i]=rightHashSet.size();
            }

            int ans =0;

            for(int i=0;i<topping.length-1;i++){
                if(left[i]==right[i+1]) ans++;
            }

            return ans;
    }
}