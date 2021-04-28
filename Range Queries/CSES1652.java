import java.util.*;
import java.io.*;
public class CSES1652 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    static int n;

    /*
        This is simple 2D DP problem

        Let DP[i][j] denote number of trees in rectangle with top left corner as
        (1 , 1) and bottom right corner as (i , j) (both indices inclusive)

        States and transitions are pretty simple too (Involves inclusion exclusion principle)
        Refer to code below
    */

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);


        int n = ni(); int  q = ni();
        int g[][] = new int[n + 2][n + 2];
 
        for (int i = 1; i <= n; ++i) {
            String s = br.readLine();
            for (int j = 1; j <= n; ++j) {
                g[i][j] = s.charAt(j - 1) == '*' ? 1 : 0;
            }
        }
 
        int dp[][] = new int[n + 2][n + 2];
        
        // First row (Base case)
        for (int i = 1; i <= n; ++i) {
            dp[1][i] = g[1][i] + dp[1][i - 1];
        }
    
        // First column (Base case)
        for (int i = 2; i <= n; ++i) {
            dp[i][1] = g[i][1] + dp[i - 1][1];
        }
    
        // Recursive case
        for (int i = 2; i <= n; ++i) {
            for (int j = 2; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + g[i][j] - dp[i - 1][j - 1];
            }
        }
 
        StringBuffer sb = new StringBuffer("");
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = ni(); int y1 = ni();
            int x2 = ni(); int y2 = ni();
 
            int ans = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            sb.append(String.valueOf(ans + "\n"));
        }
        pw.println(sb);
        
        pw.close();
        br.close();
    }
}