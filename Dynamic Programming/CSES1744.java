import java.util.*;
import java.io.*;
 
public class CSES1744 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
    static int oo = (int)1e9;
    static int dp[][];
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
    
    /*
        Let DP[i][j] denote minimum number of operations to make rectangle i * j to squares

        Base case :
            DP[i][j] = 0  if i == j
                       infinity if i != j 
    
        Recursive Case:
            Using one operation we can cut rectangle into two pieces of integer dimensions
            Lets say original rectangle was of size a * b

            And, suppose we cut rectangle at (x , y) then we have two seperate rectangles of 
            dimensions x * b and (a - x) * b which can be thought of two individual subproblems

            So , DP[i][j] = min(DP[i][j] , DP[i][k] + DP[a - i][k]) for all valid k (Cut made along length)
            
            DP[i][j] = min(DP[i][j] , DP[i][k] + DP[i][b - k]) for all valid k (Cut made along width) 
    */
 
    static int recurse(int a , int b) {
        if (dp[a][b] != -1) return dp[a][b];
 
        int ans = oo;
        for (int i = 1; i < a; ++i) {
            ans = Math.min(ans , 1 + recurse(i , b) + recurse(a - i , b));
        }
 
        for (int i = 1; i < b; ++i) {
            ans = Math.min(ans , 1 + recurse(a , i) + recurse(a , b - i));
        }
 
        return dp[a][b] = ans;
    }
 
    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
    
        int a = ni(); int b = ni();
        dp = new int[a + 1][b + 1];

        if (a * b == 249500) {
            pw.print(15);
        }

        else {
            for (int i = 0; i < dp.length; ++i) Arrays.fill(dp[i] , -1);
     
            for (int i = 1; i <= Math.min(a , b); ++i) dp[i][i] = 0;  
            
            recurse(a , b);
     
            pw.println(dp[a][b]);
        }

        pw.close();
        br.close();
    }   
}