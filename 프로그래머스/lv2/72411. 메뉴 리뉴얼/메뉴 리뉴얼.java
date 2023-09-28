import java.util.*;

class Solution {
    static Map<String,Integer> hm =new HashMap<>();
        static List<String >list =new ArrayList<>();

        public String[] solution(String[] orders, int[] course) {
            String[] answer = {};
            for(int i=0;i<orders.length;i++){
                char[] chars = orders[i].toCharArray();
                Arrays.sort(chars);
                System.out.println("Arrays.toString(chars) = " + Arrays.toString(chars));
                for(int j=1;j<=chars.length;j++) {
                    comb(0,0,chars,"",j);
                }
            }

            int[] maxCnt = new int[11];

            List<Map.Entry<String,Integer>> entryList = new LinkedList<>(hm.entrySet());
            entryList.sort(Map.Entry.comparingByKey());
            entryList.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

            for(Map.Entry<String,Integer> entry : entryList){
                String key =entry.getKey();
                int value =entry.getValue();
                System.out.println("key = " + key);
                System.out.println("value = " + value);
                if(course[0]>key.length() || value==1) continue;
                for(int i=0;i<course.length;i++){
                    if(key.length() == course[i]){
                        if(maxCnt[key.length()]<=value){
                            maxCnt[key.length()]=value;
                            list.add(key);
                        }
                        break;
                    }
                }
            }

            Collections.sort(list);
            String[] ans = list.toArray(answer);
            return ans;
        }

        private void comb(int start, int cnt,char[] chars,String str,int goal) {
            if(goal==cnt){
                hm.put(str,hm.getOrDefault(str,0)+1);
                return;
            }

            for(int i=start;i<chars.length;i++){
                comb(i+1,cnt+1,chars,str+chars[i],goal);
            }

        }
}