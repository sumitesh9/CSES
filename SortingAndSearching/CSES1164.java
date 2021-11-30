package SortingAndSearching;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
 
public class CSES1164 {
    static PrintWriter pw;
    
    // Solution - https://usaco.guide/problems/cses-1164-room-allocation/solution
    public static void main(String[] args) {
        pw = new PrintWriter(System.out);
        FastScanner f = new FastScanner();
 
        int n = f.ni();
        Customer[] customers = new Customer[n];
        for (int i = 0; i < n; i++) {
            int arrival = f.ni();
            int departure = f.ni();
            customers[i] = new Customer(arrival, departure, i);
        }
 
        Arrays.sort(customers, Comparator.comparingInt(c -> c.st));
 
        PriorityQueue<Room> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.en));
 
        int k = 0;
        int[] roomAllocations = new int[n];
        int lastRoom = 1;
        pq.add(new Room(customers[0].en, lastRoom));
        roomAllocations[customers[0].idx] = lastRoom;
 
        for (int i = 1; i < n; ++i) {
            Room min = pq.peek();
 
            if (min.en < customers[i].st) {
                pq.remove();
                pq.add(new Room(customers[i].en, min.idx));
                roomAllocations[customers[i].idx] = min.idx;
            } else {
                pq.add(new Room(customers[i].en, ++lastRoom));
                roomAllocations[customers[i].idx] = lastRoom;
            }
            k = Math.max(k, pq.size());
        }
 
        pw.println(k);
        StringBuilder str = new StringBuilder();
        for (int allocation : roomAllocations) {
            str.append(allocation).append(" ");
        }
        pw.print(str);
        pw.close();
    }
 
    static class Customer {
        int st;
        int en;
        int idx;
 
        Customer(int st, int en, int idx) {
            this.st = st;
            this.en = en;
            this.idx = idx;
        }
    }
 
    static class Room {
        int en;
        int idx;
 
        Room(int en, int idx) {
            this.en = en;
            this.idx = idx;
        }
    }
 
    static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1 << 16];
        private int curChar;
        private int numChars;
 
        public FastScanner() {
            this(System.in);
        }
 
        public FastScanner(InputStream i) {
            stream = i;
        }
 
        private int nextByte() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars == -1)
                    return -1;
            }
            return buf[curChar++];
        }
 
        public String next() {
            int c;
            do {
                c = nextByte();
            } while (c <= ' ');
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = nextByte();
            } while (c > ' ');
            return res.toString();
        }
 
        public int ni() {
            int c;
            do {
                c = nextByte();
            } while (c <= ' ');
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = nextByte();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res = 10 * res + c - '0';
                c = nextByte();
            } while (c > ' ');
            return res * sgn;
        }
    }
}