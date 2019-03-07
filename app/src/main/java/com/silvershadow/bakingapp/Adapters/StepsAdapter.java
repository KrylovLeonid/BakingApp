package com.silvershadow.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {


    RecipesViewModel mModel;
    int mRecipeId;
    OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int id);
    }

    public StepsAdapter(RecipesViewModel model, int recipeId, OnItemClickListener listener){
        mModel = model;
        mRecipeId = recipeId;
        mListener = listener;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView shortDescription;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.step_sort_description_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.steps_rv_item, viewGroup, false);

        return new StepsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder stepsViewHolder, int i) {
        stepsViewHolder.shortDescription.setText(mModel.getRecipes().getValue().get(mRecipeId).getSteps().get(i).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mModel.getRecipes().getValue().get(mRecipeId).getSteps().size();
    }


}
