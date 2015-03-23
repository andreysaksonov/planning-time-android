package com.redbooth.projectnevada;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.redbooth.projectnevada.core.*;
import com.redbooth.projectnevada.model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class CardsPagerAdapter extends FragmentPagerAdapter {
    private final Dealer dealer;
    private final List<CardFragment> fragmentList;
    private final List<CardModel> cardModelList;

    private CardFragment.OnCardStatusChangeListener onCardFragmentStateChange = new CardFragment.OnCardStatusChangeListener() {
        @Override
        public void onCardStatusChange(Fragment fragment, CardModel card, CardModel.CardStatus newStatus) {
            for(int index = 0; index < cardModelList.size(); index++) {
                cardModelList.get(index).setStatus(newStatus);
                fragmentList.get(index).setCardStatus(newStatus);
            }
        }
    };

    public CardsPagerAdapter(FragmentManager fragmentManager, Dealer dealer) {
        super(fragmentManager);
        this.dealer = dealer;
        this.fragmentList = new ArrayList<>(dealer.getDeckLength());
        this.cardModelList = new ArrayList<>(dealer.getDeckLength());
        initializeFragmentPool();
        initializeCardModelPool();
    }

    private void initializeFragmentPool() {
        int count = dealer.getDeckLength();
        while (count-- > 0) {
            CardFragment fragment = new CardFragment();
            fragment.setOnCardStatusChangeListener(onCardFragmentStateChange);
            fragmentList.add(fragment);
        }
    }

    private void initializeCardModelPool() {
        int count = dealer.getDeckLength();
        while (count-- > 0) {
            CardModel cardModel = new CardModel();
            cardModel.setDownwardResourceId(R.drawable.cover_big);
            cardModel.setUpwardResourceId(R.drawable.card14_big);
            CardModel.CardStatus cardStatus = CardModel.CardStatus.UPWARDS;
            if (dealer.getDeckStatus() == Dealer.DeckStatus.DOWNWARDS) {
                cardStatus = CardModel.CardStatus.DOWNWARDS;
            }
            cardModel.setStatus(cardStatus);
            cardModelList.add(cardModel);
        }
    }

    @Override
    public Fragment getItem(int position) {
        CardFragment fragment = fragmentList.get(position);
        CardModel cardModel = cardModelList.get(position);
        cardModel.setUpwardResourceId(getUpwardResourceId(position));
        fragment.setCard(cardModel);
        return fragment;
    }

    private int getUpwardResourceId(int position) {
        Card card = dealer.getCardAtPosition(position);
        int upwardResourceId = R.drawable.cover_big;
        switch (card) {
            case ONE:
                upwardResourceId = R.drawable.card01_big;
                break;
            case TWO:
                upwardResourceId = R.drawable.card02_big;
                break;
            case THREE:
                upwardResourceId = R.drawable.card03_big;
                break;
            case FIVE:
                upwardResourceId = R.drawable.card04_big;
                break;
            case EIGHT:
                upwardResourceId = R.drawable.card05_big;
                break;
            case THIRTEEN:
                upwardResourceId = R.drawable.card06_big;
                break;
            case TWENTY:
                upwardResourceId = R.drawable.card07_big;
                break;
            case FORTY:
                upwardResourceId = R.drawable.card08_big;
                break;
            case HUNDRED:
                upwardResourceId = R.drawable.card09_big;
                break;
            case INFINITE:
                upwardResourceId = R.drawable.card10_big;
                break;
            case UNKNOWN:
                upwardResourceId = R.drawable.card11_big;
                break;
            case YAK_SHAVING:
                upwardResourceId = R.drawable.card12_big;
                break;
            case BROWN:
                upwardResourceId = R.drawable.card13_big;
                break;
            case PAUSE:
                upwardResourceId = R.drawable.card14_big;
                break;
        }
        return upwardResourceId;
    }

    @Override
    public int getCount() {
        return dealer.getDeckLength();
    }
}
