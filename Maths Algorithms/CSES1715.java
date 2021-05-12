import java.util.*;
import java.io.*;

public class CSES1715 {
	
	static BufferedReader br;
	static PrintWriter pw;

    /*
		Answer to the problem is just number of unique permutations
		which is  n ! / (a ! * b ! * c ! ....) where a , b , c , d ... is
		frequency of each character
    */

		static long mpow(long a , long b , long mod) {
			long ans = 1l;
			
			while (b > 0) {
				if (b % 2 == 1l) {
					ans *= a;
					ans %= mod;
				}
				b /= 2;
				a = a * a;
				a %= mod;
			}

			return ans;
		}

		static long modInv(long a , long mod) {
			return mpow(a , mod - 2l , mod);
		}
		
		
		public static void main(String args[]) throws Exception {
			br = new BufferedReader(new InputStreamReader(System.in));
			pw = new PrintWriter(System.out);
			
			String s = br.readLine();
			int n = s.length();
			long mod = 1000_000_007;

			long f[] = new long[n + 1];
			int cnt[] = new int[26];
			f[0] = 1l;

			for (int i = 1; i <= n; ++i) {
				f[i] = (long)i * f[i - 1];
				f[i] %= mod;
				++cnt[s.charAt(i - 1) - 'a'];
			}

			long ans = f[n];

			for (int i = 0; i < 26; ++i) {
				ans *= modInv(f[cnt[i]] , mod);
				ans %= mod;
			}

			pw.println(ans);

			pw.close();
			br.close();
		}   
	}