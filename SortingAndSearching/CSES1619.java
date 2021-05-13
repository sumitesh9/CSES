import java.util.*;
import java.io.*;

public class CSES1619 {
    static PrintWriter pw;

    /*
        Use Scan Line algorithm :
        For every arrival or departure event in chronological order(sorted by time) , just
        update current customers that are present , +1 for arrival and
        -1 for departure , answer will be maximum value of current customer
        at any instant.
    */

    static class Event implements Comparable<Event> {
        int time;
        boolean arrival;

        public Event(int time , boolean arrival) {
            this.time = time;
            this.arrival = arrival;
        }

        public int compareTo(Event e) {
            return this.time - e.time;
        }
    }

    public static void main(String args[])throws Exception {
        FastScanner f = new FastScanner(System.in);
        pw = new PrintWriter(System.out);
        
        int n = f.ni();

        Event event[] = new Event[2 * n];

        for (int i = 0; i < n; ++i) {
            event[2 * i] = new Event(f.ni() , true);
            event[(2 * i) + 1] = new Event(f.ni() , false);
        }
        
        Arrays.sort(event);

        int ans = 0 , curr = 0;
        
        for (Event e : event) {
            if (e.arrival) ++curr;
            else --curr;
            ans = Math.max(ans , curr);
        }
        
        pw.println(ans);
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