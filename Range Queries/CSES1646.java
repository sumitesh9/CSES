import java.util.*;
import java.io.*;

public class CSES1646 {

    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String args[]) throws Exception {
        
        /* Notice that array is static (without update queries)
           Hence maintaing prefix sum array for answering queries is enough

           prefixSum[i] = sum of arr[0....i] (0 and i are both inclusive)

           So for subarray sum arr[l....r] = arr[r] - arr[l - 1] (By inclusion exclusion principle) 
        */

        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());

        int n = ni();
        int q = ni();
        
        int a[] = new int[n];
        long prefixSum[] = new long[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) {
            a[i] = ni();
            prefixSum[i] = i == 0 ? a[i] : prefixSum[i - 1] + a[i];
        }

        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());
            int l = ni();
            int r = ni();
            --l; --r;
            long ans = prefixSum[r];
            ans -= l == 0 ? 0 : prefixSum[l - 1];
            pw.println(ans);
        }

        pw.close();
        br.close();
    }   
}