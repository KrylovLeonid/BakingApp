package com.silvershadow.bakingapp.Adapters;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;
import com.squareup.picasso.Picasso;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    Listener mListener;
    private RecipesViewModel mModel;

    public RecipesAdapter(RecipesViewModel model){
        mModel = model;
    }

    public interface Listener{
        void onClick();
    }


    class RecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView recipeNameTV;
        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTV = itemView.findViewById(R.id.tv_recipe_name);

        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public RecipesAdapter.RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new RecipesViewHolder(inflater.inflate(R.layout.rv_recipe_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.RecipesViewHolder recipesViewHolder, int i) {
        recipesViewHolder.recipeNameTV.setText(mModel.getRecipes().getValue().get(i).getName());
        //Picasso.get().load(mModel.getRecipes().getValue().get(i).getImage()).into(recipesViewHolder.recipeThumbnailIV);
    }

    @Override
    public int getItemCount() {
        if(mModel.getRecipes().getValue() == null)
            return 0;
        return mModel.getRecipes().getValue().size();
    }
}
