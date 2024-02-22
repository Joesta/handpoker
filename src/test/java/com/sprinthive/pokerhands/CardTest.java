package com.sprinthive.pokerhands;

import com.sprinthive.pokerhands.handrank.BadPokerHandRanker;
import com.sprinthive.pokerhands.handrank.HandRank;
import com.sprinthive.pokerhands.handrank.HandRanker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void testCardSuitNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                // Try to create a Card without a suit
                () -> new Card(CardRank.TWO, null),
                "Card should not accept a null Suit");
    }

    @Test
    public void testCardRankNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                // Try to create a Card without a rank
                () -> new Card(null, Suit.CLUBS),
                "Card should not accept a null Rank");
    }

    @Test
    public void testCardTwoOfClubs() {
        // Create a two of clubs
        Card card = new Card(CardRank.TWO, Suit.CLUBS);

        // Ensure that this is an two of clubs
        // Examples of other cards are:
        //   two of diamonds
        //   king of spades
        assertEquals("two of clubs", card.toString());
    }

    @Test
    public void testCardAceOfClubs() {
        // Create a ace of clubs
        Card card = new Card(CardRank.ACE, Suit.CLUBS);

        // Ensure that this is an ace of clubs
        // Examples of other cards are:
        //   two of diamonds
        //   king of spades
        assertEquals("ace of clubs", card.toString());
    }

    @Test
    public void testCardKingOfHearts() {
        // Create a king of hearts
        Card card = new Card(CardRank.KING, Suit.HEARTS);

        // Ensure that this is an king of hearts
        // Examples of other cards are:
        //   two of diamonds
        //   king of spades
        assertEquals("king of hearts", card.toString());
    }

    @Test
    public void testCompareTo() {
        // Create a king of hearts
        Card kingHearts1 = new Card(CardRank.KING, Suit.HEARTS);
        // Create another king of hearts
        Card kingHearts2 = new Card(CardRank.KING, Suit.HEARTS);
        assertEquals(0, kingHearts1.compareTo(kingHearts2));
        // Create a king of clubs
        Card kingClubs = new Card(CardRank.KING, Suit.CLUBS);
        assertEquals(0, kingHearts1.compareTo(kingClubs));
        // Create a queen of hearts
        Card queenHearts = new Card(CardRank.QUEEN, Suit.HEARTS);
        assertEquals(1, kingHearts1.compareTo(queenHearts));
    }

    @Test
    public void testEquals() {
        // Create a king of hearts
        Card kingHearts1 = new Card(CardRank.KING, Suit.HEARTS);
        // Create another king of hearts
        Card kingHearts2 = new Card(CardRank.KING, Suit.HEARTS);
        assertEquals(kingHearts2, kingHearts1);
        // Create a king of clubs
        Card kingClubs = new Card(CardRank.KING, Suit.CLUBS);
        assertNotEquals(kingHearts1, kingClubs);
        // Create a queen of hearts
        Card queenHearts = new Card(CardRank.QUEEN, Suit.HEARTS);
        assertNotEquals(kingHearts1, queenHearts);
    }

    @Test
    public void testHashCode() {
        HashSet<Integer> hashCodes = new HashSet<Integer>(52 * 2);
        for (Suit suit : Suit.values()) {
            for (CardRank rank : CardRank.values()) {
                int hashCode = (new Card(rank, suit)).hashCode();
                if (hashCodes.contains(hashCode)) {
                    fail("We have a hash collision on a 52 card set and need a new hashcode method");
                }
                hashCodes.add(hashCode);
            }
        }
    }

    @Test
    public void testHighestCardRank() {
        // create king of hearts
        Card kingHearts = new Card(CardRank.KING, Suit.HEARTS);
        // create queen of hearts
        Card queenHearts = new Card(CardRank.QUEEN, Suit.HEARTS);
        // create four of diamonds
        Card four = new Card(CardRank.FOUR, Suit.DIAMONDS);
        // create king of clubs
        Card kingSpade = new Card(CardRank.ACE, Suit.CLUBS);
        // create eight of spades
        Card eight = new Card(CardRank.EIGHT, Suit.SPADES);

        List<Card> cards = new ArrayList<>();
        cards.add(kingHearts);
        cards.add(queenHearts);
        cards.add(four);
        cards.add(eight);
        cards.add(kingSpade);

        assertNotNull(cards, "cards my not be empty or null");

        HandRanker handRanker = new BadPokerHandRanker();
        HandRank bestHandRank = handRanker.findBestHandRank(cards);

        assertEquals(5, cards.size(), "expected five cards");
        assertEquals(CardRank.ACE, cards.get(0).getRank(), bestHandRank.describeHand());

        System.out.println(String.format("<<<< %s >>>>>", bestHandRank.describeHand()));

        assertEquals(CardRank.KING, cards.get(1).getRank(), "king of hearts");
        assertEquals(CardRank.QUEEN, cards.get(2).getRank(), "queen of hearts");
        assertEquals(CardRank.EIGHT, cards.get(3).getRank(), "eight of spades");
        assertEquals(CardRank.FOUR, cards.get(4).getRank(), "four of diamonds");

    }
}
