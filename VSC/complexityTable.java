public class complexityTable {

    // Each sorting algorithm’s time complexities
    private static final String[][] complexities = {
        {"Algorithm", "Best Case", "Average Case", "Worst Case"},
        {"Bubble Sort", "O(n)", "O(n²)", "O(n²)"},
        {"Insertion Sort", "O(n)", "O(n²)", "O(n²)"},
        {"Merge Sort", "O(n log n)", "O(n log n)", "O(n log n)"},
        {"Quick Sort", "O(n log n)", "O(n log n)", "O(n²)"},
        {"Heap Sort", "O(n log n)", "O(n log n)", "O(n log n)"},
        {"Selection Sort", "O(n²)", "O(n²)", "O(n²)"},
        {"Radix Sort", "O(nk)", "O(nk)", "O(nk)"},
        {"Bucket Sort", "O(n + k)", "O(n + k)", "O(n²)"},
        {"Tree Sort", "O(n log n)", "O(n log n)", "O(n^2)"}
    };

    // Print complexity table
    public static void showTable() {
        System.out.println("\nSorting Algorithm Time Complexities:\n");
        //for loop 
        for (String[] row : complexities) {
            System.out.printf("%-15s %-15s %-15s %-15s%n",
                row[0], row[1], row[2], row[3]);
        }
        System.out.println();
    }
}