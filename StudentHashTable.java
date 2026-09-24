public class StudentHashTable {

    private Student[] table;
    private boolean[] deleted;
    private int tableSize;

    public StudentHashTable(int size) {

        tableSize = size;

        table =
                new Student[tableSize];

        deleted =
                new boolean[tableSize];
    }

    private int hash(String studentId) {

        return Math.abs(
                studentId.toLowerCase()
                        .hashCode())
                % tableSize;
    }

    public boolean insert(Student student) {

        int index =
                hash(student.getStudentId());

        int startIndex = index;

        do {

            if (table[index] == null) {

                table[index] = student;
                deleted[index] = false;

                return true;
            }

            if (table[index]
                    .getStudentId()
                    .equalsIgnoreCase(
                            student.getStudentId())) {

                return false;
            }

            index =
                    (index + 1)
                            % tableSize;

        } while (index != startIndex);

        return false;
    }

    public Student search(String id) {

        int index = hash(id);
        int startIndex = index;

        do {

            if (table[index] == null &&
                    !deleted[index]) {

                return null;
            }

            if (table[index] != null &&
                    table[index]
                            .getStudentId()
                            .equalsIgnoreCase(id)) {

                return table[index];
            }

            index =
                    (index + 1)
                            % tableSize;

        } while (index != startIndex);

        return null;
    }

    public boolean delete(String id) {

        int index = hash(id);
        int startIndex = index;

        do {

            if (table[index] == null &&
                    !deleted[index]) {

                return false;
            }

            if (table[index] != null &&
                    table[index]
                            .getStudentId()
                            .equalsIgnoreCase(id)) {

                table[index] = null;

                deleted[index] = true;

                return true;
            }

            index =
                    (index + 1)
                            % tableSize;

        } while (index != startIndex);

        return false;
    }
}