package com.silvershadow.bakingapp.UI;

import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silvershadow.bakingapp.Adapters.RecipesAdapter;
import com.silvershadow.bakingapp.Entities.Recipe;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

import java.util.List;

public class MainScreenFragment extends Fragment {

    public RecipesViewModel mModel;
    RecipesAdapter mAdapter;
    RecyclerView allRecipesRV;

    public MainScreenFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.main_fragment, container, false);
        allRecipesRV = v.findViewById(R.id.main_fragment_rv);
        mAdapter = new RecipesAdapter(mModel, new RecipesAdapter.ItemClickListener() {
            @Override
            public void onClick(int id) {
                RecipeFragment fragment = new RecipeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Support.RECIPE_ID_STRING,id);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container,fragment).addToBackStack(null).commit();
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        allRecipesRV.setLayoutManager(manager);
        allRecipesRV.setHasFixedSize(true);
        allRecipesRV.setAdapter(mAdapter);

        mModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }
}
