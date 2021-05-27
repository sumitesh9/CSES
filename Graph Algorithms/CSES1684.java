import java.util.*;
import java.io.*;

public class CSES1684 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    static ArrayList<Integer> g[] , gT[];
    static int cc[] , cnt , topOrder[] , n , m;

    /*
        Reference - 1) CP Handbook by Antti Laaksonen.
                    2) cp algorithms 
                       https://cp-algorithms.com/graph/2SAT.html
        
        This problem can be solved using 2-SAT.
        Algorithm :

        Firstly a∨b is equivalent to ¬a⇒b∧¬b⇒a

        Construct implication graph by adding two edges
        for all a∨b we have first edge !a -> b and second from
        !b -> a

        In order for this 2-SAT problem to have a solution, 
        it is necessary and sufficient that for any variable x the vertices x and !x are in
        different SCC.

        Proof -> (By contradiction)
        Let suppose x and !x are in same SCC , then it means we are assuming both x and !x
        to be true which is impossible.

        Constructive solution:

        1) Firstly we find all SCC of implication graph using Kosaraju algorithm.
        2) Vertex with index (2 * x) and ((2 * x) + 1) correspond to variable x and !x respectively. 
        3) Check scc[x] and scc[!x] are different for all valid x.
        4) Let us sort the SCC in topological order(i.e. scc[v] ≤ scc[u] if there is a path from v to u) 
        and let scc[v] denote the index of strongly connected component to which the vertex v belongs.
        Then, if scc[x] < scc[!x] we assign x with false and true otherwise. 
    */

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    static void dfs1(int u) {
        cc[u] = 1;
        for (Integer v : g[u]) {
            if (cc[v] == 0) dfs1(v);
        }
        topOrder[cnt++] = u;
    }

    static void dfs2(int u , int c) {
        cc[u] = c;
        for (Integer v : gT[u]) {
            if (cc[v] == 0) dfs2(v , c);
        }
    }

        public static void main(String args[])throws Exception {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer(br.readLine());
            pw = new PrintWriter(System.out);
            
            m = ni();
            n = ni();
            g = new ArrayList[2 * n];
            gT = new ArrayList[2 * n];
            topOrder = new int[2 * n];
            cc = new int[2 * n];
            cnt = 0;

            for (int i = 0; i < 2 * n; ++i) {
                g[i] = new ArrayList<>();
                gT[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; ++i) {
                st = new StringTokenizer(br.readLine());
                boolean firstIncluded = st.nextToken().charAt(0) == '+';
                int u = ni();

                boolean secondIncluded = st.nextToken().charAt(0) == '+';
                int v = ni();

                --u; --v;
                u *= 2;
                v *= 2;

                int u1 = u + (firstIncluded ? 1 : 0);
                int u2 = u + (firstIncluded ? 0 : 1);
                int v1 = v + (secondIncluded ? 1 : 0);
                int v2 = v + (secondIncluded ? 0 : 1);

                g[u2].add(v1);
                g[v2].add(u1);
                gT[v1].add(u2);
                gT[u1].add(v2);
            }

            for (int i = 0; i < 2 * n; ++i) {
                if (cc[i] == 0) {
                    dfs1(i);
                }
            }

            Arrays.fill(cc , 0);
            int c = 0;
            for (int i = 2 * n - 1; i >= 0; --i) {
                if (cc[topOrder[i]] == 0) {
                    dfs2(topOrder[i] , ++c);
                }
            }
            
            StringBuilder ans = new StringBuilder("");
            boolean ok = true;

            for (int i = 0; i < 2 * n; i += 2) {
                if (cc[i] == cc[i + 1]) {
                    ok = false;
                    break;
                }
                ans.append(cc[i] < cc[i + 1] ? "+ " : "- ");
            }

            pw.print(ok ? ans : "IMPOSSIBLE");
            pw.close();
        }
    }