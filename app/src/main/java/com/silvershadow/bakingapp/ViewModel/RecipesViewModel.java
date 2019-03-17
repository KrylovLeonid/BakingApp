package com.silvershadow.bakingapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.silvershadow.bakingapp.Entities.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesViewModel extends AndroidViewModel {

    MutableLiveData<List<Recipe>> recipes = new MutableLiveData<List<Recipe>>();

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        loadRecipes();
    }


    public void loadRecipes(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BackingContract.BASE_URL_STR).addConverterFactory(GsonConverterFactory.create()).build();
        BackingData backingData = retrofit.create(BackingData.class);
        Call<List<Recipe>> call = backingData.loadRecipes();
        call.enqueue(new Callback<List<Recipe>>(){
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipes.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

                recipes.setValue(null);
            }

        });
    }

    public MutableLiveData<List<Recipe>> getRecipes(){
        return recipes;
    }

}
