import java.io.*;
import java.util.*;

class CSES1158 {
    
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    static int n , x , cost[] , page[] , dp[][];

    static int ni() { 
        return Integer.parseInt(st.nextToken()); 
    }


    /*
        Let DP[i][j] denote maximum pages bought using using first i books

        Base Case:
            DP[0][i] = 0
            DP[i][0] = 0

        Recursive Case:

            We have two options 
            1) Ignore the ith book then DP[i][j] = DP[i - 1][j]
            2) Take the ith book then DP[i][j] = DP[i - 1][j - cost[i]]

            DP[i][j] = max(DP[i - 1][j] , page[i] + DP[i - 1][j - cost[i]])
    */
            
    public static void main(String args[]) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);
        n = ni(); x = ni();
        cost = new int[n + 1];
        page = new int[n + 1];

        dp = new int[n + 1][x + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            cost[i + 1] = ni();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            page[i + 1] = ni();
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= x; ++j) {
                if (j < cost[i]) dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = Math.max(dp[i - 1][j] , page[i] + dp[i - 1][j - cost[i]]);
                }
            }
        }

        pw.println(dp[n][x]);
        pw.close();
        br.close();
    }
}