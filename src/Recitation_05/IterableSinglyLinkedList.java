import java.util.Iterator;

@SuppressWarnings("unchecked")
public class IterableSinglyLinkedList_Sol<E>
        implements Cloneable, Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E e;
        private Node<E> next;
        public Node(E e, Node<E> n)    { this.e = e; this.next = n; }
        public E getElement()          { return e; }
        public Node<E> getNext()       { return next; }
        public void setNext(Node<E> n) { next = n; }
    }

    private class ListIterator implements Iterator<E> {
        private Node<E> cur;
        public ListIterator() {
            cur = head;
        }

        public boolean hasNext() {
            //TODO: implement hasNext
            return cur != null;
        }

        public E next() {
            //TODO: implement next
            E ret = cur.getElement();
            cur = cur.getNext();
            return ret;
        }
    }

    public Iterator<E> iterator() {
        //TODO: implement iterator
        return new ListIterator();
    }

    public IterableSinglyLinkedList_Sol<E> clone() throws CloneNotSupportedException {
        //TODO: implement clone

        //1. use Object.clone() to create the initial copy
        IterableSinglyLinkedList_Sol<E> that = (IterableSinglyLinkedList_Sol<E>)super.clone();

        //2. deep copy
        that.size = 0;
        that.head = that.tail = null;
        for(E e : this)
            that.addLast(e);

        return that;
    }

    public boolean equals(Object o) {
        if(o == null)                   //nothing equalst to null
            return false;
        if(getClass() != o.getClass())  //classes should be the same
            return false;
        IterableSinglyLinkedList_Sol<E> that = (IterableSinglyLinkedList_Sol<E>) o;
        if(size() != that.size())       //size should be the same
            return false;

        //TODO: finish implement equals using iterator
        //element-wise equivalence
        Iterator i = that.iterator();
        for(E e : this) {
            if(!e.equals(i.next()))
                return false;
        }
        return true;
    }

    public int size()   { return size; }
    public boolean isEmpty() { return size == 0; }
    public E first() {
        return isEmpty() ? null : head.getElement();
    }
    public E last() {
        return isEmpty() ? null : tail.getElement();
    }
    public void addFirst(E e) {
        head = new Node<E>(e, head);
        if(isEmpty())  //special handling for empty case
            tail = head;
        size++;
    }
    public void addLast(E e) {
        if(isEmpty())  //special handling for empty case
            addFirst(e);
        else {
            tail.setNext(new Node<E>(e, null));
            tail = tail.getNext();
            size++;
        }
    }
    public E removeFirst() {
        if(isEmpty())
            return null;
        E ret = head.getElement();
        head = head.getNext();
        size--;
        if(size == 0)
            tail = null;
        return ret;
    }
    public E removeLast() {
        Node<E> n = head;
        if(isEmpty())
            return null;
        else if(n.getNext() == null) {
            E ret = n.getElement();
            size = 0;
            head = tail = null;
            return ret;
        }
        else {
            while(n.getNext() != tail)
                n = n.getNext();
            E ret = tail.getElement();
            n.setNext(null);
            tail = n;
            size--;
            return ret;
        }
    }

    private static void onFalseThrow(boolean b) {
        if(!b)
            throw new RuntimeException("Error: unexpected");
    }
    public static void main(String[] args) {
        IterableSinglyLinkedList_Sol<Integer> list = new IterableSinglyLinkedList_Sol<Integer>();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addFirst(1);
        //test iterator
        int i = 1;
        for(int j : list)
            onFalseThrow(j == i++);
        try {
            //test clone
            IterableSinglyLinkedList_Sol<Integer> l2 = list.clone();
            //test equals
            onFalseThrow(list.equals(l2));
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();

        }
        onFalseThrow(list.removeLast() == 4);
        onFalseThrow(list.removeLast() == 3);
        onFalseThrow(list.removeFirst() == 1);
        onFalseThrow(list.removeLast() == 2);
        System.out.println("Success!");
    }
}