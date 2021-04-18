import java.util.*;
import java.io.*;
 
public class CSES1746 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
    static int mod;
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
    
    /*
        Let DP[i][j] denote number of ways of filling ith index with j
        
        Base case :
            1) If first number if 0 -> We can fill it with any number less than equal to m
            so DP[1][x] = 1 for all 1 <= x <= m

            2) Else number of ways of filling ith index with x is number of ways of filling (i - 1)th number 
            with x - 1 , x or x + 1

            Hence DP[i][x] = DP[i - 1][x - 1] + DP[i - 1][x] + DP[i - 1][x + 1]
    */

    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
 
        int n = ni();
        int m = ni();
        mod = 1000000007;
        int arr[] = new int[n + 1];
        long dp[][] = new long[n + 1][m + 2];
 
        st = new StringTokenizer(br.readLine());
 
        for (int i = 0; i < n; ++i) arr[i + 1] = ni();
            
        if (arr[1] == 0) for (int i = 1; i <= m; ++i) dp[1][i] = 1;
 
        else dp[1][arr[1]] = 1;
 
        for (int i = 2; i <= n; ++i) {
            if (arr[i] == 0) {
                for (int j = 1; j <= m; ++j) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + dp[i - 1][j + 1];
                    dp[i][j] %= mod;
                }
            }
 
            else {
                dp[i][arr[i]] = dp[i - 1][arr[i] - 1] + dp[i - 1][arr[i] + 1] + dp[i - 1][arr[i]];
                dp[i][arr[i]] %= mod;
            }
        }
 
        long ans = 0;
 
        for (int i = 1; i <= m; ++i) {
            ans += dp[n][i];
            ans %= mod;
        }
        pw.println(ans);
 
        pw.close();
        br.close();
    }   
}