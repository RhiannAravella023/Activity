import java.util.Scanner; //imports scanner class to allow keyboard input

public class CustomerTransactionManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //object to read keyboard input
        
        // Task 1: Dynamic Data Input (scanner class) // Asks to input how many customer transactions
        System.out.print("Enter number of customer transactions to process: ");
        int count = scanner.nextInt();
        scanner.nextLine();// clear input buffer

        // Task 2: Array Implementations// DATA STORAGE 
        String[] transactionIds = new String[count];
        double[] transactionAmounts = new double[count];
        double[][] itemDetails = new double[count][3]; // [customer][item category 1-3]

        //POPULATE ARRAYS // i is loop index tracking current customer position as we move forward 

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Entering Data for Customer " + (i + 1) + " ---");
            System.out.print("Enter Transaction ID: ");
            transactionIds[i] = scanner.nextLine();

            System.out.print("Enter Total Purchase Amount ($): ");
            transactionAmounts[i] = scanner.nextDouble();

            System.out.print("Enter Breakdown for 3 item categories:\n");
            for (int j = 0; j < 3; j++) {
                System.out.print(" Category " + (j + 1) + " Amount: $");
                itemDetails[i][j] = scanner.nextDouble();
            }
            scanner.nextLine(); //clear input buffer
        }

        // Display raw data summary table using nested loops
        displayTransactionTable(transactionIds, transactionAmounts, itemDetails, "RAW TRANSACTION DATA"); 

        // Task 3: Sorting algorithm implementation (insertion sort)
        System.out.print("\nWould you like to sort by amount in Ascending (ASC) or Descending (DESC)? ");
        String direction = scanner.next().trim().toUpperCase();

        insertionSort(transactionAmounts, transactionIds, itemDetails, direction);

        // Display sorted data
        displayTransactionTable(transactionIds, transactionAmounts, itemDetails, "SORTED TRANSACTION DATA (" + direction + ")");

        scanner.close(); 
    }

    //HELPER METHOD TO DISPLAY TABLE

    public static void displayTransactionTable(String[] ids, double[] amounts, double[][] details, String title) {
        System.out.println("\n=================================================================");
        System.out.println("             " + title);
        System.out.println("=================================================================");
        System.out.printf("%-15s %-15s %-30s\n", "Txn ID", "Total ($)", "Item Breakdown [1, 2, 3]");
        System.out.println("=================================================================");

        for (int i = 0; i < ids.length; i++) {
            System.out.printf("%-15s $%-14.2f [%.2f, %.2f, %.2f]\n", 
                ids[i], amounts[i], details[i][0], details[i][1], details[i][2]);
        }
        System.out.println("=================================================================");
    }

    // Task 3: Insertion sort method
    public static void insertionSort(double[] amounts, String[] ids, double[][] details, String order) {
        int n = amounts.length;
        
       //store current elements as key to insert  into the sorted portion
        for (int i = 1; i < n; i++) {
            double keyAmount = amounts[i];
            String keyId = ids[i];
            double[] keyRow = details[i];
            int j = i - 1; 
             //j is secondary index behind i. (i-1) to compare elements

            boolean condition; // DESCENDING OR ASCENDING
            do {
                if (order.equals("DESC")) {
                    condition = (j >= 0 && amounts[j] < keyAmount);    
                } else {
                    condition = (j >= 0 && amounts[j] > keyAmount);
                }
                
                //shift elements to the right to make space for the key
                if (condition) {
                    amounts[j + 1] = amounts[j];
                    ids[j + 1] = ids[j];
                    details[j + 1] = details[j];
                    j--;  
                }
            } while (condition);
            
            //inserts the key into its correct sorted position
            amounts[j + 1] = keyAmount;
            ids[j + 1] = keyId;
            details[j + 1] = keyRow;
        }
    }
}