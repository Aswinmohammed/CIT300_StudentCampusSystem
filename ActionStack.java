public class ActionStack {

    private String[] stack;
    private int top;
    private int maxSize;


    
    public ActionStack(int size) {

        maxSize = size;
        stack = new String[maxSize];
        top = -1;
    }

    public boolean isEmpty() {

        return top == -1;
    }

    public boolean isFull() {

        return top == maxSize - 1;
    }

    public void push(String action) {

        if (isFull()) {

            // Shift older actions left
            for (int i = 0; i < maxSize - 1; i++) {
                stack[i] = stack[i + 1];
            }

            stack[maxSize - 1] = action;

        } else {

            stack[++top] = action;
        }
    }

    public String pop() {

        if (isEmpty()) {
            return null;
        }

        return stack[top--];
    }

    public String peek() {

        if (isEmpty()) {
            return null;
        }

        return stack[top];
    }

    public void display() {

        if (isEmpty()) {

            System.out.println(
                    "No recent actions.");

            return;
        }

        System.out.println(
                "\n===== RECENT ACTIONS =====");

        for (int i = top; i >= 0; i--) {

            System.out.println(
                    (top - i + 1) + ". " +
                            stack[i]);
        }
    }
}