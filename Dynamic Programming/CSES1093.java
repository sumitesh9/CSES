import java.util.*;
import java.io.*;
 
class CSES1093 {
	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;
 
	static int ni() { 
		return Integer.parseInt(st.nextToken());
	}
 
	/*
		If sum of first n numbers is odd then obviously there is no way you can 
		divide them equally in two sets , hence the answer is 0
 
		Otherwise , 
 
		Let sum = n * (n + 1) / 2 be sum of first n natural number
		Then we need to allocate sum / 2 to both sets
 
		sum / 2 = n * (n + 1) / 4;
 
		Let DP[i][j] denote number of ways of making j using subarray arr[0...i]
 
		This question can also be thought of as 0-1 Knapsack
 
		Base case:
 
			DP[0][0] = 1 
 
		Recursive case :
 
			At every stage we have to possibilites 
			1) Take ith  number  (DP[i - 1][j - i])
			2) Ignore ith number (DP[i - 1][j])
 
			DP[i][j] = DP[i -1][j] + DP[i - 1][j - i]
		
	*/
 
	public static void main(String args[])throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		pw = new PrintWriter(System.out);
 
		int mod = 1000_000_007;
		int n = ni();
		int sum = (n * (n + 1)) / 2;
 
		if (sum % 2 == 1) {
			pw.println("0");
		}
 
		else {
			sum /= 2;
			int dp[][] = new int[n][sum + 1];
			dp[0][0] = 1;
 
			for (int i = 1; i < n; ++i) {
    			for (int j = 0; j <= sum; ++j) {
      				dp[i][j] = dp[i - 1][j];
      				int rem = j - i;
      				if (rem >= 0) {
						dp[i][j] += dp[i - 1][rem];
						dp[i][j] %= mod;
      				}
    			}
  			}
  			pw.print(dp[n - 1][sum]);
		}
 
		pw.close();
		br.close();
	}
}