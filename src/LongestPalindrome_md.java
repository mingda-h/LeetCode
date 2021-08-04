public class LongestPalindrome_md {
    public String longestPalindrome(String s) {
        String result = "";
        int max = 0;
        Boolean [][] dp = new Boolean[s.length()][s.length()];

        // 当i+1=j时，s[i] = s[j] dp = true, s[i]!=s[j] dp[i][j]= false
        // 当i=j时    dp[i][j] = true
        // 当s[i]=s[j]时 ,dp = dp[i+1][j-1] 当是s[i]！=s[j]，dp[i][j] = false

        //当dp[i][j]==true 记录长度，和串，保留最大的
        for(int j = 0;j<s.length();j++){
            for(int i = 0;i<j;i++){
                if(s.charAt(i)==s.charAt(j)){
                    if(i+1<j-1){
                        dp[i][j] = dp[i+1][j-1];
                    }else {
                        dp[i][j] = true;
                    }
                }else {
                    dp[i][j] = false;
                }
                if(dp[i][j]){
                    if(max<j-i+1){
                        max = j-i+1;
                        result = s.substring(i,j+1);
                    }
                }
            }
        }

//        for(int i =0;i<s.length();i++) {
//            for (int j = 0; j <= i; j++) {
//                if (s.charAt(i) == s.charAt(j)) {
//                    if (i - 1 <= j + 1) {
//                        dp[i][j] = true;
//                    } else {
//                        dp[i][j] = dp[i-1][j+1];
//                    }
//                } else {
//                    dp[i][j] = false;
//                }
//                if(dp[i][j]){
//                    if(i-j+1>max){
//                        max = i-j+1;
//                        result = s.substring(j,i+1);
//                    }
//                }
//            }
//        }
        return result;
    }

    public static void main(String []args){
        LongestPalindrome_md longestPalindromemd = new LongestPalindrome_md();
        String s = longestPalindromemd.longestPalindrome("sdfsadfsfdgfghfgjghktdfgsdfgdsregdhfrh");
        System.out.println(s);
    }
}
