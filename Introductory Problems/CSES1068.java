import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.text.*;

public class CSES1068 {
    static long mod = 1000_000_007;
    static long mod1 = 998244353;
    static boolean fileIO = false;
    static boolean memory = true;
    static FastScanner f;
    static PrintWriter pw;
    static double eps = (double)1e-6;
    static int oo = Integer.MAX_VALUE;

    public static void solve() throws Exception {
        long n = f.nl();

        // Simulate the process until n reaches 1

        while (n != 1) {
            p(n + " ");
            if (n % 2 == 1) {
                n *= 3l;
                ++n;
            }
            else n /= 2l;
        }
        p(1 + " ");
    }    
 
    public static void main(String[] args)throws Exception {
        if(memory) new Thread(null, new Runnable() {public void run(){try{new CSES1068().run();}catch(Exception e){e.printStackTrace();System.exit(1);}}}, "", 1 << 28).start();
        else new CSES1068().run();
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