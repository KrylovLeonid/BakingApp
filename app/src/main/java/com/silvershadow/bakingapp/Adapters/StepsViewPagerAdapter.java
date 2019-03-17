package com.silvershadow.bakingapp.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.UI.StepFragment;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class StepsViewPagerAdapter extends FragmentPagerAdapter {
    RecipesViewModel mModel;
    int mRecipeId;

    public StepsViewPagerAdapter(FragmentManager fm, RecipesViewModel model, int recipeId) {
        super(fm);
        mModel = model;
        mRecipeId = recipeId;
    }

    @Override
    public Fragment getItem(int i) {
        StepFragment fragment = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Support.RECIPE_ID_STRING, mRecipeId);
        bundle.putInt(Support.STEP_ID_STRING, i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mModel.getRecipes().getValue().get(mRecipeId).getSteps().size();
    }
}
