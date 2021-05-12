import java.util.*;
import java.io.*;

public class CSES1079 {
	
	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st;
	
	static int ni() {
		return Integer.parseInt(st.nextToken());
	}
	
    /*
		We can precalculate factorials and use fast modular exponentiation.
		Inverse modulos can be calculated using Fermat's little theorem
		because we have modulo under prime (1000_000_007)
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
			
			int t = ni();
			int maxN = 1000_002;
			long mod = 1000_000_007;
			
			long f[] = new long[maxN];
			f[0] = 1l;
			
			for (int i = 1; i < maxN; ++i) {
				f[i] = (long)i * f[i - 1];
				f[i] %= mod;
			}
			
			while (t --> 0) {
				st = new StringTokenizer(br.readLine());
				int a = ni();
				int b = ni();
				long ans = f[a];
				
				long d1 = modInv(f[b] , mod);
				long d2 = modInv(f[(a - b)] , mod);
				
				ans *= d1;
				ans %= mod;
				
				ans *= d2;
				ans %= mod;
				
				pw.println(ans);
			}
			
			pw.close();
			br.close();
		}   
	}