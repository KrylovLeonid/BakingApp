package com.silvershadow.bakingapp.SupportClasses;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.silvershadow.bakingapp.R;

public class Support {

    public static final String RECIPE_ID_STRING = "recipe_id";
    public static final String STEP_ID_STRING = "step_id";
    public static final String MAIN_FRAGMENT_SAVE_TAG= "main fragment";
    private static final float MAX_PHONE_WIDTH_IN_INCHES = 4;
    public static final String RECIPE_SERVICE_INTENT = "recipe service intent";
    public static final String WIDGET_INGREDIENTS_BUNDLE = "widget ingredients bundle";
    //public static final String INGREDIENTS_BUTTON_STATE = "ingredients button";


    public enum DisplayType{
        PHONE{
            public RecyclerView.LayoutManager defineLayout(Context context){
                return new LinearLayoutManager(context);
            }

            @Override
            public int getFragmentId() {
                return R.id.main_fragment_container;
            }

        },
        TABLET {
            public RecyclerView.LayoutManager defineLayout(Context context) {
                return new GridLayoutManager(context, 3);
            }

            @Override
            public int getFragmentId() {
                return R.id.main_fragment_details_container;
            }

        };
        public abstract RecyclerView.LayoutManager defineLayout(Context context);
        public abstract int getFragmentId();
    }

    public static DisplayType getDisplayType(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if(metrics.widthPixels / metrics.xdpi < Support.MAX_PHONE_WIDTH_IN_INCHES )
            return DisplayType.PHONE;
        else
            return DisplayType.TABLET;
    }

    public static int getDisplayHeight(Activity activity){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.heightPixels;
    }

    public static int convertDpToPixels(Activity activity, int dpValue){
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) (metrics.density * dpValue);
    }



}
