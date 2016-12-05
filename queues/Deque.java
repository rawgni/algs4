import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private int n;
    private Node head, tail;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {                          // construct an empty deque
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        n = 0;
    }

    public boolean isEmpty() {                // is the deque empty?
        return n == 0;
    }

    public int size() {                       // return the number of items on the deque
        return n;
    }

    public void addFirst(Item item) {         // add the item to the front
        if (item == null) throw new java.lang.NullPointerException();

        Node tmp = new Node();
        tmp.prev = head;
        tmp.item = item;
        tmp.next = head.next;
        head.next.prev = tmp;
        head.next = tmp;
        n++;
    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) throw new java.lang.NullPointerException();

        Node tmp = new Node();
        tmp.prev = tail.prev;
        tmp.item = item;
        tmp.next = tail;
        tail.prev.next = tmp;
        tail.prev = tmp;
        n++;
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Item item = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        n--;

        return item;
    }

    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Item item = tail.prev.item;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        n--;

        return item;
    }

    public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head.next;
        public boolean hasNext() { return current != tail; }
        public void remove() { throw new java.lang.UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
