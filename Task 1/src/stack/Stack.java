package stack;

import java.util.LinkedList;

public class Stack<E> {

    private LinkedList linkedList;

    public Stack() {
        linkedList = new LinkedList<E>();
    }

    public void push(E number) {
        linkedList.addFirst(number);
    }

    public E pop() {
        return (E) linkedList.removeFirst();
    }
}