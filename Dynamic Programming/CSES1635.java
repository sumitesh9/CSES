import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.text.*;
 
public class CSES1635 {
    static long mod = 1000_000_007;
    static long mod1 = 998244353;
    static boolean fileIO = false;
    static boolean memory = true;
    static FastScanner f;
    static PrintWriter pw;
    static double eps = (double)1e-6;
    static int oo = (int)1e9;
 
    static int n , dp[] , x , coins[];
 
    // DP[x] = Number of ways to sum x
    
    /* Base cases
       1) DP[0] = 1
    */
 
    /* Recursive cases
        For every coin c in coins if c <= x
        DP[x] = DP[x] + DP[x - c]
    */
 
    public static void solve() throws Exception {
        n = f.ni();
        x = f.ni();
        dp = new int[x + 1];
        coins = new int[n];
 
        for (int i = 0; i < n; ++i) coins[i] = f.ni();
 
        Arrays.sort(coins);
        dp[0] = 1;
 
        for (int i = 1; i <= x; ++i) {
            for (int c : coins) {
                if (c <= i) {
                    dp[i] += dp[i - c];
                    if (dp[i] >= mod) dp[i] -= mod;
                }
                else break;
            }
        }
 
        pn(dp[x]);
    }    
 
    public static void main(String[] args)throws Exception {
        if(memory) new Thread(null, new Runnable() {public void run(){try{new CSES1635().run();}catch(Exception e){e.printStackTrace();System.exit(1);}}}, "", 1 << 28).start();
        else new CSES1635().run();
    }
        
/******************************END OF MAIN PROGRAM*******************************************/
    void run()throws Exception {
        f = new FastScanner();
        pw = new PrintWriter(System.out);
        //
        int t = 1;
        int tt = 1;
 
        while(t --> 0) {
            //p("Case #" + (tt++) + ": ");
            //fw.write("\n");
            solve();
        }
        
        pw.flush(); 
        pw.close(); 
    }
 
    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(String str) throws Exception {
            try {
                br = new BufferedReader(new FileReader("!a.txt"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        public String next()throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
 
        public int ni() throws IOException {return Integer.parseInt(next());}
 
        public long nl() throws IOException {return Long.parseLong(next());}
 
        public String nextLine() throws IOException {return br.readLine();}
 
        public double nd() throws IOException {return Double.parseDouble(next());}
    }
 
    public static void pn(Object... o) {for(int i = 0; i < o.length; ++i) pw.print(o[i] + (i + 1 < o.length ? " ": "\n"));}
    public static void p(Object... o) {for(int i = 0; i < o.length; ++i) pw.print(o[i] + (i + 1 < o.length ? " " : ""));}
    public static void pni(Object... o) {for(Object obj : o) pw.print(obj + " "); pw.println(); pw.flush();}
}