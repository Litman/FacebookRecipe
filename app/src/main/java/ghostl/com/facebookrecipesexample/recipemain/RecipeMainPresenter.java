package ghostl.com.facebookrecipesexample.recipemain;

import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.recipemain.events.RecipeMainEvent;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainView;

public interface RecipeMainPresenter {

    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    void imageReady();
    void imageError(String error);
    RecipeMainView getView();

}
