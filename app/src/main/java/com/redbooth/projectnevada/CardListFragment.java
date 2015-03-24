package com.redbooth.projectnevada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.redbooth.projectnevada.core.Dealer;
import com.redbooth.projectnevada.core.DealerFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardListFragment extends Fragment {
    @InjectView(R.id.pager) ViewPager viewPager;
    @InjectView(R.id.floating) FloatingActionButton floatingButton;

    public static CardListFragment newInstance() {
        return new CardListFragment();
    }

    private View.OnClickListener onFloatingClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).showGridFragment();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Dealer dealer = DealerFactory.newInstance();
        CardsPagerAdapter cardsPagerAdapter = new CardsPagerAdapter(getFragmentManager(), dealer);
        this.viewPager.setAdapter(cardsPagerAdapter);
        floatingButton.setOnClickListener(onFloatingClick);
    }
}