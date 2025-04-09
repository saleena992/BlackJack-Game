// Dealer.java
public class Dealer extends Player {

    public void play(Deck deck) {
        System.out.println("\nDealer's turn:");
        System.out.println("Dealer's hand: " + hand);

        while (getHandTotal() < 17) {
            Card newCard = deck.dealCard();
            if (newCard != null) {
                hand.addCard(newCard);
                System.out.println("Dealer draws: " + newCard);
            } else {
                System.out.println("Deck is empty!");
                break;
            }
        }

        System.out.println("Dealer's final hand: " + hand);
        if (getHandTotal() > 21) {
            System.out.println("Dealer busts!");
        } else {
            System.out.println("Dealer stands.");
        }
    }
}
