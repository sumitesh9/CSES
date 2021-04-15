import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.text.*;

public class CSES1192 {
    static long mod = 1000_000_007;
    static long mod1 = 998244353;
    static boolean fileIO = false;
    static boolean memory = true;
    static FastScanner f;
    static PrintWriter pw;
    static double eps = (double)1e-6;
    static long oo = (long)1e18;

    static int n , m , ans;
    static boolean vis[][];
    static char g[][];

    static void dfs(int i , int j) {
        vis[i][j] = true;

       	// Check four adjacent cells

        if (i - 1 >= 0 && g[i - 1][j] == '.' && !vis[i - 1][j]) dfs(i - 1 , j);
        if (i + 1 < n && g[i + 1][j] == '.' && !vis[i + 1][j]) dfs(i + 1 , j);
        if (j + 1 < m && g[i][j + 1] == '.' && !vis[i][j + 1]) dfs(i , j + 1);
        if (j - 1 >= 0 && g[i][j - 1] == '.' && !vis[i][j - 1]) dfs(i , j - 1);
    }

    public static void solve() throws Exception {
        
        // Answer is just the number of connected components in the grid
        // DFS or BFS works just fine.
        // This is simple DFS approach
        
        // Four if statement verbose in DFS can be reduced by using direction vectors dx[] , dy[]

        n = f.ni();
        m = f.ni();

        vis = new boolean[n][m];
        g = new char[n][m];

        for (int i = 0; i < n; ++i) {
            String x = f.next();
            for (int j = 0; j < m; ++j) {
                g[i][j] = x.charAt(j);
            }
        }

        ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (!vis[i][j] && g[i][j] == '.') {
                    ++ans;
                    dfs(i , j);
                }
            }
        }

        pn(ans);
    }    
 
    public static void main(String[] args)throws Exception {
        if(memory) new Thread(null, new Runnable() {public void run(){try{new CSES1192().run();}catch(Exception e){e.printStackTrace();System.exit(1);}}}, "", 1 << 28).start();
        else new CSES1192().run();
    }
        
/******************************END OF MAIN PROGRAM*******************************************/
    void run()throws Exception {
        f = new FastScanner(System.in);
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
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private FastScanner.SpaceCharFilter filter;
 
        public FastScanner(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
 
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
 
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
 
        public int ni() {
            int c = read();
 
            while (isSpaceChar(c))
                c = read();
 
            int sgn = 1;
 
            if (c == '-') {
                sgn = -1;
                c = read();
            }
 
            int res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
 
            return res * sgn;
        }
 
        public long nl() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
 
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));
            return res.toString();
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
 
    public static void pn(Object... o) {for(int i = 0; i < o.length; ++i) pw.print(o[i] + (i + 1 < o.length ? " ": "\n"));}
    public static void p(Object... o) {for(int i = 0; i < o.length; ++i) pw.print(o[i] + (i + 1 < o.length ? " " : ""));}
    public static void pni(Object... o) {for(Object obj : o) pw.print(obj + " "); pw.println(); pw.flush();}
}