package DSA.queue_adt;

/**
 * Circular Queue ADT
 */
public class CircularQueue<T> {
    private Object[] array;
    private int front, rear, capacity, count;

    public CircularQueue(int size) {
        capacity = size;
        array = new Object[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void enqueue(T item) {
        if (isFull())
            return;

        rear = (rear + 1) % capacity;
        array[rear] = item;
        count++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty())
            return null;

        T item = (T) array[front];
        front = (front + 1) % capacity;
        count--;
        return item;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }
}
