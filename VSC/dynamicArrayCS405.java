import java.io.*;
import java.security.Key;
import java.util.*;

//import javax.swing.*;
//import java.util.List;
// import ScatterPlot;

// Jeremy Daves
//cs405 large class sorting algorithms

public class dynamicArrayCS405 {

    // Reads numbers from a txt file
    public static int[] readFromFile(String filename) {
        List<Integer> list = new ArrayList<>();
//        int limit = 50000;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Convert List<Integer> to int[]
        return list.stream().mapToInt(i -> i).toArray();
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static class BinaryTreeNode {
    int value;
    BinaryTreeNode left_child;
    BinaryTreeNode right_child;

    BinaryTreeNode(int value) {
        this.value = value;
        this.left_child = null;
        this.right_child = null;
    }
}

    static class TreeSort {

    // Insert a new item into the BST
    public static BinaryTreeNode insert(BinaryTreeNode root, int item) {
        if (root == null) {
            return new BinaryTreeNode(item);
        }
        if (item < root.value) {
            root.left_child = insert(root.left_child, item);
        } else {
            root.right_child = insert(root.right_child, item);
        }
        return root;
    }

    // In-order traversal (Left  -> Root -> Right)
    public static void inOrderTraversal(BinaryTreeNode root, List<Integer> sortedList) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left_child, sortedList);
        sortedList.add(root.value);
        inOrderTraversal(root.right_child, sortedList);
    }

