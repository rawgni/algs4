import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private final boolean [][] grid;
    private final int size;
    private final WeightedQuickUnionUF uf;
    private final int bottom;

    public Percolation(int n) {
        if (n < 1) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[n+1][n+1];
        size = n;
        bottom = n*n+1;
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    private void valid(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    private int xyTo1D(int row, int col) {
        return (row-1)*size+col;
    }

    public void open(int row, int col) {
        valid(row, col);
        grid[row][col] = true;
        int tmp;
        if (row == 1) uf.union(0, xyTo1D(row, col));
        if (row == size) uf.union(bottom, xyTo1D(row, col));
        // check top
        if (row > 1 && grid[row-1][col]) {
            uf.union(xyTo1D(row, col), xyTo1D(row-1, col));
        }
        // check bottom
        if (row < size && grid[row+1][col]) {
            uf.union(xyTo1D(row, col), xyTo1D(row+1, col));
        }
        // check left
        if (col > 1 && grid[row][col-1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col-1));
        }
        // check right
        if (col < size && grid[row][col+1]) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col+1));
        }
    }

    public boolean isOpen(int row, int col) {
        valid(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        valid(row, col);
        return uf.connected(0, xyTo1D(row, col));
    }

    public boolean percolates() {
        return uf.connected(0, bottom);
    }

    public static void main(String [] args) {
        Percolation p = new Percolation(10);

        p.open(10, 2);
        p.open(2, 10);
        p.open(6, 8);
        p.open(2, 6);
        p.open(1, 4);
        p.open(8, 4);
        p.open(10, 1);
        p.open(4, 2);
        p.open(4, 8);
        p.open(9, 3);
        p.open(2, 2);
        p.open(9, 1);
        p.open(4, 3);
        p.open(5, 5);
        p.open(5, 7);
        p.open(2, 8);
        p.open(6, 4);
        p.open(7, 5);
        p.open(9, 6);
        p.open(3, 7);
        p.open(4, 7);
        p.open(7, 1);
        p.open(9, 4);
        p.open(3, 10);
        p.open(1, 10);
        p.open(10, 10);
        p.open(9, 7);
        p.open(1, 5);
        p.open(9, 8);
        p.open(6, 1);
        p.open(2, 5);
        p.open(3, 4);
        p.open(6, 9);
        p.open(5, 8);
        p.open(3, 2);
        p.open(4, 6);
        p.open(1, 7);
        p.open(7, 9);
        p.open(3, 9);
        p.open(4, 4);
        p.open(4, 10);
        p.open(3, 5);
        p.open(3, 8);
        p.open(1, 8);
        p.open(3, 1);
        p.open(6, 7);
        p.open(2, 3);
        p.open(7, 4);
        p.open(9, 10);
        p.open(7, 6);
        p.open(5, 2);
        p.open(8, 3);
        p.open(10, 8);
        p.open(7, 10);
        p.open(4, 5);
        p.open(8, 10);
    }
}
