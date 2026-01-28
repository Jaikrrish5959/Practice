package DSA.stack_adt;

/**
 * Stack ADT
 */
public class Stack<T> {
    private Object[] array;
    private int top;
    private int capacity;

    public Stack(int size) {
        capacity = size;
        array = new Object[capacity];
        top = -1;
    }

    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        array[++top] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return null;
        }
        return (T) array[top--];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty())
            return null;
        return (T) array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }
}
