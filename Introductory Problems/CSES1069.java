import java.util.*;
import java.io.*;

public class CSES1069 {
    static PrintWriter pw;
    static BufferedReader br;

    /*
        Implement a sliding window where window size is extended to 
        right if it matches previous character else new window of size
        1 is started from that character

        Answer is maximum window size at any instant
    */

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        String x = br.readLine();

        int ans = 1;
        int curr = 1;
        char p = x.charAt(0);

        for (int i = 1; i < x.length(); ++i) {
            if (x.charAt(i) == p) ++curr;
            else {
                ans = Math.max(ans , curr);
                p = x.charAt(i);
                curr = 1;
            }
        }

        ans = Math.max(ans , curr);

        pw.println(ans);
        br.close();
        pw.close();
    }
}