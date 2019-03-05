package com.silvershadow.bakingapp.Adapters;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.silvershadow.bakingapp.Entities.Recipe;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

import java.util.zip.Inflater;

import javax.sql.DataSource;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    RecipesViewModel mModel;
    int recipeIndex;
    public StepsAdapter(RecipesViewModel model, int index){
        mModel = model;
        recipeIndex = index;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        TextView shortDescreption;
        TextView longDescreption;
        PlayerView recipeVideo;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDescreption = itemView.findViewById(R.id.step_sort_description_tv);
            longDescreption = itemView.findViewById(R.id.step_long_description_tv);
            //PlayerView recipeVideo = itemView.findViewById(R.id.step_video);



        }
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new StepsViewHolder(inflater.inflate(R.layout.steps_rv_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder stepsViewHolder, int i) {
        stepsViewHolder.shortDescreption.setText(mModel.getRecipes().getValue().get(recipeIndex).getSteps().get(i).getShortDescription());
        stepsViewHolder.longDescreption.setText(mModel.getRecipes().getValue().get(recipeIndex).getSteps().get(i).getDescription());

 //       TrackSelector trackSelector = new DefaultTrackSelector();
   //     LoadControl loadControl = new DefaultLoadControl();

//        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(stepsViewHolder.itemView.getContext(), trackSelector, loadControl);
//        stepsViewHolder.recipeVideo.setPlayer(player);
//        String userAgent = Util.getUserAgent(stepsViewHolder.itemView.getContext(),"BackingApp");
        //MediaSource currenrStepVideo = new ExtractorMediaSource()


    }

    @Override
    public int getItemCount() {
        return mModel.getRecipes().getValue().get(recipeIndex).getSteps().size();
    }


}
