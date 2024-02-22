package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;

import java.util.Collections;
import java.util.List;

public class BadPokerHandRanker implements HandRanker {

    public HandRank findBestHandRank(List<Card> cards) {
        if (cards.size() != 5) {
            return new NotRankableHandRanker(cards);
        }
        Collections.sort(cards);
        Collections.reverse(cards);
        // High card
        return new HighCardHandRank(cards);
    }

    /**
     * order cards from highest to lowest rank
     * @param cards - collection of unordered cards
     * @return list of ordered cards
     */
    // Initially thought the sorting from collections won't work. Misunderstood the task, hence tried this method
//    private List<Card> orderCardsByRank(List<Card> cards) {
//        int len = cards.size();
//        int highestIndex;
//        Card temp;
//
//        for (int i = 0; i < len - 1; i ++) {
//            highestIndex = i;
//            for (int j = i + 1; j < len; j ++) {
//                if (cards.get(j).getRank().getValue() > cards.get(highestIndex).getRank().getValue()) {
//                    highestIndex = j;
//                    temp = cards.get(i);
//                    // swap cards from highest to lowest ranks
//                    cards.set(i, cards.get(highestIndex));
//                    cards.set(highestIndex, temp);
//                }
//            }
//        }
//
//        return cards;
//    }
}
