import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidateCard {

    public static boolean works(String cardNumber) {
        int sum = 0;
        int length = cardNumber.length();

        for (int i = 0; i < length; i++) {
        	  int digit = 0 ;

            if (i % 2 == 0) {
                digit *= 2;

                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
        }

        return sum % 10 == 0;
    }
	
    public static void validateCardNumbersFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File("StudentList.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    boolean isValid = works(line);
                    if (isValid) {
                        System.out.println(line + " is right");
                    } else {
                        System.out.println(line + " is not right");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading  " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filename = "CardNums.txt";  
        validateCardNumbersFromFile(filename);
    }
}
