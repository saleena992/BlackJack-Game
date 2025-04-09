// BlackjackGame.java
import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private Scanner scanner;

    private int playerScore = 0;
    private int dealerScore = 0;
    private int roundCount = 0;
    private final int MAX_ROUNDS = 4;

    public BlackjackGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Blackjack!");

        while (roundCount < MAX_ROUNDS) {
            System.out.println("\n--- Round " + (roundCount + 1) + " ---");
            playRound();
            roundCount++;
            resetHands();
        }

        System.out.println("\n=== Final Results After " + MAX_ROUNDS + " Rounds ===");
        System.out.println("Player Wins: " + playerScore);
        System.out.println("Dealer Wins: " + dealerScore);

        if (playerScore > dealerScore) {
            System.out.println("🏆 Overall Winner: Player!");
        } else if (dealerScore > playerScore) {
            System.out.println("🏆 Overall Winner: Dealer!");
        } else {
            System.out.println("🤝 It's a tie overall!");
        }

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

        // Dealer plays only if player hasn't busted
        if (player.getHandTotal() <= 21) {
            dealer.play(deck);
        }

        // Evaluate winner and update score
        checkWinnerAndScore();
    }

    private void checkWinnerAndScore() {
        int playerTotal = player.getHandTotal();
        int dealerTotal = dealer.getHandTotal();

        System.out.println("\nFinal Hands:");
        System.out.println("Your hand: " + player.getHand());
        System.out.println("Dealer's hand: " + dealer.getHand());

        if (playerTotal > 21) {
            System.out.println("You busted. Dealer wins!");
            dealerScore++;
        } else if (dealerTotal > 21) {
            System.out.println("Dealer busted. You win!");
            playerScore++;
        } else if (playerTotal > dealerTotal) {
            System.out.println("You win!");
            playerScore++;
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins!");
            dealerScore++;
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void resetHands() {
        player.clearHand();
        dealer.clearHand();
    }
}
