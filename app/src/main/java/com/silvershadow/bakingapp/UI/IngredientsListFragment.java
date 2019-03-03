package com.silvershadow.bakingapp.UI;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvershadow.bakingapp.Adapters.IngredientsAdapter;
import com.silvershadow.bakingapp.Entities.Ingredient;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class IngredientsListFragment extends Fragment {
    RecipesViewModel mModel;
    RecyclerView mRecyclerView;
    int id;
    public IngredientsListFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        id = getArguments().getInt(Support.RECIPE_ID_STRING);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredients_fragment, container, false);
        IngredientsAdapter adapter = new IngredientsAdapter(mModel, id);
        mRecyclerView = v.findViewById(R.id.ingredients_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        return v;

    }
}
