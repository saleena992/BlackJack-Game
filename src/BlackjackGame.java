// BlackjackGame.java
import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private Scanner scanner;

    public BlackjackGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Blackjack!");

        boolean playAgain;
        do {
            playRound();
            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("yes");
            resetHands();
        } while (playAgain);

        System.out.println("Thanks for playing!");
    }

    private void playRound() {
        deck.shuffle();

        // Initial deal
        player.hit(deck);
        player.hit(deck);
        dealer.hit(deck);
        dealer.hit(deck);

        System.out.println("\nYour hand: " + player.getHand());

        // Player turn
        boolean playerTurn = true;
        while (playerTurn) {
            System.out.print("Do you want to hit or stand? ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("hit")) {
                player.hit(deck);
                System.out.println("Your hand: " + player.getHand());
                if (player.getHandTotal() > 21) {
                    System.out.println("You busted!");
                    playerTurn = false;
                }
            } else if (choice.equals("stand")) {
                player.stand();
                playerTurn = false;
            } else {
                System.out.println("Invalid choice. Type 'hit' or 'stand'.");
            }
        }

        // If player busts, dealer doesn't play
        if (player.getHandTotal() <= 21) {
            dealer.play(deck);
        }

        // Evaluate result
        checkBlackjackOrBust();
    }

    private void checkBlackjackOrBust() {
        int playerTotal = player.getHandTotal();
        int dealerTotal = dealer.getHandTotal();

        System.out.println("\nFinal Hands:");
        System.out.println("Your hand: " + player.getHand());
        System.out.println("Dealer's hand: " + dealer.getHand());

        if (playerTotal > 21) {
            System.out.println("You busted. Dealer wins!");
        } else if (dealerTotal > 21) {
            System.out.println("Dealer busted. You win!");
        } else if (playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void resetHands() {
        player.clearHand();
        dealer.clearHand();
    }
}
