package com.silvershadow.bakingapp.UI;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.silvershadow.bakingapp.Adapters.RecipesAdapter;
import com.silvershadow.bakingapp.Entities.Ingredient;
import com.silvershadow.bakingapp.Entities.Recipe;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.RecipesWidget;
import com.silvershadow.bakingapp.Services.RecipeWidgetService;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainScreenFragment extends Fragment {


    interface OnRecipeClick{
        void onRecipeClick(int recipeId);
    }

    public RecipesViewModel mModel;
    RecipesAdapter mAdapter;
    RecyclerView allRecipesRV;
    int selectedRecipe = -1 ;
    Support.DisplayType currentDisplayType;
    OnRecipeClick mCallback;

    public MainScreenFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (OnRecipeClick) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipeClick ");
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        currentDisplayType = Support.getDisplayType(getActivity());


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
                selectedRecipe = id;
                updateWidgetData();
                if(currentDisplayType == Support.DisplayType.TABLET)
                    mCallback.onRecipeClick(id);
                else
                    replaceRecipeFragment(id);
            }
        });
        allRecipesRV.setLayoutManager(currentDisplayType.defineLayout(getContext()));
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

    private void replaceRecipeFragment(int id){
        RecipeFragment fragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Support.RECIPE_ID_STRING,id);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.main_fragment_container,fragment).addToBackStack(null).commit();
    }

    private void updateWidgetData(){
        ComponentName widget = new ComponentName(getContext(), RecipesWidget.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        int[] ids = appWidgetManager.getAppWidgetIds(widget);
        appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.widget_recipes_lv);




    }

    private ArrayList<String> createIngredientsList(int recipeId){
        ArrayList <String> result = new ArrayList<>();
        for(Ingredient ing : mModel.getRecipes().getValue().get(recipeId).getIngredients()){
            result.add(String.valueOf(ing.getQuantity()) + " " + ing.getMeasure() + " of " + ing.getIngredient());
        }
    return result;
    }

}
