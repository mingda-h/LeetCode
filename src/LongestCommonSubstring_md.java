public class LongestCommonSubstring_md {
    public String LongestCommonSubstring(String s1,String s2){
        // 构造dp状态 ， 公共字符串  A串和B串的最大公共子串stsdfsf 那么 A[n] = f ,B[m] = f,即A[n] = B [m] 即dp[n][m] = 7
        // 则必有 A[n-1]= B[m-1]  dp[n-1][m-1] = 6
        // 用dp[x][y]表示当前子串长度，即当前 A[x]B[y]公共子串的长度是由 A[x]B[y]元素值和A[x-1]B[y-1]子串长度决定 即 dp[x][y] = dp[x-1][y-1] + 1 或者 0
        // 为了便于边界处理 用A[m+1]B[n+1]来表示
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        String s = "";
        int max = 0;
        // 注意是从1开始
        for(int i = 1; i<=s1.length();i++){
            for(int j = 1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){                 // 这里的字符串位置要-1
                    dp[i][j] = dp[i-1][j-1]+1;
                    if(dp[i][j]>max){
                        max = dp[i][j];
                        s = s1.substring(i-max+1,i);                // 这里的字符串位置要-1
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return s;
    }

    public static void main(String []args){
        LongestCommonSubstring_md longestCommonSubstring_md = new LongestCommonSubstring_md();
        String st = longestCommonSubstring_md.LongestCommonSubstring("sdfsdffsf","sdfsdfsfs");
        System.out.println(st);
    }
}
