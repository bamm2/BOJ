import java.util.*;

class Solution {
   public String solution(String m, String[] musicinfos) {

            String ans = "";
            int compTime = 0;
            for(int i= musicinfos.length-1 ; i>=0 ;i--){
                int time = 0 ;
                String[] currMusic = musicinfos[i].split(",");
                String[] startTime = currMusic[0].split(":");
                int sH = Integer.parseInt(startTime[0]); int sM = Integer.parseInt(startTime[1]);
                String[] endTime = currMusic[1].split(":");
                int eH = Integer.parseInt(endTime[0]); int eM = Integer.parseInt(endTime[1]);
                time+= 60*(eH-sH) + eM-sM;
                String musicName = currMusic[2];
                String comp = currMusic[3];
                Queue<String > q = new ArrayDeque<>();
                for(int j=0;j<comp.length();j++){
                    char c = comp.charAt(j);
                    if(j==comp.length()-1){
                        if(c !='#') q.offer(String.valueOf(c));
                    }else{
                        if(comp.charAt(j+1)=='#'){
                            q.offer(c+"#");
                            j++;
                        }else{
                            q.offer(String.valueOf(c));
                        }
                    }
                }
                int idx = 0;
                String compStr = "";
                while (idx!=time){
                    String poll =q.poll();
                    compStr+=poll;
                    idx++;
                    q.offer(poll);
                    if(idx==time) break;
                }

                if(isOk(compStr,m)){
                    if(compTime <= time){
                        ans=musicName;
                        compTime=time;
                    }
                }
            }
            if(ans.equals("")) ans="(None)";

            System.out.println(ans);
            return ans;
        }
        private static boolean isOk(String comp, String str){

            int idx = 0;
            for(int i=0;i<comp.length();i++){
                 if(comp.charAt(i)==str.charAt(idx)){
                     idx++;
                 }else{
                     idx=0;
                        if (comp.charAt(i) == str.charAt(idx)) idx++;
                 }
                 if(idx==str.length()){
                     if(i!=comp.length()-1){
                         if(comp.charAt(i+1)=='#'){
                            idx=0;
                         }else{
                             return true;
                         }
                     }else{
                         return  true;
                     }
                 }
            }
            return false;
    }
}