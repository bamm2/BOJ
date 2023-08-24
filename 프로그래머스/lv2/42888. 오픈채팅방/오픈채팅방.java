import java.util.*;

class Solution {
    
    static class Info {
            int idx;
            String log;
            String nickname="";

            Info(int idx, String log, String nickname) {
                this.idx = idx;
                this.log = log;
                this.nickname=nickname;
            }
        }
    
    public String[] solution(String[] record) {
         Map<String, List<Info>> map = new HashMap<>();
            int size = 0;
            int changeCnt = 0;
            for (int i = 0; i < record.length; i++) {
                String[] s = record[i].split(" ");
                String log = s[0];
                String id = s[1];
                String nickname="";
                
                if(!log.equals("Leave")) nickname += s[2];
                
                if (!log.equals("Change")) size++;
                else changeCnt++;
                
                List<Info> infos;
                if (map.containsKey(id)) {
                    infos = map.get(id);
                } else {
                    infos = new ArrayList<>();
                }
                
                infos.add(new Info(i-changeCnt, log, nickname));
                map.put(id, infos);
                
            }

            String[] ans = new String[size];
        
            for (Map.Entry<String, List<Info>> info : map.entrySet()) {
                List<Info> value = info.getValue();
                String name = "";
                
                for (int i = value.size() - 1; i >= 0; i--) {
                    Info infos = value.get(i);
                    
                    if(infos.log.equals("Change")) continue;
                    
                    if(name.equals("")) name = findNickName(value);
                    ans[infos.idx]=makeComment(name,infos.log);
                }
            }

            return ans;
    }
      private static String findNickName(List<Info> list){
            Info info = list.get(list.size() - 1);
            String nickname = "";
            if(info.nickname.equals("")) nickname = list.get(list.size()-2).nickname;
            else nickname = info.nickname;;

            return nickname;
        }

        private static String makeComment(String name, String log) {
            String comment = name+"님이 ";
            switch (log) {
                case "Enter":
                    comment+="들어왔습니다.";
                    break;
                case "Leave":
                    comment+="나갔습니다.";
                    break;
            }
            return comment;
        }
}