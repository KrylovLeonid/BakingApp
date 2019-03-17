package com.silvershadow.bakingapp.UI;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class StepFragment extends Fragment{


    RecipesViewModel mModel;
    PlayerView stepVideo;
    TextView description;
    int mRecipeId;
    int mStepId;




    public StepFragment(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        mRecipeId =  getArguments().getInt(Support.RECIPE_ID_STRING);
        mStepId = getArguments().getInt(Support.STEP_ID_STRING);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.step_fragment, container, false);
        stepVideo = view.findViewById(R.id.step_video);
        description = view.findViewById(R.id.step_long_description_tv);
        setData(mRecipeId,mStepId);


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setData(int recipeId, int stepId){
        description.setText(mModel.getRecipes().getValue().get(recipeId).getSteps().get(stepId).getDescription());
        setPlayer(recipeId, stepId);

    }


    private void setPlayer(int recipeId, int stepId){

        Uri stepVideoUri = Uri.parse(mModel.getRecipes().getValue().get(recipeId).getSteps().get(stepId).getVideoURL());
        TrackSelector trackSelector = new DefaultTrackSelector();
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
        stepVideo = setHeight(stepVideo);
        stepVideo.setPlayer(player);
        String userAgent = Util.getUserAgent(getContext(),"BackingApp");
        com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),userAgent);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(stepVideoUri);
        player.prepare(mediaSource);
        player.setPlayWhenReady(false);
    }

    public PlayerView setHeight(PlayerView view){

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                Support.getDisplayType(getActivity()) != Support.DisplayType.TABLET)
            lp.height = (int)(Support.getDisplayHeight(getActivity()) * 0.80);
        else
            lp.height = Support.convertDpToPixels(getActivity(), 300);
        view.setLayoutParams(lp);
        return view;

    }
}
