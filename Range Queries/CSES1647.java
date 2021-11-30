import java.util.*;
import java.io.*;
public class CSES1647 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
 
    static int n , q , a[] , log[] , maxN , spt[][] , oo;
 
    /*  
        Static range minimum queries can be solved efficiently using sparse table
        spt[i][j] = Minimum of the range [i , i + (1 << j) - 1]

        let k = maximum power of 2 that doesn't exceed (j - i + 1)
        min[i , j] = min(spt[i][k] , spt[j - k + 1][j]);
    */
 
    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);
 
        n = ni(); q = ni();
        maxN = (int)2e5 + 2;
        log = new int[maxN + 1];
        spt = new int[maxN][26];
        oo = Integer.MAX_VALUE;
        log[1] = 0;
        for (int i = 2; i <= maxN; ++i) log[i] = log[i / 2] + 1;

        
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) a[i] = ni();

        for (int i = 0; i < maxN; ++i) {
            for (int j = 0; j < 26; ++j) {
                spt[i][j] = oo;
            }
        }
        
        for (int i = 0; i < n; ++i) spt[i][0] = a[i];
        for (int j = 1; j < 26; ++j) {
            for (int i = 0; i + (1 << j) <= n; ++i) {
                spt[i][j] = Math.min(spt[i][j - 1] , spt[i + (1 << (j - 1))][j - 1]);
            }
        }

        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());
            int l = ni() - 1;
            int r = ni() - 1;
            int j = log[r - l + 1];
            int min = Math.min(spt[l][j] , spt[r - (1 << j) + 1][j]);
            pw.println(min);
        }
        
        pw.close();
        br.close();
    }
}