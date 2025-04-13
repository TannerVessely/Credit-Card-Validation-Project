import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidateCard {

    public static boolean works(String cardNumber) {
        int sum = 0;
        int length = cardNumber.length();

        for (int i = 0; i < length; i++) {
            int digit = Character.digit(cardNumber.charAt(i), 10);

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
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    boolean isValid = works(line);
                    if (isValid) {
                        System.out.println(line + " is VALID.");
                    } else {
                        System.out.println(line + " is INVALID.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Specify the text file name that contains the card numbers
        String filename = "CardNums.txt";  
        validateCardNumbersFromFile(filename);
    }
}
