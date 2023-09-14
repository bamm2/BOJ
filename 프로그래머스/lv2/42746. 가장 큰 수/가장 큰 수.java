import java.util.Arrays;

class Solution {
    
     static class Number implements Comparable<Number> {
            String str;
            int num;

            Number(String str, int num) {
                this.str = str;
                this.num = num;
            }

            @Override
            public int compareTo(Number o) {
                int A =this.str.length();
                int B =o.str.length();

                int aLoop = findLoop(A);
                int bLoop = findLoop(B);

                String aStr = makeNum(this.str, aLoop);
                String bStr = makeNum(o.str,bLoop);

                return bStr.compareTo(aStr);
            }

            private int findLoop(int num) {
                switch (num){
                    case 1 :
                        return 3;
                    case 2: case 3:
                        return 2;
                    case 4:
                        return 1;
                }
                return -1;
            }

            private String makeNum(String str, int a) {
                String tmp = "";
                for(int i=0;i<a;i++){
                    tmp+=str;
                }
                return tmp;
            }
        }
    
    public String solution(int[] numbers) {
       Number[] numberArr = new Number[numbers.length];

           for(int i=0;i<numbers.length;i++){
               int num = numbers[i];
               String str =String.valueOf(num);
               numberArr[i]=new Number(str,num);
           }

           Arrays.sort(numberArr);

           String ans ="";

           for(int i=0;i<numberArr.length;i++){
               ans+=numberArr[i].str;
           }
            String comp="";
            comp =ans.replaceAll("0","");
            if(comp.length()==0) ans = "0";
            return ans;
    }
}