import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ValidateCard {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nCard Validator");
            System.out.println("1. Check cards from file");
            System.out.println("2. Check one card");
            System.out.print("Choose 1 or 2: ");

            int choice = input.nextInt();
            input.nextLine(); 

            if (choice == 1) {
                checkCardsFromFile();
            } else if (choice == 2) {
                checkOneCard();
            } else {
                System.out.println("Not a correct choice. Try again.");
            }
        }
    }

    // Luhn
    public static boolean isCorrect(String number) {
        int sum = 0;
        boolean doubleDigit = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return sum % 10 == 0;
    }

    // check input
    public static void checkOneCard() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String number = input.nextLine();

        if (isCorrect(number)) {
            System.out.println("Card is correct.");
        } else {
            System.out.println("Card is incorrect.");
        }
    }

    // Check cards from a file 
    public static void checkCardsFromFile() {
        try {
            File file = new File("src/CardNums.txt");
            Scanner fileInput = new Scanner(file);
            int correctCount = 0;

            while (fileInput.hasNextLine()) {
                String number = fileInput.nextLine().trim();
                if (isCorrect(number)) {
                    System.out.println(number + " is correct.");
                    correctCount++;
                } else {
                    System.out.println(number + " is incorrect.");
                }
            }

            System.out.println("Total correct cards: " + correctCount);
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file.");
        }
    }
}
