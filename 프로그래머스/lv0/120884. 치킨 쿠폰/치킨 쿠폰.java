class Solution {
    public int solution(int chicken) {
        int coupon = chicken;
        int service = 0 ; 
        
        while(true){
            int newCoupon=0;
            int cp=coupon/10;
            newCoupon+=cp;
            service+=cp;
            
            coupon%=10;
            coupon+=newCoupon;
            
            if(cp==0) break;
        }
        
        return service;
    }
}