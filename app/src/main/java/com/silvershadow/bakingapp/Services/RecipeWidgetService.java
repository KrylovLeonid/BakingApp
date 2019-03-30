package com.silvershadow.bakingapp.Services;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.UI.MainActivity;
import com.silvershadow.bakingapp.UI.MainScreenFragment;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeWidgetService extends RemoteViewsService {



    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeWidgetFactory(getApplicationContext(), intent);
    }

    class RecipeWidgetFactory implements RemoteViewsService.RemoteViewsFactory{

        private ArrayList<String> mIngredientsList;
        private Context context;
        private int mAppWidgetId;

        public RecipeWidgetFactory(Context context, Intent intent) {
            this.context = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            }


        @Override
        public void onCreate() {
            mIngredientsList.add("Nooooo");
            mIngredientsList.add("Pls nooo");
        }

        @Override
        public void onDataSetChanged() {
            mIngredientsList.add("Works?");
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mIngredientsList.size() ;
    }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_list_item);
            views.setTextViewText(R.id.recipe_widget_tv_item, mIngredientsList.get(i));
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
