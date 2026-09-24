import java.util.Scanner;

public class Main {

    private static Scanner scanner =
            new Scanner(System.in);

    private static StudentLinkedList studentList =
            new StudentLinkedList();

    private static ActionStack actionStack =
            new ActionStack(20);

    private static ServiceQueue serviceQueue =
            new ServiceQueue(20);

    private static StudentBST studentBST =
            new StudentBST();

    private static StudentHashTable hashTable =
            new StudentHashTable(101);

    private static CampusGraph campusGraph =
            new CampusGraph(50);

    public static void main(String[] args) {

        int choice;

        System.out.println(
                "==========================================");

        System.out.println(
                " UNIVERSITY STUDENT RECORD AND");

        System.out.println(
                " CAMPUS ROUTE MANAGEMENT SYSTEM");

        System.out.println(
                "==========================================");

        do {

            displayMenu();

            choice =
                    readInteger(
                            "Enter your choice: ");

            System.out.println();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    deleteStudent();
                    break;

                case 4:
                    studentList.display();
                    break;

                case 5:
                    addServiceRequest();
                    break;

                case 6:
                    processServiceRequest();
                    break;

                case 7:
                    actionStack.display();
                    break;

                case 8:
                    studentBST.displayInOrder();
                    break;

                case 9:
                    searchUsingHashing();
                    break;

                case 10:
                    addCampusLocation();
                    break;

                case 11:
                    removeCampusLocation();
                    break;

                case 12:
                    addCampusConnection();
                    break;

                case 13:
                    removeCampusConnection();
                    break;

                case 14:
                    campusGraph.displayConnections();
                    break;

                case 15:
                    traverseCampus();
                    break;

                case 16:

                    System.out.println(
                            "Exiting system...");

                    System.out.println(
                            "Thank you.");

                    break;

                default:

                    System.out.println(
                            "Invalid menu option.");
            }

        } while (choice != 16);