     public static void treeSort(int[] inputArray) {
            long startTime = System.nanoTime();

            if (inputArray.length == 0) {
                System.out.println("Empty array.");
                return;
            }

            BinaryTreeNode root = null;
            for (int item : inputArray) {
                root = insert(root, item);
            }

            List<Integer> sortedResult = new ArrayList<>();
            inOrderTraversal(root, sortedResult);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;

            System.out.println("Ascending Tree Sort:");
            for (int num : sortedResult) System.out.print(num + " ");
            System.out.println("\nTime taken: " + duration + " ms");
        }
    }

    // partition function for quicksort
    public static int partition(int[] arr, int low, int high, boolean ascending) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if ((ascending && arr[j] < pivot) || (!ascending && arr[j] > pivot)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // recursive quicksort
    public static void quickSort(int[] arr, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(arr, low, high, ascending);
            quickSort(arr, low, pi - 1, ascending);
            quickSort(arr, pi + 1, high, ascending);
        }
    }

    public static void bSort(int[] arr, boolean ascending) {
        long startTime = System.nanoTime();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if ((ascending && arr[j] > arr[j + 1]) || (!ascending && arr[j] < arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        } // end bucket sort

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println((ascending ? "Ascending" : "Descending") + " Bubble Sort:");
        for (int num : arr) System.out.print(num + " ");
        
        System.out.println("\nTime taken: " + duration + " ms");
        System.out.println("\n");
    }

    public static void mergeSort(int[] arr, boolean ascending) {
        long startTime = System.nanoTime();
        mergeSortRecursive(arr, 0, arr.length - 1, ascending);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println((ascending ? "Ascending" : "Descending") + " Merge Sort:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nTime taken: " + duration + " ms");
    }

    public static void mergeSortRecursive(int[] arr, int left, int right, boolean ascending) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(arr, left, mid, ascending);
            mergeSortRecursive(arr, mid + 1, right, ascending);
            merge(arr, left, mid, right, ascending);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, boolean ascending) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if ((ascending && L[i] <= R[j]) || (!ascending && L[i] >= R[j])) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    } // end merge sort


    public static void insSort(int[] arr, boolean ascending) {
        long startTime = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int k = arr[i];
            int j = i - 1;
            while (j >= 0 && ((ascending && arr[j] > k) || (!ascending && arr[j] < k))) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = k;
        }
        //timer 
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println((ascending ? "Ascending" : "Descending") + " Insertion Sort:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nTime taken: " + duration + " ms");
        System.out.println("\n");
    } // end insertion sort

    public static void selectionSort(int[] arr, boolean ascending) {
        long startTime = System.nanoTime();

        for (int i = 0; i < arr.length - 1; i++) {
            int minMax = i;
            for (int j = i + 1; j < arr.length; j++) {
                if ((ascending && arr[j] < arr[minMax]) || (!ascending && arr[j] > arr[minMax])) {
                    minMax = j;
                }
            }
            //swap variable
            int temp = arr[minMax];
            arr[minMax] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println((ascending ? "Ascending" : "Descending") + " Selection Sort:");
        for (int num : arr) { 
            System.out.print(num + " ");
        }
        System.out.println("\nTime taken: " + duration + " ms");
        System.out.println("\n");
    }

    public static void heapSort(int[] arr, boolean ascending) {
        long startTime = System.nanoTime();
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(arr, n, i, ascending);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heap(arr, i, 0, ascending);
        }
        //intialize timer for heapSort

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println((ascending ? "Ascending" : "Descending") + " Heap Sort:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nTime taken: " + duration + " ms");
        System.out.println("\n");
    }

    private static void heap(int[] arr, int n, int i, boolean ascending) {
        int j = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && ((ascending && arr[l] > arr[j]) || (!ascending && arr[l] < arr[j]))) {
            j = l;
        }
        if (r < n && ((ascending && arr[r] > arr[j]) || (!ascending && arr[r] < arr[j]))) {
            j = r;
        }
        if (j != i) {
            // swap can be defined in 2 lines for heap

            int swap = arr[i]; arr[i] = arr[j]; arr[j] = swap;
            heap(arr, n, j, ascending);
        }
    } // end heap sort

    public static void radixSort(int[] arr) {
        int max = findMax(arr);
        for (int digit = 1; max / digit > 0; digit *= 10) {
            countingSort(arr, digit);
        }
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max)  {
                max = num;
            }
        }
        return max;
    }

    private static void countingSort(int[] arr, int digit) {

        int n = arr.length;

        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : arr) {

         count[(num / digit) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int reduce = (arr[i] / digit) % 10;
            output[count[reduce] - 1] = arr[i];
            count[reduce]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    } // end of counting sort

    // for floats, doubles
    public static void bucketSort(double[] arr) {
    long startTime = System.nanoTime();

    int n = arr.length;
    if (n <= 0) return;

    // Create empty buckets
    @SuppressWarnings("unchecked")
    List<Double>[] buckets = new List[n];
    for (int i = 0; i < n; i++) {
        buckets[i] = new ArrayList<>();
    }

    // Put indvl elements in buckets
    for (int i = 0; i < n; i++) {
        int bucketIndex = (int)(arr[i] * n); 
        if (bucketIndex >= n) bucketIndex = n - 1; // safety clamp
        buckets[bucketIndex].add(arr[i]);
    }

    // Sort call
    for (List<Double> bucket : buckets) {
        Collections.sort(bucket);
    }

    // Merge buckets back into array
    int index = 0;
    for (List<Double> bucket : buckets) {
        for (double num : bucket) {
            arr[index++] = num;
        }
    }
    // timer for merge
    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1_000_000;

    System.out.println("Bucket Sort Result:");
    for (double num : arr) {
        System.out.print(num + " ");
    }
    System.out.println("\nTime taken: " + duration + " ms");
}

public static void printArray(int[] arr) {
    for (int n : arr) {
        System.out.print(n + " ");
    }
    System.out.println();
}

public static void asciiScatterPlot(int[] arr) {
    if (arr.length == 0) {
        System.out.println("Array is empty. Nothing to plot.");
        return;
    }

    int width = 60;  // columns for plot
    int height = 20; // rows for plot

    int maxVal = Arrays.stream(arr).max().getAsInt();
    int minVal = Arrays.stream(arr).min().getAsInt();
    int range = maxVal - minVal;
    if (range == 0) range = 1;

    char[][] plot = new char[height][width];
    for (int i = 0; i < height; i++)
        Arrays.fill(plot[i], ' ');

    // Place points
    for (int i = 0; i < arr.length; i++) {
        int x = i * (width - 1) / (arr.length - 1);
        int y = (arr[i] - minVal) * (height - 1) / range;
        y = height - 1 - y; // invert Y axis
        plot[y][x] = '*';
    }

    // Print plot with Y-axis labels
    for (int i = 0; i < height; i++) {
        int yLabel = maxVal - i * range / (height - 1);
        System.out.printf("%4d |", yLabel); // Y-axis
        for (int j = 0; j < width; j++) {
            System.out.print(plot[i][j]);
        }
        System.out.println();
    }

    // X-axis
    System.out.print("     +");
    for (int i = 0; i < width; i++) System.out.print("-");
    System.out.println();

    // X-axis labels (0, middle, max)
    System.out.printf("      0%"+(width/2-1)+"s%d\n", "", arr.length-1);

    System.out.println("Min: " + minVal + " Max: " + maxVal);
}


    public static void countingSort(int[] arr, boolean ascending) {
    if (arr.length == 0) return;

    long startTime = System.nanoTime(); // start timer

    int max = Arrays.stream(arr).max().getAsInt();
    int min = Arrays.stream(arr).min().getAsInt();
    int range = max - min + 1;

    // Prevent OutOfMemoryError for huge ranges
    if (range > 1_000_000) {
        System.out.println("Counting Sort skipped: range too large (" + range + ")");
        return;
    }

    int[] count = new int[range];
    for (int num : arr) count[num - min]++;

    int index = 0;
    if (ascending) {
        for (int i = 0; i < range; i++)
            while (count[i]-- > 0) arr[index++] = i + min;
    } else {
        for (int i = range - 1; i >= 0; i--)
            while (count[i]-- > 0) arr[index++] = i + min;
    }

    long endTime = System.nanoTime(); // end timer
    long duration = (endTime - startTime) / 1_000_000; // in ms

    // Only one print block
    System.out.println((ascending ? "Ascending" : "Descending") + " Counting Sort:");
    printArray(arr);
    System.out.println("Time taken: " + duration + " ms\n");
}

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Sort in ascending or descending order? (a/d): ");
        boolean ascending = true;
        String order = s.next();
        if (order.equalsIgnoreCase("d")) ascending = false;


        System.out.println("Choose sorting algorithm:");
        System.out.println("0. Tree");
        System.out.println("1. Bubble");
        System.out.println("2. Insertion");
        System.out.println("3. Merge");
        System.out.println("4. Quick");
        System.out.println("5. Heap");
        System.out.println("6. Selection");
        System.out.println("7. Radix");
        System.out.println("8. Bucket");
        System.out.println("9. All");
        System.out.println("10. Counting Sort");
        System.out.println("11. Load from file and sort");
        int choice = s.nextInt();

        int[] arr = null;
        double[] arrDouble = null;

        if (choice == 11) {
            System.out.print("Enter filename (numbers_1M.txt/numbers_500k.txt/numbers_10M.txt): ");
            String filename = s.next();
            arr = readFromFile(filename);

            if (arr.length == 0) {
                System.out.println("No numbers loaded — file may be empty or missing.");
            } else {
                System.out.println("Loaded " + arr.length + " numbers from " + filename);
                System.out.println("Choose sorting algorithm:");
                System.out.println("0. Tree");
                System.out.println("1. Bubble Sort");
                System.out.println("2. Insertion Sort");
                System.out.println("3. Merge Sort");
                System.out.println("4. Quick Sort");
                System.out.println("5. Heap Sort");
                System.out.print("Enter choice: ");
                int sortChoice = s.nextInt();

                long start = System.currentTimeMillis();
                switch (sortChoice) {
                    case 0 -> TreeSort.treeSort(arr);
                    case 1 -> bSort(arr, true);
                    case 2 -> insSort(arr, true);
                    case 3 -> mergeSort(arr, true);
                    case 4 -> {
                    long startTime = System.nanoTime();                  // start timer
                    quickSort(arr, 0, arr.length - 1, ascending);       
                    long endTime = System.nanoTime();                    // end timer
                    long duration = (endTime - startTime) / 1_000_000;  // convert to ms
                    System.out.println((ascending ? "Ascending" : "Descending") + " Quick Sort:");
                    printArray(arr);
                    System.out.println("Time taken: " + duration + " ms\n"); // show actual time
                }
                    case 5 -> heapSort(arr, true);
                    default -> System.out.println("Invalid choice.");
                }
                long end = System.currentTimeMillis();
                System.out.println("Sorting completed in " + (end - start) + " ms.");

                // Show preview if large
                System.out.println("\nSorted Output Preview");
                if (arr.length <= 50) {
                    for (int n : arr) System.out.print(n + " ");
                } else {
                    for (int i = 0; i < 25; i++) System.out.print(arr[i] + " ");
                    System.out.print("... ");
                    for (int i = arr.length - 25; i < arr.length; i++)
                        System.out.print(arr[i] + " ");
                    System.out.println("\n(total: " + arr.length + " numbers)");
                }

                System.out.print("View ASCII scatter plot of the sorted array? (yes/no): ");
                String plotChoice = s.next();
                if (plotChoice.equalsIgnoreCase("yes") || plotChoice.equalsIgnoreCase("y")) {
                    asciiScatterPlot(arr);
                }
            }
        } else if (choice == 8) {
            // Bucket sort with doubles
            System.out.println("Enter decimal numbers between 0 and 1 (type 'done' to finish):");
            List<Double> input = new ArrayList<>();
            while (s.hasNext()) {
                if (s.hasNextDouble()) input.add(s.nextDouble());
                else if (s.next().equalsIgnoreCase("done")) break;
            }
            arrDouble = input.stream().mapToDouble(Double::doubleValue).toArray();
            bucketSort(arrDouble);
            System.out.print("Would you like to see an ASCII scatter plot of the sorted array? (yes/no): ");
            String plotChoice = s.next();
            if (plotChoice.equalsIgnoreCase("yes") || plotChoice.equalsIgnoreCase("y")) {
                // Convert doubles 0..1 to int 0..100 for ASCII plotting

                int[] intArr = Arrays.stream(arrDouble).mapToInt(d -> (int)(d * 100)).toArray();
                asciiScatterPlot(intArr);
            }
        } else {
            // General integer input
            System.out.println("Enter integers (type 'done' to finish):");
            List<Integer> input = new ArrayList<>();

            while (s.hasNext()) {
                if (s.hasNextInt()) input.add(s.nextInt());
                else if (s.next().equalsIgnoreCase("done")) break;
            }
            arr = input.stream().mapToInt(Integer::intValue).toArray();

            switch (choice) {
                case 0 -> TreeSort.treeSort(arr);
                case 1 -> bSort(arr, true);
                case 2 -> insSort(arr, true);
                case 3 -> mergeSort(arr, true);
                case 4 -> {
                    long startTime = System.nanoTime();                  // start timer
                    quickSort(arr, 0, arr.length - 1, ascending);       
                    long endTime = System.nanoTime();                    // end timer
                    long duration = (endTime - startTime) / 1_000_000;  // convert to ms
                    System.out.println((ascending ? "Ascending" : "Descending") + " Quick Sort:");
                    printArray(arr);
                    System.out.println("Time taken: " + duration + " ms\n"); // show actual time
                }
                case 5 -> heapSort(arr, true);
                case 6 -> selectionSort(arr, true);
                case 7 -> radixSort(arr);
                case 9 -> {
                int[] copy;

            copy = arr.clone(); bSort(copy, ascending); System.out.println();
            copy = arr.clone(); insSort(copy, ascending); System.out.println();
            copy = arr.clone(); mergeSort(copy, ascending); System.out.println();
            copy = arr.clone(); quickSort(copy, 0, copy.length - 1, ascending); 
            System.out.println((ascending ? "Ascending" : "Descending") + " Quick Sort:");
            printArray(copy); System.out.println();
            copy = arr.clone(); heapSort(copy, ascending); System.out.println();
            copy = arr.clone(); selectionSort(copy, ascending); System.out.println();
            copy = arr.clone(); countingSort(copy, ascending); System.out.println();
            copy = arr.clone(); TreeSort.treeSort(copy); System.out.println();
            copy = arr.clone(); radixSort(copy); 
            System.out.println();
        }
                case 10 -> countingSort(arr, ascending);
                default -> System.out.println("Invalid choice.");
            }

            System.out.print("View ASCII scatter plot of the sorted array? (yes/no): ");
            String plotChoice = s.next();
            if (plotChoice.equalsIgnoreCase("yes") || plotChoice.equalsIgnoreCase("y")) {
                asciiScatterPlot(arr);
            }
        }

        // Complexity table prompt
        System.out.print("View the complexity table? (yes/no): ");
        String r = s.next();
        if (r.equalsIgnoreCase("yes") || r.equalsIgnoreCase("y")) {
            complexityTable.showTable();
        }

        s.close();
    }
}