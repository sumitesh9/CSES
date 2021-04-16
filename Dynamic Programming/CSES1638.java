import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.text.*;
 
public class CSES1638 {
    static long mod = 1000_000_007;
    static long mod1 = 998244353;
    static boolean fileIO = false;
    static boolean memory = true;
    static FastScanner f;
    static PrintWriter pw;
    static double eps = (double)1e-6;
    static int oo = (int)1e9;

    static int n , dp[][];
    static char g[][];

    /*
        DP[i][j] = Number of ways to reach (i , j)

        Recursive case:
    
        // Number of ways to reach g[i][j] = Number of ways of reaching g[i - 1][j] + Number of ways of reaching g[i][j - 1] 
        DP[i][j] = DP[i - 1][j] + DP[i][j - 1]
    */

    public static void solve() throws Exception {
        n = f.ni();
        dp = new int[n][n];
        g = new char[n][n];

        for (int i = 0; i < n; ++i) {
            String s = f.next();
            for (int j = 0; j < n; ++j) {
                g[i][j] = s.charAt(j);
            }
        }   

        dp[0][0] = g[0][0] == '.' ? 1 : 0;

        for (int i = 1; i < n; ++i) {
            if (g[0][i] == '*') dp[0][i] = 0;
            else dp[0][i] = dp[0][i - 1];
            dp[0][i] %= mod;
        }

        for (int i = 1; i < n; ++i) {
            if (g[i][0] == '*') dp[i][0] = 0;
            else dp[i][0] = dp[i - 1][0];
            dp[i][0] %= mod;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                if (g[i][j] == '*') dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                dp[i][j] %= mod; 
            }
        }

        pn(dp[n - 1][n - 1]);
    }    
 
    public static void main(String[] args)throws Exception {
        if(memory) new Thread(null, new Runnable() {public void run(){try{new CSES1638().run();}catch(Exception e){e.printStackTrace();System.exit(1);}}}, "", 1 << 28).start();
        else new CSES1638().run();
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