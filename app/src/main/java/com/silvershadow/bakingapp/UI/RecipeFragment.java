package com.silvershadow.bakingapp.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    int currentStepId = -1;
    boolean ingredientsButtonIsPressed;

    public static StepsAdapter stepsAdapter;
    RecyclerView mRecyclerView;
    Button ingredientsButton;
    RecipesViewModel mModel;
    Support.DisplayType currentDisplayType;

    TwoColumnsStepClickListener mRecipeCallback;
    TwoColumnsIngredientsClickListener mIngredientsCallback;


    public RecipeFragment(){ }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mRecipeCallback = (TwoColumnsStepClickListener)context;
            mIngredientsCallback = (TwoColumnsIngredientsClickListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement TwoColumnsRecipeClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentRecipeId = getArguments().getInt(Support.RECIPE_ID_STRING);
        currentStepId = getArguments().getInt(Support.STEP_ID_STRING);
        mModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        currentDisplayType = Support.getDisplayType(getActivity());
        setRetainInstance(true);
        }

    interface TwoColumnsStepClickListener{
        void onStepClick(int recipeId, int stepId);
    }
    interface TwoColumnsIngredientsClickListener{
        void onIngredientClick(int recipeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recipe_steps_fragment,container,false);
        ingredientsButton = v.findViewById(R.id.ingredients_button);

        mRecyclerView = v.findViewById(R.id.steps_rv);
        stepsAdapter = new StepsAdapter(mModel, currentRecipeId,currentDisplayType, new StepsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int stepId) {
                setStepFragment(stepId);
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setAdapter(stepsAdapter);
        mRecyclerView.setLayoutManager(manager);


        ingredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientsButtonIsPressed = true;
                setIngredientsFragment();
            stepsAdapter.resetSelection();
            }
        });

        return v;


    }

    private void setIngredientsFragment(){
        if(currentDisplayType == Support.DisplayType.TABLET) {
            ingredientsButton.setBackgroundColor(getContext().getColor(R.color.colorPrimaryDark));
            mIngredientsCallback.onIngredientClick(currentRecipeId);
        }
        else {
            Bundle bundle = new Bundle();
            bundle.putInt(Support.RECIPE_ID_STRING, currentRecipeId);
            IngredientsListFragment fragment = new IngredientsListFragment();
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragment).addToBackStack(null).commit();

        }
    }
    private void setStepFragment(int stepId){
        if(currentDisplayType == Support.DisplayType.TABLET) {
            ingredientsButton.setBackgroundColor(getContext().getColor(R.color.colorPrimaryLight));
            mRecipeCallback.onStepClick(currentRecipeId, stepId);
        }
        else {
            ScrollFrameFragment fragment = new ScrollFrameFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Support.RECIPE_ID_STRING, currentRecipeId);
            bundle.putInt(Support.STEP_ID_STRING, stepId);
            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragment).addToBackStack(null).commit();

        }
    }

}
