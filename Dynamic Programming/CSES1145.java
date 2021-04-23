import java.util.*;
import java.io.*;

class CSES1145{
	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;

	static int ni() { 
		return Integer.parseInt(st.nextToken());
	}

	/*
		Optimal solution involves using binary search with DP

		Let DP[] of size n be filled with all zero

		DP[] holds most optimal LIS at every iteration , because in this approach we
		fill every element of the array to correct position of DP array using 
		binary search as it will be always sorted.

		Finally length of dp is our answer
	*/

	public static void main(String args[])throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		pw = new PrintWriter(System.out);

		int n = ni();

		int a[] = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; ++i) a[i] = ni();	

		int dp[] = new int[n];

		int len = 0;
		for (int i = 0; i < n; ++i) {
			int pos = Arrays.binarySearch(dp , 0 , len , a[i]);
			if (pos < 0) pos = -(pos + 1);
			dp[pos] = a[i];
			if (pos == len) ++len;
		}

		pw.println(len);

		pw.close();
		br.close();
	}
}