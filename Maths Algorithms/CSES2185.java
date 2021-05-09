import java.util.*;
import java.io.*;

public class CSES2185 {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st;

	static int ni() {
		return Integer.parseInt(st.nextToken());
	}

	static long nl() {
		return Long.parseLong(st.nextToken());
	}

    /*
		Since n is very big hence iterating in range 1..n leads to TLE.
		Optimised solution involves idea of inclusion exclusion principle

		Reference - https://cp-algorithms.com/combinatorics/inclusion-exclusion.html
		Inclusion Exclusion Principle - 
		To compute the size of a union of multiple sets, it is necessary to sum the sizes of these sets separately, 
		and then subtract the sizes of all pairwise intersections of the sets, then add back the size of the intersections of triples of the sets,
		subtract the size of quadruples of the sets, and so on, up to the intersection of all sets.
		
		Solution :
			Number of integers divisible by i in range 1...n = floor(n / i) - (1)
			We can iterate in all subsets of primes given in input
			If subset size is odd we need to add count of integers divisible by 
			product of primes of current subset using - (1)

			Else if subset size is even then we need to subtract count of 
			integers divisible by product of primes of current subset using - (1)

    */

	static int k;
	static long a[] , n;


	public static void main(String args[]) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		st = new StringTokenizer(br.readLine());

		n = nl();
		k = ni();
		a = new long[k];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < k; ++i) a[i] = nl();

		long ans = 0l;
		for (int i = 0; i < (1l << k); ++i) {
			long p = 1;
			int cnt = 0;
			ArrayList<Long> taken = new ArrayList<>();
			for (int j = 0; j < k; ++j) {
				if ((i & (1l << j)) > 0l) {
					if (p > n / a[j]) {
						p = n + 1;
						break;
					}
					p *= a[j];
					taken.add(a[j]);
					++cnt;
				}
			}
			if (cnt == 0) continue;
			if (cnt % 2 == 1) {
				ans += n / p;
			}
			else {
				ans -= n / p;
			}
		}

		pw.println(ans);
		
		pw.close();
		br.close();
	}   
}