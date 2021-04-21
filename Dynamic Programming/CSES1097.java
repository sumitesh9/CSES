import java.util.*;
import java.io.*;
 
public class CSES1097 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
    static int n;
    static long oo;
    static long dp[][] , arr[];
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    static long nl() {
        return Long.parseLong(st.nextToken());
    }


    /*
        Let DP[i][j] denote maximum sum that first player can collect if subarray arr[i...j] 
        is considered

        Naturally final answer is -> DP[0][n - 1]

        Base cases :
            DP[i][j] = arr[i]  if i == j
            DP[i][j] = max(arr[i] , arr[j]) if j = i + 1

        Recursive case :
    
            Let current subarray be arr[i....j] both bounds are inclusive

            We have two choices at every stage 
            1) First player took leftmost element of current subarray -

                In this case first player adds arr[i] to his sum and since second player will play
                optimally he will try to reduce first player's score in subarray arr[i + 1 .... j]
                which he can achieve by taking either i + 1 th or j th element

                Hence this leads to

                ScoreByTakingLeftMost = arr[i] + min(DP[i + 2][j] ,  DP[i + 1][j - 1])

            2) Now lets assume first player took rightmost element of current subarray i.e arr[j]
                In this case first player adds arr[j] to his sum and second player will try to 
                reduce sum from subarray arr[i.. j - 1] by taking either i th or j - 1 th element

                Hence this leads to 
                ScoreByTakingRightMost = arr[j] + min(DP[i + 1][j - 1] , DP[i][j - 2]);
    
            And finally
            DP[i][j] = max(ScoreByTakingLeftMost , ScoreByTakingRightMost);

    */

    static long recurse(int l , int r) {
        if (dp[l][r] != -1) return dp[l][r];

        long ans = -oo;

        long x = oo;
        if (l + 2 < n) x = Math.min(x , recurse(l + 2 , r));
        if (l + 1 < n && r - 1 >= 0) x = Math.min(x , recurse(l + 1 , r - 1));

        ans = Math.max(ans , arr[l] + x);

        x = oo;
        if (l + 1 < n && r - 1 >= 0) x = Math.min(x , recurse(l + 1 , r - 1));
        if (r >= 2) x = Math.min(x , recurse(l , r - 2));

        ans = Math.max(ans , arr[r] + x);
        return dp[l][r] = ans;
    }
 
    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
        
        n = ni();
        arr = new long[n];
        dp = new long[n][n];
        oo = (long)1e12;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) {
            arr[i] = nl();
        }

        for (int i = 0; i < n; ++i) Arrays.fill(dp[i] , -1);

        for (int i = 0; i < n; ++i) {
            dp[i][i] = arr[i];
            if (i + 1 < n) dp[i][i + 1] = Math.max(arr[i] , arr[i + 1]);
        }
        
        pw.println(recurse(0 , n - 1));

        pw.close();
        br.close();
    }   
}