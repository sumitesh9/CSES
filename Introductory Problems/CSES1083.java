import java.util.*;
import java.io.*;
public class CSES1083 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    static long nl() {
        return Integer.parseInt(st.nextToken());
    }

    static long n;

    /*
        Sum of first n natural numbers is (n * (n + 1)) / 2;
        Since only 1 number is missing we can find the difference b/w 
        the sums to get the answer
    */

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pw = new PrintWriter(System.out);

        long n = nl();
        long expectedSum = (n * (n + 1)) / 2;
        long actualSum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i + 1 < n; ++i) actualSum += nl();
        
        long ans = expectedSum - actualSum;
        pw.println(ans);
        pw.close();
        br.close();
    }
}