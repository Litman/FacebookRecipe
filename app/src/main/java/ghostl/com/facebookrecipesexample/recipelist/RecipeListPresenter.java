package ghostl.com.facebookrecipesexample.recipelist;

import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.recipelist.events.RecipeListEvent;
import ghostl.com.facebookrecipesexample.recipelist.ui.RecipeListView;

public interface RecipeListPresenter {

    void onCreate();
    void onDestroy();

    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();

}
