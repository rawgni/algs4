import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String [] args) {
        String [] words = StdIn.readAllStrings();

        StdRandom.shuffle(words);
        
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(words[i]);
        }
    }
}
