package DSA.queue_adt;

/**
 * Queue ADT (Linear using Array)
 */
public class Queue<T> {
    private Object[] array;
    private int front, rear, capacity, count;

    public Queue(int size) {
        capacity = size;
        array = new Object[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }
        rear++;
        array[rear] = item;
        count++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return null;
        }
        T item = (T) array[front];
        // Shift (inefficient for linear, but demonstrates concept)
        // Alternatively, simple linear queue without shift moves front
        front++;
        count--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty())
            return null;
        return (T) array[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return rear == capacity - 1;
    } // Linear constraint

    public int size() {
        return count;
    }
}
