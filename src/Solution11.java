public class Solution11 {

    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issip"));

    }

    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        int sLength = haystack.length();
        int nLength = needle.length();

        int j = 0;
        for (int i = 0; i < sLength; i++) {
            System.out.println(haystack.charAt(i) +":::::"+ needle.charAt(j));
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                System.out.println(i+"-------"+j);
                if (j == nLength) return i - j + 1;
            } else {
                i = i-j;
                j = 0;
            }
        }
        return -1;
    }
}
