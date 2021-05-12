import java.util.*;
import java.io.*;

public class CSES1716 {
	
	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st;

	static int ni() {
		return Integer.parseInt(st.nextToken());
	}

    /*
		According to stars and bars technique number of ways of distributing
		m objects in n boxes is C(n + m - 1 , m)

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
			st = new StringTokenizer(br.readLine());
			
			int n = ni();
			int m = ni();

			long mod = 1000_000_007;

			long f[] = new long[n + m];
			f[0] = 1l;

			for (int i = 1; i < n + m; ++i) {
				f[i] = (long)i * f[i - 1];
				f[i] %= mod;
			}

			long ans = f[n + m - 1];

			ans *= modInv(f[n - 1] , mod);
			ans %= mod;
			ans *= modInv(f[m] , mod);
			ans %= mod;
			pw.println(ans);

			pw.close();
			br.close();
		}   
	}