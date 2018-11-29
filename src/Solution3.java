import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;

/**
 * @author eltons,  Date on 2018-11-26.
 */
public class Solution3 {

    //暴力法
    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int start = 0;
        int maxChildLength = 0;
        StringBuffer sb=new StringBuffer();
        while (start < c.length){
            int offset;
            if((offset=isContains(sb,c[start]))!=-1){
                //注意sb的删除函数
                sb.delete(0,sb.length());
                start=start-offset;
            }
            sb.append(c[start]);
            start++;
            maxChildLength=Math.max(maxChildLength,sb.length());
        }
        return maxChildLength;
    }

    //abcad
    private int isContains(StringBuffer sb,char c){
        char[] chars=sb.toString().toCharArray();
        int i=0;
        while (i<chars.length){
            if (chars[i]==c){
                return chars.length-i-1;
            }
            i++;
        }
        return -1;
    }

    @Test
    public void test() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("C:\\Users\\lenovo\\Desktop\\a.log"));
        String s = new String(bytes, "utf-8");

        Instant start = Instant.now();
        System.out.println(lengthOfLongestSubstring(s));
        Instant mid=Instant.now();
        System.out.println(lengthOfLongestSubstring1(s));
        Instant end=Instant.now();
        System.out.println("未优化版："+Duration.between(start,mid).toMillis());
        System.out.println(Duration.between(mid,end).toMillis());

    }

    //优化解法
    public int lengthOfLongestSubstring1(String s) {
        HashSet<Character> set = new HashSet<Character>();
        int len=s.length();
        int max=0,i=0,j=0;
        //afgtdst
        while (i<len && j<len){
            if (!set.contains(s.charAt(i))){
                set.add(s.charAt(i++));
                max=Math.max(set.size(),max);
            }else {
                set.remove(s.charAt(j++));
            }
        }
        return max;
    }
}
