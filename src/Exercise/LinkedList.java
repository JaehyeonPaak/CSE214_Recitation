package Exercise;

public class LinkedList {
    //interface Ordered
    public static interface Ordered {
        public boolean ge(Ordered a);   //greater than or equal to

        //default methods
        public default boolean gt(Ordered a) {  //greater than
            return this.ge(a) && this.ne(a);
        }
        public default boolean le(Ordered a) {  //less than or equal to
            return a.ge(this);
        }
        public default boolean lt(Ordered a) {  //less than
            return a.ge(this) && a.ne(this);
        }
        public default boolean eq(Ordered a) {  //equal
            return this.ge(a) && a.ge(this);
        }
        public default boolean ne(Ordered a) {  //not equal
            return !this.eq(a);
        }
    }

    //abstract class AbsList
    public static abstract class AbsList<E extends Ordered> {

        //inner interface...
        protected static interface Node<E extends Ordered> {
            E getElement();
            void setElement(E e);
        }

        //sentinels
        Node<E> head, tail;

        public AbsList() {
            head = makeNode(null);
            tail = makeNode(null);
            initList(head, tail);
        }

        //add e to the first position
        public void addFirst(E e) {
            //TODO: implement addFirst using addAfter
            addAfter(makeNode(e), head);
        }

        //add e to the last position
        public void addLast(E e) {
            //TODO: implement addLast using addAfter and getPrev
            addAfter(makeNode(e), getPrev(tail));
        }

        //find the minimum node
        protected Node<E> findMin(Node<E> from) {
            //TODO: implement findMin
            Node<E> min = from;

            Node<E> p = getNext(min);
            while(p != tail) {
                if(min.getElement().ge(p.getElement())) {
                    min = p;
                }
                p = getNext(p);
            }
            return min;
        }

        //selection sort
        public void selSort() {
            //TODO: implement selSort using findMin
            for(Node<E> pos = getNext(head); pos != tail; pos = getNext(pos)) {
                Node<E> min = findMin(pos);
                swap(pos, min);
            }
        }

        //insertion sort (backward: insert from the back to the front)
        public void insSort() {
            //TODO: implement insSort
            for(Node<E> pos1 = getNext(getNext(head)); pos1 != tail; pos1 = getNext(pos1)) {
                for(Node<E> pos = getNext(getNext(head)); pos != tail; pos = getNext(pos)) {
                    for(Node<E> tmp = pos; tmp != head && pos.getElement().le(tmp.getElement()); tmp = getPrev(tmp)) {
                        swap(pos, tmp);
                    }
                }
            }

        }

        //print the list
        public void print() {
            for(Node<E> pos = getNext(head); pos != tail; pos = getNext(pos)) {
                System.out.print(pos.getElement() + " ");
            }
            System.out.println();
        }

        //swap the elements of the two nodes
        protected void swap(Node<E> a, Node<E> b) {
            E tmp = a.getElement();
            a.setElement(b.getElement());
            b.setElement(tmp);
        }

        //abstract methods
        protected abstract Node<E> makeNode(E e);
        protected abstract void initList(Node<E> head, Node<E> tail);
        protected abstract void addAfter(Node<E> node, Node<E> pos);
        protected abstract Node<E> getNext(Node<E> pos);
        protected abstract Node<E> getPrev(Node<E> pos);
    }

    //singly linked list
    public static class SglList<E extends Ordered> extends AbsList<E> {

        protected static class SglNode<E extends Ordered> implements Node<E> {
            E e;
            SglNode<E> next;

            SglNode(E e, SglNode<E> next) { this.e = e; this.next = next; }
            public E getElement()         { return e; }
            public void setElement(E e)   { this.e = e; }
        }

        //TODO: implement all abstract methods of AbsList
        @Override
        protected Node<E> makeNode(E e) {
            return new SglNode<>(e, null);
        }

        @Override
        protected void initList(Node<E> head, Node<E> tail) {
            SglNode<E> h = (SglNode<E>) head;
            SglNode<E> t = (SglNode<E>) tail;
            h.next = t;
        }

        @Override
        protected void addAfter(Node<E> node, Node<E> pos) {
            SglNode<E> n = (SglNode<E>) node;
            SglNode<E> p = (SglNode<E>) pos;
            n.next = p.next;
            p.next = n;
        }

        @Override
        protected Node<E> getNext(Node<E> pos) {
            SglNode<E> p = (SglNode<E>) pos;
            return p.next;
        }

        @Override
        protected Node<E> getPrev(Node<E> pos) {
            SglNode<E> h = (SglNode<E>) head;
            while(h.next != pos) {
                h = h.next;
            }
            return h;
        }
    }

    //doubly linked list
    public static class DblList<E extends Ordered> extends AbsList<E> {

        //inner class...
        protected static class DblNode<E extends Ordered> implements Node<E> {
            E e;
            DblNode<E> prev;
            DblNode<E> next;

            DblNode(E e, DblNode<E> prev, DblNode<E> next) {
                this.e = e;
                this.prev = prev;
                this.next = next;
            }

            public E getElement()       { return e; }
            public void setElement(E e) { this.e = e; }
        }

        //TODO: implement all abstract methods of AbsList
        @Override
        protected Node<E> makeNode(E e) {
            return new DblNode<>(e, null, null);
        }

        @Override
        protected void initList(Node<E> head, Node<E> tail) {
            DblNode<E> h = (DblNode<E>) head;
            DblNode<E> t = (DblNode<E>) tail;
            t.prev = h;
            h.next = t;
        }

        @Override
        protected void addAfter(Node<E> node, Node<E> pos) {
            DblNode<E> n = (DblNode<E>) node;
            DblNode<E> p = (DblNode<E>) pos;
            n.next = p.next;
            n.prev = p;
            n.next.prev = n;
            p.next = n;
        }

        @Override
        protected Node<E> getNext(Node<E> pos) {
            DblNode<E> p = (DblNode<E>) pos;
            return p.next;
        }

        @Override
        protected Node<E> getPrev(Node<E> pos) {
            DblNode<E> p = (DblNode<E>) pos;
            return p.prev;
        }
    }

    public static class Int implements Ordered {
        int n;
        public Int(int n)            { this.n = n; }
        public boolean ge(Ordered a) { return n >= ((Int)a).n; }
        public String toString()     { return "" + n; }
    }

    public static void test(String msg, AbsList<Int> list, boolean selSort) {
        System.out.println(msg);
        System.out.println("test add...");
        list.addFirst(new Int(1));
        list.addLast( new Int(2));
        list.addFirst(new Int(3));
        list.addLast( new Int(4));
        list.addFirst(new Int(5));
        list.addLast( new Int(6));
        list.addFirst(new Int(7));
        list.addLast( new Int(8));
        list.print();

        System.out.println("test sort...");
        if(selSort)
            list.selSort();
        else
            list.insSort();
        list.print();

        System.out.println("test done...");
    }

    public static void main(String[] args) {
        /* Expected output
            SglList
            test add...
            7 5 3 1 2 4 6 8
            test sort...
            1 2 3 4 5 6 7 8
            test done...
            DblList
            test add...
            7 5 3 1 2 4 6 8
            test sort...
            1 2 3 4 5 6 7 8
            test done...
        */
        test("SglList", new SglList<Int>(), true);
        test("DblList", new DblList<Int>(), false);
    }
}