package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;
import com.sprinthive.pokerhands.Suit;

import java.util.HashMap;
import java.util.List;

/**
 * @author Joesta
 */
public class BestSingleFiveHand  {
    // @todo - try and understand the requirement for this task and revisit.

    private final List<HandRank> handRanks;

    public BestSingleFiveHand(List<HandRank> handRanks) {
        if (handRanks.isEmpty()) {
            throw new IllegalArgumentException("handRanks may not be null or empty");
        }
        this.handRanks = handRanks;
    }

   private void bestSingleFiveHand() {
        if (this.handRanks.get(0) instanceof FourOfAKindHandRank) {
            HandRank handRank = new FourOfAKindHandRank(CardRank.ACE);
        }
   }
}
