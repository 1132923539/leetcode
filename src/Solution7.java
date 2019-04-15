public class Solution7 {

    /**
     * 拆分数组可使用 charAt[] 优化,可节省一个数组
     * strs[0].charAt(0);
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {

        if(strs == null || 0 == strs.length) return "";
        char[][] charss=new char[strs.length][];

        for(int i=0;i<strs.length;i++){
            charss[i] = strs[i].toCharArray();
        }

        int minLength = charss[0].length;
        for(char[] chars:charss){
            if(chars.length<minLength) minLength = chars.length;
        }

        StringBuilder sb = new StringBuilder();
        for(int j=0;j<minLength;j++){
            for(int i=0;i<charss.length-1;i++){
                if(charss[i][j] != charss[i+1][j]) return sb.toString();
            }
            sb.append(charss[0][j]);
        }
        return sb.toString();

    }
}
