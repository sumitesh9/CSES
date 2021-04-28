import java.util.*;
import java.io.*;
public class CSES1625 {
    static BufferedReader br;
    static PrintWriter pw;

    static String s;
    static int ans;
    static boolean vis[][];

    /*
        This is recursive bactracking problem which requires lot of pruning
        Refer to Page 52 of CP Handbook by Antti Laaksonen for problem solution
    */

    static boolean ok(int i , int j) {
        if (i == 7 && j == 1)
            return false;
        int cnt = 0;
        if (!vis[i - 1][j]) ++cnt;
        if (!vis[i + 1][j]) ++cnt;
        if (!vis[i][j - 1]) ++cnt;
        if (!vis[i][j + 1]) ++cnt;
        return cnt == 1;
    }

    static void recurse(int i , int j , int idx) {
        if (idx == 48 || i == 7 && j == 1) {
            if (idx == 48)
                ++ans;
            return;
        }

        boolean u = !vis[i - 1][j];
        boolean d = !vis[i + 1][j];
        boolean l = !vis[i][j - 1];
        boolean r = !vis[i][j + 1];
        if (u && d && !l && !r || !u && !d && l && r)
            return;
        vis[i][j] = true;
        char p = s.charAt(idx++);

        if (u && ok(i - 1, j)) {
            if (p == 'U' || p == '?')
                recurse(i - 1, j, idx);
            vis[i][j] = false;
            return;
        }

        if (d && ok(i + 1, j)) {
            if (p == 'D' || p == '?')
                recurse(i + 1, j, idx);
            vis[i][j] = false;
            return;
        }
        
        if (l && ok(i, j - 1)) {
            if (p == 'L' || p == '?')
                recurse(i, j - 1, idx);
            vis[i][j] = false;
            return;
        }
        
        if (r && ok(i, j + 1)) {
            if (p == 'R' || p == '?')
                recurse(i, j + 1, idx);
            vis[i][j] = false;
            return;
        }
        if (u && (p == 'U' || p == '?'))
            recurse(i - 1, j, idx);
        if (d && (p == 'D' || p == '?'))
            recurse(i + 1, j, idx);
        if (l && (p == 'L' || p == '?'))
            recurse(i, j - 1, idx);
        if (r && (p == 'R' || p == '?'))
            recurse(i, j + 1, idx);
        vis[i][j] = false;
    }


    public static void main(String args[])throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        s = br.readLine();
        vis = new boolean[9][9];

        for (int i = 0; i <= 8; i += 8)
            for (int j = 0; j <= 8; ++j)
                vis[i][j] = true;
                for (int j = 0; j <= 8; j += 8)
                    for (int i = 0; i <= 8; ++i)
                        vis[i][j] = true;

        ans = 0;
        recurse(1 , 1, 0);
        pw.println(ans);

        pw.close();
        br.close();
    }
}