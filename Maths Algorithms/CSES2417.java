import java.util.*;
import java.io.*;

public class CSES2417 {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st;

	static int ni() {
		return Integer.parseInt(st.nextToken());
	}

    /*
		This problem can be solved using Mobius Inversion

		Claim: the solution is sum of μ(i) * C(D(i), 2) over all integer i in the array.
		
		C(x , y) = (x ! / ((y !) * (x - y) !))  : Standard Combinatorics formula 
		D(i) = Number of multiples of i in the array.

		Note that if i is greater than the maximal number that occurs in the sequence, then  
		contribution of that i to answer will 0 which is pretty easy to see why.
	
		Proof of correctness of above claim :

			If we solve this problem using inclusion exclusion principle we can show that
			above solution is correct.

			That means that we will add to the answer the number of pair that are divisible by
			some integer x if x is formed by multiplication of even number of prime numbers and
			subtract this number of pairs otherwise.
		
		References :
		https://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle (Check mobius inversion section)
		https://discuss.codechef.com/t/coprime3-editorial/6051 (Similar problem in Codechef)

    */


	public static void main(String args[]) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		st = new StringTokenizer(br.readLine());

		int n = ni();
		int maxN = 0;

		int cnt[] = new int[1000005];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; ++i) {
			int x = ni();
			maxN = Math.max(maxN , x);
			++cnt[x];
		}

		long ans = 0l;

		int lpf[] = new int[maxN + 5];
		int m[] = new int[maxN + 5];

		for (int i = 2; i <= maxN; ++i) {
			if (lpf[i] == 0) {
				lpf[i] = i;
				for (int j = i + i; j <= maxN; j += i) {
					if (lpf[j] == 0) lpf[j] = i;
				}
			}
		}

		m[1] = 1;
		
		for (int i = 2; i <= maxN; ++i) {
			if (lpf[i / lpf[i]] == lpf[i]) m[i] = 0;
			else m[i] = -1 * m[i / lpf[i]];
		}

		for (int i = 1; i <= maxN; ++i) {
			if (m[i] == 0) continue;
			long curr = cnt[i];
			for (int j = i + i; j <= maxN; j += i) {
				curr += cnt[j];
			}
			ans += ((curr * (curr - 1)) / 2) * m[i];
		}

		pw.println(ans);

		pw.close();
		br.close();
	}   
}