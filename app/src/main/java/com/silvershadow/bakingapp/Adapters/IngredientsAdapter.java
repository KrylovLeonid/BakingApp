package com.silvershadow.bakingapp.Adapters;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

import java.util.zip.Inflater;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    RecipesViewModel mModel;
    int mRecipeId;

    public IngredientsAdapter(RecipesViewModel model, int recipeId){
        mModel = model;
        mRecipeId = recipeId;
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder{
        TextView quantity;
        TextView measure;
        TextView ingredient;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.quantity_tv);
            measure = itemView.findViewById(R.id.measure_tv);
            ingredient = itemView.findViewById(R.id.ingredient_tv);
        }
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new IngredientsViewHolder(inflater.inflate(R.layout.ingridient_rv_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder ingredientsViewHolder, int i) {
        ingredientsViewHolder.quantity.setText(String.valueOf(mModel.getRecipes().getValue().get(mRecipeId).getIngredients().get(i).getQuantity()));
        ingredientsViewHolder.measure.setText(mModel.getRecipes().getValue().get(mRecipeId).getIngredients().get(i).getMeasure());
        ingredientsViewHolder.ingredient.setText(mModel.getRecipes().getValue().get(mRecipeId).getIngredients().get(i).getIngredient());
    }


    @Override
    public int getItemCount() {
        return mModel.getRecipes().getValue().get(mRecipeId).getIngredients().size();
    }
}
