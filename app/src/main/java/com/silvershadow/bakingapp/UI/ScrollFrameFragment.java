package com.silvershadow.bakingapp.UI;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvershadow.bakingapp.Adapters.StepsViewPagerAdapter;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class ScrollFrameFragment extends Fragment {
    RecipesViewModel mModel;
    int mRecipeId;
    ViewPager mPager;
    public ScrollFrameFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        mRecipeId = getArguments().getInt(Support.STEP_ID_STRING);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps_view_pager, container, false);
        mPager = view.findViewById(R.id.steps_view_pager);
        PagerAdapter pagerAdapter = new StepsViewPagerAdapter(getFragmentManager(), mModel, mRecipeId);
        mPager.setAdapter(pagerAdapter);
        return view;
    }
}
