import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] a;
    private int n;

    public RandomizedQueue() {                // construct an empty randomized queue
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {                // is the queue empty?
        return n == 0;
    }

    public int size() {                       // return the number of items on the queue
        return n;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(Item item) {          // add the item
        if (item == null) throw new java.lang.NullPointerException();
        if (n == a.length) resize(2*a.length);
        a[n++] = item;
    }

    public Item dequeue() {                   // remove and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int t = StdRandom.uniform(n);
        Item item = a[t];
        a[t] = a[n-1];
        a[n-1] = null;
        n--;
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    public Item sample() {                    // return (but do not remove) a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int t = StdRandom.uniform(n);
        return a[t];
    }

    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] ta;
        public ReverseArrayIterator() {
            ta = (Item[]) new Object[n];
            for (i = 0; i < n; i++) {
                ta[i] = a[i];
            }
            i--;
            StdRandom.shuffle(ta);
        }
        public boolean hasNext() { return i >= 0; }
        public void remove() { throw new java.lang.UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return ta[i--];
        }
    }
}
