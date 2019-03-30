package com.silvershadow.bakingapp.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silvershadow.bakingapp.R;
import com.silvershadow.bakingapp.SupportClasses.Support;
import com.silvershadow.bakingapp.ViewModel.RecipesViewModel;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

  private static int selectedItem = -1;

  private RecipesViewModel mModel;
  private int mRecipeId;
  private Support.DisplayType mDisplayType;
  private OnItemClickListener mListener;

  public interface OnItemClickListener{
    void onItemClick(int stepId);
  }

  public StepsAdapter(RecipesViewModel model, int recipeId, Support.DisplayType dispayType, OnItemClickListener listener){
    mModel = model;
    mRecipeId = recipeId;
    mListener = listener;
    mDisplayType = dispayType;
  }

  public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView shortDescription;

    public StepsViewHolder(@NonNull View itemView) {
      super(itemView);
      shortDescription = itemView.findViewById(R.id.step_sort_description_tv);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      mListener.onItemClick(getAdapterPosition());
      selectedItem = getAdapterPosition();
      setPressedStyle(view.findViewById(R.id.step_sort_description_tv));
      notifyDataSetChanged();
    }
  }

  @NonNull
  @Override
  public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.steps_rv_item, viewGroup, false);

    return new StepsViewHolder(view);
  }



  @Override
  public void onBindViewHolder(@NonNull StepsViewHolder stepsViewHolder, int i) {
    stepsViewHolder.shortDescription.setText(mModel.getRecipes().getValue().get(mRecipeId).getSteps().get(i).getShortDescription());
    View viewToDecorate = stepsViewHolder.itemView.findViewById(R.id.step_sort_description_tv);

    if(selectedItem == stepsViewHolder.getAdapterPosition() && mDisplayType == Support.DisplayType.TABLET){
      setPressedStyle(viewToDecorate);
    }else
      setDefaultStyle(viewToDecorate);


  }

  @Override
  public int getItemCount() {
    return mModel.getRecipes().getValue().get(mRecipeId).getSteps().size();
  }

  private void setPressedStyle(View view){
    view.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.colorPrimaryDark));
  }

  private void setDefaultStyle(View view){
    view.setBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.colorPrimaryLight));
  }
  public void resetSelection(){
    selectedItem = -1;
    notifyDataSetChanged();
  }

}