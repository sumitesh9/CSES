import java.util.*;
import java.io.*;
 
public class CSES1193 {
 
    static BufferedReader br;
    static PrintWriter pw;
    static StringTokenizer st;
 
    static int ni() {
        return Integer.parseInt(st.nextToken());
    } 

    /*
        Traverse the grid using BFS technique to find the 
        shortest distance
        Using distance vectors(check implementation below) we can reduce the 'if' statements verbose
    */

    static int n , m , dx[] , dy[];
    static char par[][];
    static char dir[];
    static boolean vis[][];

    static class Pair {
        int x , y;
        public Pair(int x , int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int srcX , int srcY , int desX , int desY) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(srcX , srcY));
        while (q.size() > 0) {
            Pair p = q.poll();
            int currX = p.x;
            int currY = p.y;
            for (int i = 0; i < 4; ++i) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && !vis[newX][newY]) {
                    par[newX][newY] = dir[i];
                    vis[newX][newY] = true;
                    q.add(new Pair(newX , newY));
                }
            }
        }

        if (!vis[desX][desY]) {
            pw.println("NO");
        }

        else {
            pw.println("YES");
            StringBuffer sb = new StringBuffer("");
            int currx = desX;
            int curry = desY;
            while (!(currx == srcX && curry == srcY)) {
                sb.append(par[currx][curry]);
                if (par[currx][curry] == 'L') curry += 1;
                else if (par[currx][curry] == 'D') currx -= 1;
                else if (par[currx][curry] == 'U') currx += 1; 
                else curry -= 1;
            }
            pw.println(sb.length());
            pw.println(sb.reverse());
        }
    }
 
    public static void main(String args[]) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        st = new StringTokenizer(br.readLine());
 
        n = ni();
        m = ni();

        char g[][] = new char[n][m];
        par = new char[n][m];
        dx = new int[]{1 , 0 , -1 , 0};
        dy = new int[]{0 , 1 , 0 , -1};
        vis = new boolean[n][m];
        dir = new char[]{'D' , 'R' , 'U' , 'L'};
        int fromX = -1 , fromY = -1 , toX = -1 , toY = -1;

        for (int i = 0; i < n; ++i) {
            String x = br.readLine();
            for (int j = 0; j < m; ++j) {
                g[i][j] = x.charAt(j);
                if (g[i][j] == 'A') {
                    fromX = i;
                    fromY = j;
                }
                if (g[i][j] == 'B') {
                    toX = i;
                    toY = j;
                }
                if (g[i][j] == '#') vis[i][j] = true;
            }
        }
        
        bfs(fromX , fromY , toX , toY);

        
        pw.close();
        br.close();
    }   
}
