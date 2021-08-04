import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CoinChange_md {
    // 主要思想 dp[F] = dp[]


    public int coinChange(int[] coins, int amount) {
        if(amount==0){return 0;}
        List <Integer>dp = new ArrayList<>();
        dp.add(0);
        int i = 1;
        while (i<=amount){
            List <Integer>temp = new ArrayList<>();
            for(int coin :coins){
                if(i-coin==0){
                    temp.add(dp.get(i-coin)+1);
                }
                if(i-coin>0){
                    if(dp.get(i-coin)!=-1){
                        temp.add(dp.get(i-coin)+1);
                    }
                }
            }
            if(temp.size()==0){
                dp.add(-1);
            }else {
                int dp_num = temp.get(0);
                for(int j =0;j<temp.size();j++){
                    if(dp_num>temp.get(j)){
                        dp_num = temp.get(j);
                    }
                }
                dp.add(dp_num);
            }
            i = i+1;
        }
        return dp.get(amount);
    }

    public static void main(String []agrs){
        CoinChange_md coinChange_md = new CoinChange_md();
        int []coins = {2,5,7};
        int result = coinChange_md.coinChange(coins,1);
        System.out.println(result);
    }
}
