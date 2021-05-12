import java.util.*;
import java.io.*;

public class CSES1717 {
	
	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st;

	static int ni() {
		return Integer.parseInt(st.nextToken());
	}

    /*
		Answer to the problem is number of dearrangements of sequence
		of size n

		Dearrangement of sequence is permutation of that sequence where
		a[i] != i for all i.

		Problem can be solved using DP or alternative by IEP.
		Let DP[i] = Number of dearrangements of sequence of size n

		Base Case:
			DP[0] = 0 (No way to choose a element)
			DP[1] = 0 (1st element will always be at first position)
			DP[2] = 1 ([2 , 1] is only dearrangement possible)

		Recursive Case: (Refer to Page 214 of CP Handbook by Antti Laaksonen)
		There are i - 1 ways to choose an element x that
		replaces the element 1. In each such case, there are two choices:
		Choice 1: We also replace the element x with the element 1. After this, the
		remaining task is to construct a derangement of i - 2 elements.

		Choice 2: We replace the element x with some other element than 1. Now we
		have to construct a derangement of i - 1 element, because we cannot replace the
		element x with the element 1, and all other elements must be changed.

		So DP[i] = (i - 1) * (DP[i - 2] + DP[i - 1])

		Sum of both the choices multiplied by number of ways of choosing the index
    */		
		
		public static void main(String args[]) throws Exception {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(System.out);
			st = new StringTokenizer(br.readLine());
			
			int n = ni();

			if (n < 2) pw.print(0);

			long mod = 1000_000_007;
			long dp[] = new long[n + 1];
			dp[2] = 1;

			for (int i = 3; i <= n; ++i) {
				dp[i] = ((i - 1) * (dp[i - 2] + dp[i - 1]) % mod) % mod;
			}

			pw.println(dp[n]);

			pw.close();
			br.close();
		}   
	}