public class CampusGraph {

    private  String[] locations;
    private int[][] adjacencyMatrix;

    private int locationCount;
    private int maxLocations;
    

    public CampusGraph(int size) {

        maxLocations = size;

        locations =
                new String[maxLocations];

        adjacencyMatrix =
                new int[maxLocations]
                        [maxLocations];

        locationCount = 0;
    }

    public boolean addLocation(String location) {

        if (findLocation(location) != -1) {

            System.out.println(
                    "Location already exists.");

            return false;
        }

        if (locationCount == maxLocations) {

            System.out.println(
                    "Maximum location limit reached.");

            return false;
        }

        locations[locationCount] =
                location;

        locationCount++;

        return true;
    }

    public boolean removeLocation(String location) {

        int index =
                findLocation(location);

        if (index == -1) {

            return false;
        }

        // Shift location names
        for (int i = index;
             i < locationCount - 1;
             i++) {

            locations[i] =
                    locations[i + 1];
        }

        // Shift matrix rows
        for (int i = index;
             i < locationCount - 1;
             i++) {

            for (int j = 0;
                 j < locationCount;
                 j++) {

                adjacencyMatrix[i][j] =
                        adjacencyMatrix[i + 1][j];
            }
        }

        // Shift matrix columns
        for (int j = index;
             j < locationCount - 1;
             j++) {

            for (int i = 0;
                 i < locationCount - 1;
                 i++) {

                adjacencyMatrix[i][j] =
                        adjacencyMatrix[i][j + 1];
            }
        }

        locationCount--;

        locations[locationCount] = null;

        for (int i = 0;
             i < maxLocations;
             i++) {

            adjacencyMatrix[locationCount][i] = 0;
            adjacencyMatrix[i][locationCount] = 0;
        }

        return true;
    }

    public boolean addConnection(
            String location1,
            String location2) {

        int index1 =
                findLocation(location1);

        int index2 =
                findLocation(location2);

        if (index1 == -1 ||
                index2 == -1) {

            return false;
        }

        if (index1 == index2) {

            return false;
        }

        adjacencyMatrix[index1][index2] = 1;
        adjacencyMatrix[index2][index1] = 1;

        return true;
    }

    public boolean removeConnection(
            String location1,
            String location2) {

        int index1 =
                findLocation(location1);

        int index2 =
                findLocation(location2);

        if (index1 == -1 ||
                index2 == -1) {

            return false;
        }

        if (adjacencyMatrix[index1][index2]
                == 0) {

            return false;
        }

        adjacencyMatrix[index1][index2] = 0;
        adjacencyMatrix[index2][index1] = 0;

        return true;
    }

    public int findLocation(String name) {

        for (int i = 0;
             i < locationCount;
             i++) {

            if (locations[i]
                    .equalsIgnoreCase(name)) {

                return i;
            }
        }

        return -1;
    }

    public void displayConnections() {

        if (locationCount == 0) {

            System.out.println(
                    "No campus locations available.");

            return;
        }

        System.out.println(
                "\n===== CAMPUS CONNECTIONS =====");

        for (int i = 0;
             i < locationCount;
             i++) {

            System.out.print(
                    locations[i] + " -> ");

            boolean connected = false;

            for (int j = 0;
                 j < locationCount;
                 j++) {

                if (adjacencyMatrix[i][j]
                        == 1) {

                    System.out.print(
                            locations[j] + " ");

                    connected = true;
                }
            }

            if (!connected) {

                System.out.print(
                        "No connections");
            }

            System.out.println();
        }
    }

    // Breadth First Search
    public void bfs(String startLocation) {

        int start =
                findLocation(startLocation);

        if (start == -1) {

            System.out.println(
                    "Starting location not found.");

            return;
        }

        boolean[] visited =
                new boolean[locationCount];

        int[] queue =
                new int[locationCount];

        int front = 0;
        int rear = -1;

        visited[start] = true;

        queue[++rear] = start;

        System.out.println(
                "\n===== BFS TRAVERSAL =====");

        while (front <= rear) {

            int current =
                    queue[front++];

            System.out.print(
                    locations[current] + " ");

            for (int i = 0;
                 i < locationCount;
                 i++) {

                if (adjacencyMatrix[current][i]
                        == 1 &&
                        !visited[i]) {

                    visited[i] = true;

                    queue[++rear] = i;
                }
            }
        }

        System.out.println();
    }
}