import java.util.*;
import java.io.*;
 
public class CSES1745 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
    static boolean dp[];
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    }
    
    /*
        DP[i] denotes whether i is possible to sum using given denominations

        Base case :
            DP[i] = true if i is present as a denomination
            DP[0] = true

        Recursive case :

            A sum = x can only be made if x is present as denomination or 

            x - d is possible to make for any d >= 0

            for all d in denominations
            DP[x] |= DP[d - x]
    */
 
 
    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
    
        int n = ni();
        int arr[] = new int[n];
        dp = new boolean[100001];
        dp[0] = true;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) arr[i] = ni();

        HashSet<Integer> h;
        for (int i = 0; i < n; ++i) {
            h = new HashSet<>();
            for (int j = 1; j < dp.length; ++j) {
                if (dp[j] && j + arr[i] < dp.length) h.add(j + arr[i]);
            }
            dp[arr[i]] = true;
            for (Integer x : h) dp[x] = true;
        }

        TreeSet<Integer> t = new TreeSet<>();
        int cnt = 0;

        for (int i = 1; i < dp.length; ++i) {
            if (dp[i]) {
                ++cnt;
                t.add(i);
            }
        }

        pw.println(cnt);
        for (Integer x : t) pw.print(x + " ");

        pw.close();
        br.close();
    }   
}