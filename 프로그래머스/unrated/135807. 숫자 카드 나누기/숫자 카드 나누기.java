class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
            int aGCD = findGCD(arrayA);
            int bGCD = findGCD(arrayB);

            int max = 0 ;
            boolean flag;
            if(bGCD!=1) {
                flag=false;
                for (int i = 0; i < arrayA.length; i++) {
                    if (arrayA[i] % bGCD == 0) {
                        flag=true;
                        break;
                    }
                }
                if(!flag) if(max<bGCD) max=bGCD;
            }

            if(aGCD!=1){
                flag=false;
                for(int i=0;i< arrayB.length;i++){
                    if(arrayB[i] % aGCD ==0){
                        flag=true;
                        break;
                    }
                }
                if(!flag) if(max<aGCD) max=aGCD;
            }

            System.out.println(max);

            return max;
        }

        private static int findGCD(int[] arr) {
            int GCD = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (GCD >= arr[i]) {
                    GCD = gcd(GCD, arr[i]);
                } else {
                    GCD = gcd(arr[i], GCD);
                }
            }
            return GCD;
        }

        private static int gcd(int A, int B) {
            if (B == 0) return A;
            return gcd(B, A % B);
        }
}