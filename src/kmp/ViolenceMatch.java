package kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅谷你好";
        String str2 = "尚硅谷你尚硅谷你~";
        int match = violenceMatch(str1, str2);
        System.out.println("match:"+match);
    }

    private static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int strLength1 = str1.length();
        int strLength2 = str2.length();
        int i = 0,j =0;
        while (i < strLength1 && j < strLength2) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            }else{
                i = i - (j - 1);
                j = 0;
            }
        }
        if(j == strLength2)
            return (i-j);
        else
            return -1;
    }
}