        scanner.close();
    }


    // ==========================
    // MENU
    // ==========================

    private static void displayMenu() {

        System.out.println(
                "\n=============== MAIN MENU ===============");

        System.out.println(
                "1. Add Student Record");

        System.out.println(
                "2. Update Student Record");

        System.out.println(
                "3. Delete Student Record");

        System.out.println(
                "4. Display All Records using Linked List");

        System.out.println(
                "5. Add Service Request to Queue");

        System.out.println(
                "6. Process Next Service Request");

        System.out.println(
                "7. Display Recent Actions using Stack");

        System.out.println(
                "8. Display Students using BST");

        System.out.println(
                "9. Search Student using Hashing");

        System.out.println(
                "10. Add Campus Location");

        System.out.println(
                "11. Remove Campus Location");

        System.out.println(
                "12. Add Campus Connection/Road");

        System.out.println(
                "13. Remove Campus Connection/Road");

        System.out.println(
                "14. Display Campus Connections");

        System.out.println(
                "15. Traverse Campus Locations using BFS");

        System.out.println(
                "16. Exit");

        System.out.println(
                "=========================================");
    }


    // ==========================
    // STUDENT
    // ==========================

    private static void addStudent() {

        System.out.println(
                "===== ADD STUDENT =====");

        String id =
                readNonEmptyString(
                        "Student ID: ");

        if (studentList.search(id) != null) {

            System.out.println(
                    "Error: Student ID already exists.");

            return;
        }

        String name =
                readNonEmptyString(
                        "Student Name: ");

        String programme =
                readNonEmptyString(
                        "Programme: ");

        double marks =
                readMarks();

        Student student =
                new Student(
                        id,
                        name,
                        programme,
                        marks);

        studentList.add(student);

        studentBST.insert(student);

        hashTable.insert(student);

        actionStack.push(
                "Added student: " + id);

        System.out.println(
                "Student added successfully.");
    }


    private static void updateStudent() {

        System.out.println(
                "===== UPDATE STUDENT =====");

        String id =
                readNonEmptyString(
                        "Enter Student ID: ");

        Student student =
                studentList.search(id);

        if (student == null) {

            System.out.println(
                    "Student record not found.");

            return;
        }

        System.out.println(
                "Current Record:");

        System.out.println(student);

        String name =
                readNonEmptyString(
                        "Enter new name: ");

        String programme =
                readNonEmptyString(
                        "Enter new programme: ");

        double marks =
                readMarks();

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        actionStack.push(
                "Updated student: " + id);

        System.out.println(
                "Student updated successfully.");
    }


    private static void deleteStudent() {

        System.out.println(
                "===== DELETE STUDENT =====");

        String id =
                readNonEmptyString(
                        "Enter Student ID: ");

        Student deleted =
                studentList.delete(id);

        if (deleted == null) {

            System.out.println(
                    "Student record not found.");

            return;
        }

        studentBST.delete(id);

        hashTable.delete(id);

        actionStack.push(
                "Deleted student: " + id);

        System.out.println(
                "Student deleted successfully.");

        System.out.println(
                "Deleted Record:");

        System.out.println(deleted);
    }


    // ==========================
    // QUEUE
    // ==========================

    private static void addServiceRequest() {

        System.out.println(
                "===== ADD SERVICE REQUEST =====");

        String studentId =
                readNonEmptyString(
                        "Student ID: ");

        String request =
                readNonEmptyString(
                        "Request: ");

        String fullRequest =
                "Student " +
                        studentId +
                        " - " +
                        request;

        serviceQueue.insert(fullRequest);

        actionStack.push(
                "Added service request for " +
                        studentId);
    }


    private static void processServiceRequest() {

        System.out.println(
                "===== PROCESS SERVICE REQUEST =====");

        String request =
                serviceQueue.remove();

        if (request == null) {

            System.out.println(
                    "No service requests available.");

            return;
        }

        System.out.println(
                "Processing:");

        System.out.println(request);

        actionStack.push(
                "Processed service request: " +
                        request);
    }


    // ==========================
    // HASHING
    // ==========================

    private static void searchUsingHashing() {

        System.out.println(
                "===== HASH SEARCH =====");

        String id =
                readNonEmptyString(
                        "Enter Student ID: ");

        Student student =
                hashTable.search(id);

        if (student == null) {

            System.out.println(
                    "Student not found.");

        } else {

            System.out.println(
                    "Student found:");

            System.out.println(student);
        }
    }


    // ==========================
    // GRAPH
    // ==========================

    private static void addCampusLocation() {

        System.out.println(
                "===== ADD CAMPUS LOCATION =====");

        String location =
                readNonEmptyString(
                        "Location name: ");

        if (campusGraph
                .addLocation(location)) {

            System.out.println(
                    "Location added successfully.");

            actionStack.push(
                    "Added campus location: " +
                            location);
        }
    }


    private static void removeCampusLocation() {

        System.out.println(
                "===== REMOVE CAMPUS LOCATION =====");

        String location =
                readNonEmptyString(
                        "Location name: ");

        if (campusGraph
                .removeLocation(location)) {

            System.out.println(
                    "Location removed successfully.");

            actionStack.push(
                    "Removed campus location: " +
                            location);

        } else {

            System.out.println(
                    "Location not found.");
        }
    }


    private static void addCampusConnection() {

        System.out.println(
                "===== ADD CAMPUS CONNECTION =====");

        String location1 =
                readNonEmptyString(
                        "First location: ");

        String location2 =
                readNonEmptyString(
                        "Second location: ");

        if (campusGraph
                .addConnection(
                        location1,
                        location2)) {

            System.out.println(
                    "Connection added successfully.");

            actionStack.push(
                    "Connected " +
                            location1 +
                            " and " +
                            location2);

        } else {

            System.out.println(
                    "Unable to add connection.");

            System.out.println(
                    "Check that both locations exist " +
                            "and are different.");
        }
    }


    private static void removeCampusConnection() {

        System.out.println(
                "===== REMOVE CAMPUS CONNECTION =====");

        String location1 =
                readNonEmptyString(
                        "First location: ");

        String location2 =
                readNonEmptyString(
                        "Second location: ");

        if (campusGraph
                .removeConnection(
                        location1,
                        location2)) {

            System.out.println(
                    "Connection removed successfully.");

            actionStack.push(
                    "Removed connection between " +
                            location1 +
                            " and " +
                            location2);

        } else {

            System.out.println(
                    "Connection does not exist.");
        }
    }


    private static void traverseCampus() {

        System.out.println(
                "===== BFS CAMPUS TRAVERSAL =====");

        String start =
                readNonEmptyString(
                        "Starting location: ");

        campusGraph.bfs(start);
    }


    // ==========================
    // INPUT VALIDATION
    // ==========================

    private static int readInteger(
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Enter a number.");
            }
        }
    }


    private static double readMarks() {

        while (true) {

            System.out.print(
                    "Marks (0 - 100): ");

            String input =
                    scanner.nextLine();

            try {

                double marks =
                        Double.parseDouble(input);

                if (marks >= 0 &&
                        marks <= 100) {

                    return marks;
                }

                System.out.println(
                        "Marks must be between 0 and 100.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid marks.");
            }
        }
    }


    private static String readNonEmptyString(
            String message) {

        while (true) {

            System.out.print(message);

            String value =
                    scanner.nextLine().trim();

            if (!value.isEmpty()) {

                return value;
            }

            System.out.println(
                    "Input cannot be empty.");
        }
    }
}