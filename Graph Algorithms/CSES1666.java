import java.util.*;
import java.io.*;
public class CSES1666 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    static int n , m;
    
    /*
        We can solve this problem using disjoint set union data structure
        Number of edges to be added is just the number of connected components - 1 ,
        as every edge between two unconnected components reduces no. of connected 
        components by exactly 1.
        So greedily merge two unconnected components using DSU.
    */

    static class DSU {
        private int id[] , sz[] , cc;

        public DSU(int n) {
            cc = n;
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; ++i) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        public int root(int a) {
            while (id[a] != a) {
                id[a] = id[id[a]];
                a = id[a];
            }
            return a;
        }

        public boolean unite(int a , int b) {
            int rA = root(a);
            int rB = root(b);
            if (rA == rB) return false;
            --cc;
            if (sz[rA] < sz[rB]) {
                id[rA] = id[rB];
                sz[rB] += sz[rA];
            }

            else {
                id[rB] = id[rA];
                sz[rA] += sz[rB];
            }
            return true;
        }
    }


    /*
        
    */

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);

        int n = ni();
        int m = ni();

        DSU d = new DSU(n);
        
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = ni();
            int v = ni();
            --u; --v;
            d.unite(u , v); 
        }

        int ans = d.cc - 1;

        pw.println(ans);
        
        for (int i = 0; i < n && ans > 0; ++i) {
            for (int j = i + 1; j < n && ans > 0; ++j) {
                if (d.unite(i , j)) {
                    --ans;
                    pw.println((i + 1) + " " + (j + 1));
                }
            }
        }


        pw.close();
        br.close();
    }
}