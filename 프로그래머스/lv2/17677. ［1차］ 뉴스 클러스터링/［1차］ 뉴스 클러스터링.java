import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
         String compA =str1.toLowerCase();
            String compB = str2.toLowerCase();

            Map<String,Integer> aMap ;
            Map<String,Integer> bMap ;

            int intersectCnt = 0;
            int unionCnt = 0;

            aMap=makeMap(compA);
            bMap=makeMap(compB);

            for(Map.Entry<String,Integer> entry : aMap.entrySet()){
                String key = entry.getKey();
                if(bMap.containsKey(key)){
                    int union = Math.max(aMap.get(key),bMap.get(key));
                    int inter = Math.min(aMap.get(key),bMap.get(key));
                    unionCnt+=union;
                    intersectCnt+=inter;
                }else{
                    Integer value = aMap.get(key);
                    unionCnt+=value;
                }
            }

            for(Map.Entry<String,Integer> entry : bMap.entrySet()){
                String key = entry.getKey();
                if(aMap.containsKey(key)) continue;
                    Integer value = bMap.get(key);
                    unionCnt+=value;
            }

            int ans = (int)(((double)intersectCnt/unionCnt)*65536) ;
            if(aMap.size()==0 && bMap.size()==0 ) ans = 65536;

            return ans;
    }
     private static Map<String,Integer> makeMap(String s){
            Map<String,Integer> tmpMap =new HashMap<>();
            for(int i=0;i<s.length()-1;i++){
                if(!isChar(s.charAt(i)) || !isChar(s.charAt(i+1))) continue;
                String tmp =s.substring(i,i+2);
                tmpMap.put(tmp,tmpMap.getOrDefault(tmp,0)+1);
            }
            return tmpMap;
        }

        private static boolean isChar(char c ){
            return 'a'<= c && c<= 'z';
        }
}