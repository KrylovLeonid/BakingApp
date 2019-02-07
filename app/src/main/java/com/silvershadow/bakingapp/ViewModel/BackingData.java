package com.silvershadow.bakingapp.ViewModel;

import com.silvershadow.bakingapp.Entities.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BackingData {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> loadRecipes();
}
