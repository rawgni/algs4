import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String [] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        for (String w: StdIn.readAllStrings()) {
            rq.enqueue(w);
        }

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
