package ghostl.com.facebookrecipesexample.recipemain.ui;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public interface RecipeMainView {

    void showProgressBar();
    void hideProgressBar();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);

    void onGetRecipeError(String error);
}
