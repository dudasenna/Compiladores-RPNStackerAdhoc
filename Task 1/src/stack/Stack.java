package stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<E> {

    private LinkedList linkedList;

    public Stack() {
        linkedList = new LinkedList<E>();
    }

    public void push(E number) {
        linkedList.addFirst(number);
    }

    public E pop() throws NoSuchElementException {
        return (E) linkedList.removeFirst();
    }
}