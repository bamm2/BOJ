import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
            Map<String,Integer> map ;
            map = init(want,number);

            for(int i=0;i<discount.length-9;i++){
                for(int j=i;j<i+10;j++){
                    String chk = discount[j];
                    if(map.containsKey(chk)){
                        Integer value = map.get(chk);
                        if(value==0) {
                            break;
                        }else{
                            map.put(chk,--value);
                        }
                    }else{
                        break;
                    }
                }
                if(isOk(map)) answer++;
                map=init(want,number);
            }

        return answer;
    }
    
     private static boolean isOk(Map<String,Integer> map ){
        for(Map.Entry<String,Integer> tmp : map.entrySet()){
            if(tmp.getValue()!=0) return false;
        }
        return true;
    }

    private static Map<String,Integer> init(String[] want, int[] number){
        Map<String, Integer> tmp =new HashMap<>();
        for(int i=0;i<want.length;i++){
            tmp.put(want[i],number[i]);
        }

        return tmp;
    }

}