import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String skill, String[] skill_trees) {
         Map<Character,Integer> skillMap =new HashMap<>();

            for(int i=0;i<skill.length();i++){
                skillMap.put(skill.charAt(i),i);
            }

            int ans = 0 ;

            for(int i=0;i<skill_trees.length;i++){
                String str = skill_trees[i];
                int curLevel = 0 ;
                boolean flag =true;
                for(int j=0;j<str.length();j++){
                    char curr = str.charAt(j);
                    if(!skillMap.containsKey(curr)) continue;
                    if(curLevel == skillMap.get(curr)) curLevel++;
                    else{
                        flag=false;
                        break;
                    }
                }
                if(flag) ans++;
            }

            return ans;
    }
}