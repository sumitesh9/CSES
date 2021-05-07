import java.util.*;
import java.io.*;
public class CSES1731 {
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
    static int dp[];
    static int mod;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /*
        Checking for all possible partitions leads to TLE

        Optimised version of solving this problem using advanced data structures like 
        Trie or suffix array

        Using Trie: - 
        First insert all the words present in dictionary into trie , now lets
        suppose dp[i] = Number of ways of making suffix starting at ith position of input
        string
    
        Base case :
            dp[s.length] = 1 (One way of making null string)

        Recursive case :
            Iterate from right to left
            Initially answer = 0
            For every suffix while searching it in trie every time we encounter
            a leaf node we have found new way of making that particular suffix
            and dp[leafPos + 1] is number of ways to make that suffix so keep
            adding all dp[leafPos + 1] to answer variable. 

            Finally dp[0] will be our answer.
    */
    
    static class Trie {
        private int sz;
        private int t[][];
        private int leafs;
        private boolean isLeaf[];

        public Trie(int sz) {
            this.sz = sz;
            t = new int[sz][26];
            leafs = 0;
            isLeaf = new boolean[sz];
        }

        public void insert(String s) {
            int node = 0; // Begin at root node($)
            for (int i = 0; i < s.length(); ++i) {
                if (t[node][s.charAt(i) - 'a'] == 0) t[node][s.charAt(i) - 'a'] = ++leafs;
                node = t[node][s.charAt(i) - 'a'];
            }
            isLeaf[node] = true;
        }

        public int search(int idx , String s) {
            int node = 0 , ans = 0;
            
            for (int i = idx; i < s.length(); ++i) {
                if (t[node][s.charAt(i) - 'a'] == 0) return ans;
                node = t[node][s.charAt(i) - 'a'];
                if (isLeaf[node]) {
                    ans += dp[i + 1];
                    ans %= mod;
                }
            }

            return ans;
        }
    }

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();

        st = new StringTokenizer(br.readLine());
        int k = ni();
        mod = 1000_000_007;

        Trie t = new Trie(1000_000);

        dp = new int[s.length() + 1];

        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            t.insert(st.nextToken());
        }

        dp[s.length()] = 1;

        for (int i = s.length() - 1; i >= 0; --i) dp[i] = t.search(i , s);

        pw.println(dp[0]);

        pw.close();
        br.close();
    }
}