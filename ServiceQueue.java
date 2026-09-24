public class ServiceQueue {

    private String[] queue;
    private int front;
    private int rear;
    private int nItems;
    private int maxSize;

    public ServiceQueue(int size) {

        maxSize = size;
        queue = new String[maxSize];

        front = 0;
        rear = -1;
        nItems = 0;
    }

    public boolean isEmpty() {

        return nItems == 0;
    }

    public boolean isFull() {

        return nItems == maxSize;
    }

    public void insert(String request) {

        if (isFull()) {

            System.out.println(
                    "Service request queue is full.");

            return;
        }

        if (rear == maxSize - 1) {
            rear = -1;
        }

        queue[++rear] = request;
        nItems++;

        System.out.println(
                "The Service request added successfully.");
    }

    public String remove() {

        if (isEmpty()) {
            return null;
        }

        String temp = queue[front++];

        if (front == maxSize) {
            front = 0;
        }

        nItems--;

        return temp;
    }

    public String peekFront() {

        if (isEmpty()) {
            return null;
        }

        return queue[front];
    }

    public void display() {

        if (isEmpty()) {

            System.out.println(
                    "No service requests.");

            return;
        }

        System.out.println(
                "\n===== SERVICE REQUESTS =====");

        int index = front;

        for (int i = 0; i < nItems; i++) {

            System.out.println(
                    (i + 1) + ". " +
                            queue[index]);

            index++;

            if (index == maxSize) {
                index = 0;
            }
        }
    }
}