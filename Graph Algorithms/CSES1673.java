import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.text.*;

public class CSES1673 {
    static long mod = 1000_000_007;
    static long mod1 = 998244353;
    static boolean fileIO = false;
    static boolean memory = true;
    static FastScanner f;
    static PrintWriter pw;
    static double eps = (double)1e-6;
    static long oo = (long)1e18;

    static int n , m , from[] , to[] , w[];
    static long d[];
    static ArrayList<Integer> adj[];
    static boolean vis[];

    static void dfs(int u) {
        vis[u] = true;
        for (Integer v : adj[u]) {
            if (!vis[v]) dfs(v);
        }
    }

    static boolean relax() {
        boolean relaxed = false;

        for (int i = 0; i < m; ++i) {
            if (vis[from[i]] && vis[to[i]] && d[from[i]] != oo && d[to[i]] > d[from[i]] + w[i]) {
                relaxed = true;
                d[to[i]] = d[from[i]] + w[i];
            } 
        }

        return relaxed;
    }

    public static void solve() throws Exception {
        
        // We can implement Bellman Ford on reversed edge weights to find the maximum
        // If there is successful relaxation for the nth time then we have infinite score otherwise score
        // is finite and given by -d[n - 1]

        // We need to run a DFS to consider only those nodes in bellman ford
        // algorithm which are part of same connected component.

        n = f.ni();
        m = f.ni();

        from = new int[m];
        to = new int[m];
        w = new int[m];
        d = new long[n];
        vis = new boolean[n];
        adj = new ArrayList[n];

        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; ++i) {
            int u = f.ni(); int v = f.ni(); int wt = f.ni();
            adj[--v].add(--u);
            from[i] = u;
            to[i] = v;
            w[i] = -wt; 
        }

        dfs(n - 1);
        Arrays.fill(d , oo);
        d[0] = 0;

        for (int i = 0; i + 1 < n; ++i) {
            relax();
        }

        if (relax()) {
            p(-1);
            return;
        }

        p(-d[n - 1]);
    }    
 
    public static void main(String[] args)throws Exception {
        if(memory) new Thread(null, new Runnable() {public void run(){try{new CSES1673().run();}catch(Exception e){e.printStackTrace();System.exit(1);}}}, "", 1 << 28).start();
        else new CSES1673().run();
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