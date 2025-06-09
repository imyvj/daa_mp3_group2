import java.util.*;

public class RussianMultiplication {

    public static int russianMultiplication(int a, int b) { 
        int result = 0; 
        while (a > 0) { 
            if (a % 2 == 1) { 
                result += b; 
            } 
            a = a / 2; 
            b = b * 2;
        } 
        return result;
    }

    public static int russianMultiplicationOptimized(int a, int b) {
        int sign = 1;
        if (a < 0) {
            sign *= -1;
            a = Math.abs(a);
        }
        if (b < 0) {
            sign *= -1;
            b = Math.abs(b);
        }

        int result = 0;
        while (a > 0) {
            if (a % 2 == 1) {
                result += b;
            }
            a = a / 2;
            b = b * 2;
        }

        return result * sign;
    }

    public static int[] menu() {
        Scanner scanner = new Scanner(System.in);
        int[] values = new int[2];

        System.out.println("\nRussian Multiplication:\n");

        System.out.print("Enter the first number: ");
        values[0] = scanner.nextInt();

        System.out.print("Enter the second number: ");
        values[1] = scanner.nextInt();

        return values;
    }

    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            int[] values = menu();
            int x = values[0];
            int y = values[1];

            System.out.println("\nChoose an algorithm to use:\n");
            System.out.println("1. Traditional");
            System.out.println("2. Optimized for Negative Integers");
            System.out.println("3. Exit");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            if(choice == 1) {
                System.out.println("\nAnswer: " + russianMultiplication(x, y) + "\n");
                break;
            } else if(choice == 2) {
                System.out.println("\nAnswer: " + russianMultiplicationOptimized(x, y) + "\n");
                break;
            } else if(choice == 3){
                System.out.println("\nExiting...\n");
            } else {
                System.out.println("\nPlease enter a valid number (1-3).\n");
            }

        } while(choice != 3);
    }
}


// HALVES DOUBLES
// 13      11

// 3       44
// 1       88

// SUM = 143
