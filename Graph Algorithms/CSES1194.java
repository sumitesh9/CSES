import java.util.*;
import java.io.*;

public class CSES1194 {
    static PrintWriter pw;
    static boolean vis[][];
    static char g[][] , dir[] , p[][];
    static ArrayDeque<Integer> x , y;
    static int dMonster[][] , dSource[][] , dx[] , dy[] , sx , sy , n , m , oo;

    /*
        Do a multisource BFS on the grid with sources being all the monsters
        and then we will have all minimum distance dMonsters[][] , to reach any
        cell by any monster.

        Run another BFS with starting position 'A' and find minimum distance
        to every border of the grid. If any border cell has minimum distance
        dSource[i][j] < dMonster[i][j] , then we can reach (i , j) before any
        monster else if no (i , j) is found then answer is impossible. 
    */

        static void bfs_Monster() {
            while (x.size() > 0) {
                int u = x.poll();
                int v = y.poll();
                for (int i = 0; i < 4; ++i) {
                    int xx = u + dx[i];
                    int yy = v + dy[i];
                    if (xx >= 0 && xx < n && yy >= 0 && yy < m && !vis[xx][yy] && g[xx][yy] != '#') {
                        vis[xx][yy] = true;
                        dMonster[xx][yy] = dMonster[u][v] + 1;
                        x.add(xx);
                        y.add(yy);
                    }
                }
            }
        }

        static void bfs_Source() {
            dSource[sx][sy] = 0;
            x = new ArrayDeque<>();
            y = new ArrayDeque<>();
            x.add(sx);
            y.add(sy);
            vis = new boolean[n][m];
            vis[sx][sy] = true;

            while (x.size() > 0) {
                int u = x.poll();
                int v = y.poll();
                for (int i = 0; i < 4; ++i) {
                    int xx = u + dx[i];
                    int yy = v + dy[i];
                    if (xx >= 0 && xx < n && yy >= 0 && yy < m && !vis[xx][yy] && g[xx][yy] != '#') {
                        vis[xx][yy] = true;
                        dSource[xx][yy] = dSource[u][v] + 1;
                        p[xx][yy] = dir[i];
                        x.add(xx);
                        y.add(yy);
                    }
                }
            }
        }

        public static void main(String args[])throws Exception {
            FastScanner f = new FastScanner(System.in);
            pw = new PrintWriter(System.out);
            n = f.ni();
            m = f.ni();

            g = new char[n][m];    
            dMonster = new int[n][m];
            dSource = new int[n][m];    
            vis = new boolean[n][m];
            p = new char[n][m];
            x = new ArrayDeque<>();
            y = new ArrayDeque<>();
            dx = new int[]{1 , 0 , -1 , 0};
            dy = new int[]{0 , 1 , 0 , -1};
            dir = new char[]{'D' , 'R' , 'U' , 'L'};
            oo = 1000_000_000;
            
            sx = -1;
            sy = -1;
            for (int i = 0; i < n; ++i) {
                String s = f.next();
                for (int j = 0; j < m; ++j) {
                    g[i][j] = s.charAt(j);
                    dMonster[i][j] = oo;
                    dSource[i][j] = oo;
                    if (g[i][j] == 'M') {
                        x.add(i);
                        y.add(j);
                        vis[i][j] = true;
                        dMonster[i][j] = 0;
                    }
                    if (g[i][j] == 'A') {
                        sx = i;
                        sy = j;
                    }
                }
            }

            bfs_Monster();
            bfs_Source();

            // i == 0
            int toX = -1 , toY = -1;

            for (int j = 0; j < m; ++j) { 
                if (g[0][j] != '#' && dSource[0][j] < dMonster[0][j]) {
                    toX = 0;
                    toY = j;
                    break;
                }
            }

            if (toX == -1) {
                // i == n - 1
                for (int j = 0; j < m; ++j) { 
                    if (g[n - 1][j] != '#' && dSource[n - 1][j] < dMonster[n - 1][j]) {
                        toX = n - 1;
                        toY = j;
                        break;
                    }
                }
            }

            if (toX == -1) {
                // j == 0
                for (int i = 0; i < n; ++i) { 
                    if (g[i][0] != '#' && dSource[i][0] < dMonster[i][0]) {
                        toX = i;
                        toY = 0;
                        break;
                    }
                }
            }

            if (toX == -1) {
                // j == m - 1
                for (int i = 0; i < n; ++i) { 
                    if (g[i][m - 1] != '#' && dSource[i][m - 1] < dMonster[i][m - 1]) {
                        toX = i;
                        toY = m - 1;
                        break;
                    }
                }
            }

            if (toX == -1) {
                pw.println("NO");
            }

            else {
                pw.println("YES");
                pw.println(dSource[toX][toY]);
                int currx = toX , curry = toY;
                StringBuffer sb = new StringBuffer("");
                
                while (!(currx == sx && curry == sy)) {
                    sb.append(p[currx][curry]);
                    if (p[currx][curry] == 'L') curry += 1;
                    else if (p[currx][curry] == 'D') currx -= 1;
                    else if (p[currx][curry] == 'U') currx += 1; 
                    else curry -= 1;
                }

                for (int i = sb.length() - 1; i >= 0; --i) pw.print(sb.charAt(i));
            }
        pw.close();
    }

    public static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private FastScanner.SpaceCharFilter filter;

        public FastScanner(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }

                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int ni() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nl() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}