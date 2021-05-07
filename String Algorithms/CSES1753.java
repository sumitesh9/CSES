import java.util.*;
import java.io.*;

public class CSES1753 {
    static BufferedReader br;
    static PrintWriter pw;

    /*
        This problem can be solved by implementing Z algorithm
        Refer to Pg 248 of CP Handbook by Antti Laaksonen

        We basically need to construct Z array efficiently (O(n)) where
        Z[i] = length of longest substring starting from i which is also prefix

        Firstly we create a new string "pattern + "$" + input" and calculate Z array
        of this string efficiently in linear time
        After that number of occurences of pattern in input string will be just number of
        times pattern.length occurs in Z array. 
    */

    static class ZAlgo {
        private int Z[];
        private int len;
        private String s;

        public ZAlgo(String inp , String pattern) {
            this.len = pattern.length();
            this.s = pattern + "$" + inp; 
            this.Z = new int[s.length()];
        }

        public void generateZArray() {
            int x = 0, y = 0;
            for (int i = 1; i < Z.length; ++i) {
                Z[i] = Math.max(0 , Math.min(Z[i - x] , y - i + 1));
                while (i + Z[i] < Z.length && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                    x = i; 
                    y = i + Z[i];
                    ++Z[i];
                }
            }
        }

        public int search() {
            int ans = 0;
            for (int i = 0; i < Z.length; ++i) {
                if (Z[i] == len) ++ans;
            }
            return ans;
        }
    }

    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);

        String inp = br.readLine();
        String pattern = br.readLine();
        ZAlgo z = new ZAlgo(inp , pattern);
        
        z.generateZArray();

        int ans = z.search();
        pw.println(ans);
        
        pw.close();
        br.close();
    }
}