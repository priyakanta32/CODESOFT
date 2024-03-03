package intern_java_development;
import java.util.*;
public class TASK_1 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();
	        boolean playAgain = true;
	        int round = 1;
	        int score = 0;

	        while (playAgain) {
	            System.out.println("Round-" + round );
	            int targetNumber = random.nextInt(100) + 1;
	            int guess;
	            int attempts = 0;
	            boolean guessedCorrectly = false;

	            System.out.println("Guess the number between 1 and 100:");

	            while (!guessedCorrectly && attempts < 10) { 
	                System.out.print("Enter your guess: ");
	                guess = scanner.nextInt();
	                attempts++;

	                if (guess == targetNumber) {
	                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
	                    guessedCorrectly = true;
	                    score += 10 - attempts; 
	                } else if (guess < targetNumber) {
	                    System.out.println("Too low. Try again!");
	                } else {
	                    System.out.println("Too high. Try again!");
	                }
	            }

	            if (!guessedCorrectly) {
	                System.out.println("Sorry, you've reached the maximum attempts. The correct number was: " + targetNumber);
	            }

	            System.out.println("Your current score: " + score);

	            System.out.print("Do you want to play again? (yes/no): ");
	            String playAgainResponse = scanner.next().toLowerCase();

	            if (playAgainResponse.equals("no")) {
	                playAgain = false;
	            } else {
	                round++;
	            }
	        }

	        System.out.println("Thanks for playing! Your final score is: " + score);
	        scanner.close();
	    }
	}

	
	

