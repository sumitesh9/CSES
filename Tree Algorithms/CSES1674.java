import java.util.*;
import java.io.*;
 
public class CSES1674 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
 
 
    static ArrayList<Integer> adj[];
    static int subSize[];
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
 
    /* 
        @param u - Current node u
        @param p - Parent node of u
    */ 
 
    static void dfs(int u , int p) {
        subSize[u] = 1;
        for (Integer v : adj[u]) {
            if (v != p) {
                dfs(v , u);
                subSize[u] += subSize[v];
            }
        }
    }
 
    /*
        This problem asks you to find subtree size of every node
        and the number of subordinates will be exactly subtree size - 1
        since we are counting node itself in the subtree
 
        Subtree size can be found using DFS traversal of the tree 
        using adjacency list representation.
    */
 
    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
 
        int n = ni();
        subSize = new int[n];
 
        adj = new ArrayList[n];
        
        for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
 
        st = new StringTokenizer(br.readLine());
 
        for (int i = 1; i < n; ++i) {
            int par = ni();
            adj[i].add(--par);
            adj[par].add(i);
        }
 
        dfs(0 , -1);
 
        for (int i = 0; i < n; ++i) {
            pw.print((subSize[i] - 1) + " ");
        }
 
        pw.close();
        br.close();
    }   
}