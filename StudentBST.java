public class StudentBST {

    private class Node {

        Student student;
        Node left;
        Node right;

        Node(Student student) {

            this.student = student;
            left = null;
            right = null;
        }
    }

    private Node root;

    public StudentBST() {
        root = null;
    }

    public boolean insert(Student student) {

        if (root == null) {

            root = new Node(student);

            return true;
        }

        Node current = root;

        while (true) {

            int compare =
                    student.getStudentId()
                            .compareToIgnoreCase(
                                    current.student
                                            .getStudentId());

            if (compare == 0) {

                return false;
            }

            if (compare < 0) {

                if (current.left == null) {

                    current.left =
                            new Node(student);

                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {

                    current.right =
                            new Node(student);

                    return true;
                }

                current = current.right;
            }
        }
    }

    public Student search(String id) {

        Node current = root;

        while (current != null) {

            int compare =
                    id.compareToIgnoreCase(
                            current.student
                                    .getStudentId());

            if (compare == 0) {

                return current.student;

            } else if (compare < 0) {

                current = current.left;

            } else {

                current = current.right;
            }
        }

        return null;
    }

    public void displayInOrder() {

        if (root == null) {

            System.out.println(
                    "BST is empty.");

            return;
        }

        System.out.println(
                "\n===== STUDENTS USING BST =====");

        inOrder(root);
    }

    private void inOrder(Node node) {

        if (node != null) {

            inOrder(node.left);

            System.out.println(node.student);

            inOrder(node.right);
        }
    }

    public void delete(String id) {

        root = deleteRecursive(root, id);
    }

    private Node deleteRecursive(
            Node node, String id) {

        if (node == null) {
            return null;
        }

        int compare =
                id.compareToIgnoreCase(
                        node.student.getStudentId());

        if (compare < 0) {

            node.left =
                    deleteRecursive(
                            node.left, id);

        } else if (compare > 0) {

            node.right =
                    deleteRecursive(
                            node.right, id);

        } else {

            // No child
            if (node.left == null &&
                    node.right == null) {

                return null;
            }

            // One child
            if (node.left == null) {

                return node.right;
            }

            if (node.right == null) {

                return node.left;
            }

            // Two children
            Node successor =
                    findMinimum(node.right);

            node.student =
                    successor.student;

            node.right =
                    deleteRecursive(
                            node.right,
                            successor.student
                                    .getStudentId());
        }

        return node;
    }

    private Node findMinimum(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}