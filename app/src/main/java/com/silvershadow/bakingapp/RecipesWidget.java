package com.silvershadow.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.silvershadow.bakingapp.Services.RecipeWidgetService;
import com.silvershadow.bakingapp.SupportClasses.Support;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class RecipesWidget extends AppWidgetProvider {

    private ArrayList<String> mIngredientsList = new ArrayList<>();


    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, ArrayList<String> ingredientsListBundle) {
        // Construct the RemoteViews object
        Intent serviceIntent = new Intent(context, RecipeWidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.putStringArrayListExtra(Support.WIDGET_INGREDIENTS_BUNDLE, ingredientsListBundle);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipes_widget);
        views.setRemoteAdapter(R.id.widget_recipes_lv, serviceIntent);
        views.setEmptyView(R.id.widget_recipes_lv, R.id.ingredients_widget_empty_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        //appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_recipes_lv);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId, mIngredientsList);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created


    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled

    }
}

