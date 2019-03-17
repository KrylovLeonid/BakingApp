package com.silvershadow.bakingapp.UI;
import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;


public class MainActivity extends AppCompatActivity implements  MainScreenFragment.OnRecipeClick,

                                                                RecipeFragment.TwoColumnsIngredientsClickListener,
                                                                RecipeFragment.TwoColumnsStepClickListener{

    private Support.DisplayType mDisplayType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isOnline()) {
            setContentView(R.layout.error_layout);
            return;
        }

        setContentView(R.layout.activity_main);
        MainScreenFragment mainScreenFragment;
        if (savedInstanceState == null) {

            mainScreenFragment = new MainScreenFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.main_fragment_container, mainScreenFragment, Support.MAIN_FRAGMENT_SAVE_TAG).addToBackStack(null).commit();
        } else {
            mainScreenFragment = (MainScreenFragment) getSupportFragmentManager().findFragmentByTag(Support.MAIN_FRAGMENT_SAVE_TAG);
        }

        mDisplayType = Support.getDisplayType(this);
        if(mDisplayType == Support.DisplayType.TABLET){
            setOneColumnLayout();
        }
    }

    public void setOneColumnLayout(){
        LinearLayout ll = findViewById(R.id.main_fragment_details_container);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll.getLayoutParams();
        params.weight = 0;
        ll.setLayoutParams(params);
    }

    public void setTwoColumnsLayout(){
        LinearLayout ll = findViewById(R.id.main_fragment_details_container);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll.getLayoutParams();
        params.weight = 1;
        ll.setLayoutParams(params);
    }

    @Override
    public void onRecipeClick(int recipeId) {
        setTwoColumnsLayout();

        if(RecipeFragment.stepsAdapter != null)
            RecipeFragment.stepsAdapter.resetSelection();

        RecipeFragment recipeFragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Support.RECIPE_ID_STRING,recipeId);
        recipeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,recipeFragment).addToBackStack(null).commit();

        setTwoColumnsLayout();
        StepFragment stepFragment = new StepFragment();
        stepFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_details_container,stepFragment).commit();


    }

    @Override
    public void onIngredientClick(int recipeId) {

        Bundle bundle = new Bundle();
        bundle.putInt(Support.RECIPE_ID_STRING, recipeId);
        IngredientsListFragment fragment = new IngredientsListFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(mDisplayType.getFragmentId(), fragment).commit();

    }

    @Override
    public void onStepClick(int recipeId, int stepID) {
        StepFragment fragment = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Support.RECIPE_ID_STRING, recipeId);
        bundle.putInt(Support.STEP_ID_STRING, stepID);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(mDisplayType.getFragmentId(),fragment).commit();

    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Support.getDisplayType(this) == Support.DisplayType.TABLET)
            setOneColumnLayout();
    }

}
