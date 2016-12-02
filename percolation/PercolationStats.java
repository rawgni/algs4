import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private int count;
    private double [] data;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        count = trials;
        data = new double[count];
        for (int i = 0; i < count; i++) {
            Percolation pr = new Percolation(n);
            int open = 0;

            while (!pr.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (!pr.isOpen(row, col)) {
                    pr.open(row, col);
                    open++;
                }
            }
            data[i] = open*1./(n*n);
        }
    }
    
    public double mean() {
        return StdStats.mean(data);
    }

    public double stddev() {
        return StdStats.stddev(data);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev())/Math.sqrt(count));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev())/Math.sqrt(count));
    }

    public static void main(String [] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() +
                ", " + ps.confidenceHi());
    }
}
