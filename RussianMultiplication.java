public class RussianMultiplication {
    
    public static int russianMultiplication(int a, int b) { 
        int result = 0; 

        while (a > 0) { 
            // If a is odd, add b to result 
            if (a % 2 == 1) { 
                result += b; 
            } 

            // Cut a in half and double b 
            a = a / 2; 
            b = b * 2;
        } 

        return result;
    }

    public static void main(String[] args) {
        System.out.println(russianMultiplication(13, 11));
    }
}

// HALVES DOUBLES
// 13      11

// 3       44
// 1       88

// SUM = 143
