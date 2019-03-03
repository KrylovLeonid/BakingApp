package com.silvershadow.bakingapp.UI;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.silvershadow.bakingapp.Adapters.StepsAdapter;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class RecipeFragment extends Fragment {
    int currentRecipeId;
    RecyclerView mRecyclerView;
    Button ingridientsButton;
    RecipesViewModel model;

    public RecipeFragment(){ }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentRecipeId = getArguments().getInt(Support.RECIPE_ID_STRING);
        model = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.recipe_steps_fragment,container,false);
        ingridientsButton = v.findViewById(R.id.ingredients_button);
        ingridientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Support.RECIPE_ID_STRING, currentRecipeId);
                IngredientsListFragment fragment = new IngredientsListFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
            }
        });


        mRecyclerView = v.findViewById(R.id.steps_rv);
        StepsAdapter adapter = new StepsAdapter(model, currentRecipeId);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);


        return v;


    }




}
