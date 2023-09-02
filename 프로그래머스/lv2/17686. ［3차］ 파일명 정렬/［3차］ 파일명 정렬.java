import java.util.Arrays;

class Solution {
    
     static class FileName implements Comparable<FileName>{
            String head,tail;
            int number,idx;

            FileName(String head,int number,String tail,int idx){
                this.head=head;
                this.number=number;
                this.tail=tail;
                this.idx=idx;
            }

            @Override
            public int compareTo(FileName o){
                if(this.head.equals(o.head)){
                    if(this.number==o.number){
                        return Integer.compare(this.idx,o.idx);
                    }else{
                        return Integer.compare(this.number,o.number);
                    }
                }else{
                    return this.head.compareTo(o.head);
                }
            }
        }
    
    public String[] solution(String[] files) {
        FileName[] fileArr =new FileName[files.length];

            for(int i=0;i<files.length;i++){
                makeFileStruct(files[i].toLowerCase(),i,fileArr);
            }

            Arrays.sort(fileArr);
            String[] ans =new String[files.length];
            for(int i=0;i<fileArr.length;i++){
                ans[i] = files[fileArr[i].idx];
            }

            return ans;
    }
      private static void makeFileStruct(String s,int idx ,FileName[] arr){
            int headEndIdx = 0;
            int numberEndIdx = 0;
            for(int i=0;i<s.length();i++){
                boolean isNumber = '0'<=s.charAt(i) && s.charAt(i)<='9';
                if(headEndIdx==0 && isNumber ){
                    headEndIdx = i;
                }else if (headEndIdx!=0 && !isNumber ){
                    numberEndIdx = i;
                    break;
                }
            }

            if(numberEndIdx==0) numberEndIdx=s.length();
            arr[idx]=new FileName(s.substring(0,headEndIdx),Integer.parseInt(s.substring(headEndIdx,numberEndIdx)),s.substring(numberEndIdx),idx);

        }
    
}