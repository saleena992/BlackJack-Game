// Player.java
public class Player {
    protected Hand hand;

    public Player() {
        hand = new Hand();
    }

    public void hit(Deck deck) {
        Card newCard = deck.dealCard();
        if (newCard != null) {
            hand.addCard(newCard);
            System.out.println("Player draws: " + newCard);
        } else {
            System.out.println("Deck is empty!");
        }
    }

    public void stand() {
        System.out.println("Player stands.");
    }

    public Hand getHand() {
        return hand;
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public void clearHand() {
        hand.clear();
    }
}
