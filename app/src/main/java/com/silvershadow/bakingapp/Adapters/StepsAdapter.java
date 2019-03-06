package com.silvershadow.bakingapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    RecipesViewModel mModel;
    int recipeIndex;
    public StepsAdapter(RecipesViewModel model, int index){
        mModel = model;
        recipeIndex = index;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView shortDescription;
        TextView longDescription;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.step_sort_description_tv);


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
        stepsViewHolder.shortDescription.setText(mModel.getRecipes().getValue().get(recipeIndex).getSteps().get(i).getShortDescription());
        //stepsViewHolder.longDescription.setText(mModel.getRecipes().getValue().get(recipeIndex).getSteps().get(i).getDescription());





    }

    @Override
    public int getItemCount() {
        return mModel.getRecipes().getValue().get(recipeIndex).getSteps().size();
    }


}
