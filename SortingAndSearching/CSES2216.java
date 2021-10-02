import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CSES2216 {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    static int ni() {
        return Integer.parseInt(st.nextToken());
    }

    /*
        Store position of each number and simulate the process. 
    */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
        int n = ni();
 
        int[] pos = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) pos[ni() - 1] = i;
 
        int ans = 1;
        int curr = pos[0];
 
        for (int i = 1; i < n; ++i) {
            if (pos[i] > curr) curr = pos[i];
            else {
                ++ans;
                curr = pos[i];
            }
        }
 
        pw.println(ans);
        pw.close();
        br.close();
    }
}
