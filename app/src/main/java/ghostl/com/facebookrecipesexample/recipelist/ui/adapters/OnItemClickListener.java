package ghostl.com.facebookrecipesexample.recipelist.ui.adapters;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
