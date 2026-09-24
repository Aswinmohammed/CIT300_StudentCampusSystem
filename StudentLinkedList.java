public class StudentLinkedList {

    private class Node {

        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private Node head;

    public StudentLinkedList() {
        head = null;
    }

    // Add student
    public boolean add(Student student) {

        if (search(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
        } else {

            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        return true;
    }

    // Search student
    public Student search(String id) {

        Node current = head;

        while (current != null) {

            if (current.student.getStudentId()
                    .equalsIgnoreCase(id)) {

                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Delete student
    public Student delete(String id) {

        if (head == null) {
            return null;
        }

        if (head.student.getStudentId()
                .equalsIgnoreCase(id)) {

            Student deleted = head.student;
            head = head.next;

            return deleted;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student.getStudentId()
                    .equalsIgnoreCase(id)) {

                Student deleted =
                        current.next.student;

                current.next =
                        current.next.next;

                return deleted;
            }

            current = current.next;
        }

        return null;
    }

    // Display records
    public void display() {

        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        Node current = head;

        System.out.println(
                "\n===== STUDENT RECORDS =====");

        while (current != null) {

            System.out.println(current.student);

            current = current.next;
        }
    }
}