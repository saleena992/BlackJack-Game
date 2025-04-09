// Hand.java
import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotal() {
        int total = 0;
        int aceCount = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank().equals("Ace")) {
                aceCount++;
            }
        }

        // Adjust for Aces (11 or 1)
        while (total > 21 && aceCount > 0) {
            total -= 10; // treat one Ace as 1 instead of 11
            aceCount--;
        }

        return total;
    }

    public void clear() {
        cards.clear();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString() + " (Total: " + getTotal() + ")";
    }
}
