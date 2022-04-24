package SortingAndSearching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.TreeMap;

public class CSES1632 {
    /**
     * Problem can be solved using sorting movies in increasing order of end times
     * Now greedily assign movies to one of the k member or skip that movie if no one is available
     * We need to keep track of end time of every member so as to check if he is availabe for new 
     * assignment.
     * This can be done using sorted map (TreeMap) in Java. 
     */

    static class Event implements Comparable<Event> {
        int start;
        int end;
        public Event(int start , int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Event event) {
            if (this.end == event.end) return this.start - event.start;
            return this.end - event.end;
        }
    }

    public static void main(String[] args)throws IOException {
        _Scanner scanner = new _Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Event[] events = new Event[n];

        for (int i = 0; i < n; ++i) {
            events[i] = new Event(scanner.nextInt() , scanner.nextInt());
        }

        Arrays.sort(events);

        int ans = 0;
        TreeMap<Integer, Integer> endTimes = new TreeMap<>();
		endTimes.put(0, k);  // initialize all members at time 0

		for (Event event : events) {
			// find member who finished watching their assigned movies the
			// latest before movie.start
			Integer lower = endTimes.floorKey(event.start);

			// if such member exists, assign the member to the current movie
			if (lower != null) {
				++ans;
				int lowerValue = endTimes.get(lower);
				// remove the original time in which member finishes movie
				if (lowerValue == 1)
					endTimes.remove(lower);
				else
					endTimes.put(lower, lowerValue - 1);
				// member now finishes watching at time movie.end
				endTimes.put(event.end, endTimes.getOrDefault(event.end, 0) + 1);
			}
		}
		System.out.println(ans);
    }

    static class _Scanner {
        InputStream is;
        _Scanner(InputStream is) {
            this.is = is;
        }
        byte[] bb = new byte[1 << 15];
        int k, l;
        byte getc() throws IOException {
            if (k >= l) {
                k = 0;
                l = is.read(bb); //if (l < 0) return -1;
            }
            return bb[k++];
        }
        byte skip() throws IOException {
            byte b;
            while ((b = getc()) <= 32)
                ;
            return b;
        }
        int nextInt() throws IOException {
            int n = 0;
            for (byte b = skip(); b > 32; b = getc())
                n = n * 10 + b - '0';
            return n;
        }
    }
}
