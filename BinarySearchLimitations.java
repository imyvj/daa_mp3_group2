import java.util.*;

public class BinarySearchLimitations {

    // Menu display
    public static void menu() {
        System.out.println("\n=== Binary Search Limitations Demo ===");
        System.out.println("1. Binary Search on Unsorted Array");
        System.out.println("2. Dynamic Data Structure (Add/Remove/Search)");
        System.out.println("3. Binary Search on Linked List");
        System.out.println("4. Binary Search on Unknown-Length Stream");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public static int[] readIntArray(Scanner scanner) {
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

    // Binary Search method
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Not found
    }

    // Check if List<Integer> is sorted 
    public static boolean isSortedList(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) return false;
        }
        return true;
    }

    // Case 1: Binary Search on User-Provided Array (checks if sorted)
    public static void binarySearchOnUserArray(Scanner scanner) {
        System.out.println("\nBinary Search on Array (with Sort Check)");
        int[] arr = readIntArray(scanner);

        if (!isSorted(arr)) {
            System.out.println("Error: Array is not sorted. Binary search requires sorted input.");
            return;  // Exit the method early
        }

        System.out.print("Enter target to search: ");
        int target = scanner.nextInt();
        int index = binarySearch(arr, target);
        System.out.println(index != -1
                ? "Target " + target + " found at index " + index
                : "Target not found.");
    }

    // Case 2: Interactive Dynamic List Demo
    public static void dynamicDataDemo(Scanner scanner) {
        System.out.println("\nDynamic Data - Add/Remove Elements Demo");
        List<Integer> list = new ArrayList<>();

        System.out.print("Enter number of initial sorted elements: ");
        int n = scanner.nextInt();
        System.out.println("Enter " + n + " sorted elements:");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        boolean isSorted = true;

        while (true) {
            System.out.println("\nCurrent data: " + list);
            System.out.println("Choose action:");
            System.out.println("1. Add element");
            System.out.println("2. Remove element");
            System.out.println("3. Search target (binary search)");
            System.out.println("4. Sort data");
            System.out.println("5. Exit to main menu");
            System.out.print("Your choice: ");
            int action = scanner.nextInt();

            if (action == 1) {
                // Adding new elements
                System.out.print("How many elements to add: ");
                int count = scanner.nextInt();
                System.out.println("Enter " + count + " element(s):");
                for (int i = 0; i < count; i++) {
                    int val = scanner.nextInt();
                    list.add(val);
                }
                isSorted = isSortedList(list);
                if (!isSorted) {
                    System.out.println("Data is now UNSORTED due to the addition.");
                    System.out.println("Binary search cannot be applied unless data is re-sorted.");
                }
            } else if (action == 2) {
                // Removing an element
                System.out.print("Enter element to remove: ");
                int val = scanner.nextInt();
                if (list.remove(Integer.valueOf(val))) {
                    System.out.println("Removed " + val);
                    isSorted = isSortedList(list);
                    if (!isSorted) {
                        System.out.println("Data is now UNSORTED due to the removal.");
                        System.out.println("Binary search cannot be applied unless data is re-sorted.");
                    }
                } else {
                    System.out.println(val + " not found.");
                }
            } else if (action == 3) {
                // Performing binary search on list
                if (!isSorted) {
                    System.out.println("Cannot perform binary search. Data is unsorted.");
                    System.out.println("Please sort the data first (option 4).");
                } else {
                    System.out.print("Enter target to search: ");
                    int target = scanner.nextInt();
                    int[] arr = list.stream().mapToInt(i -> i).toArray();
                    int idx = binarySearch(arr, target);
                    if (idx != -1) {
                        System.out.println("Target found at index " + idx);
                    } else {
                        System.out.println("Target not found.");
                    }
                }
            } else if (action == 4) {
                // Sorting the list
                Collections.sort(list);
                isSorted = true;
                System.out.println("Data sorted. Binary search is now valid.");
            } else if (action == 5) {
                // Exit to main menu
                System.out.println("Returning to main menu...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Case 3: Binary Search on Linked List 
    public static void linkedListDemo(Scanner scanner) {
        System.out.println("\nLinked List Demo - Simulating Binary Search with Linear Access Cost");

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        LinkedList<Integer> list = new LinkedList<>();
        System.out.println("Enter " + n + " sorted elements:");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        System.out.print("Enter target to search: ");
        int target = scanner.nextInt();

        int left = 0, right = list.size() - 1;
        int steps = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = getNodeAt(list, mid);  // Simulates get(mid) in LinkedList
            steps++;

            if (midVal == target) {
                System.out.println("Target " + target + " found at index " + mid + ".");
                System.out.println("Simulated get() steps (linear traversals): " + steps);
                System.out.println("Note: Binary search is inefficient on linked lists because accessing the middle node requires O(n) time.");
                return;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Target not found.");
        System.out.println("Simulated get() steps (linear traversals): " + steps);
        System.out.println("Note: Binary search on linked lists is O(n log n), not O(log n), due to lack of random access.");
    }

    // Simulate get(index) for LinkedList (O(n) time)
    public static int getNodeAt(LinkedList<Integer> list, int index) {
        int i = 0;
        for (int val : list) {
            if (i == index) return val;
            i++;
        }
        throw new IndexOutOfBoundsException("Index out of bounds in getNodeAt()");
    }

    // Case 4: Binary Search on Unknown-Length Stream
    public static void unknownBoundariesDemo(Scanner scanner) {
        System.out.println("\nUnknown-Length Stream Demo");
        System.out.println("Simulating input stream (unknown size)...");

        List<Integer> stream = new ArrayList<>();
        System.out.println("Enter numbers (type 'x' to stop input):");
        while (scanner.hasNextInt()) {
            stream.add(scanner.nextInt());  // Read until non-integer
        }
        scanner.next(); // Consume 'x' or non-integer input

        // Validate target input
        System.out.print("Enter target to search: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int target = scanner.nextInt();

        // Explain limitations
        System.out.println("\n--- Why Binary Search Fails Here ---");
        System.out.println("1. The total length of the stream is unknown while receiving data.");
        System.out.println("2. We cannot calculate the middle element without first reading all elements.");
        System.out.println("3. Binary search requires random access, which streams do not provide.");
        System.out.println("4. Real-time data streams grow dynamically, making indexing impractical.");

        System.out.println("\nConclusion:");
        System.out.println("Binary search is not suitable for real-time or unknown-length streams.");
        System.out.println("A more appropriate approach for such data is linear processing or streaming algorithms.");
    }

    // Main method to drive the menu loop
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            menu();
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            // Switch based on user menu choice
            switch (choice) {
                case 1:
                    binarySearchOnUserArray(scanner);
                    break;
                case 2:
                    dynamicDataDemo(scanner);
                    break;
                case 3:
                    linkedListDemo(scanner);
                    break;
                case 4:
                    unknownBoundariesDemo(scanner);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
        scanner.close();  // Close the scanner resource
    }
}