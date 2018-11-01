package ghostl.com.facebookrecipesexample.recipelist;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public interface RecipeListRepository {
    void getSaveRecipes();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
}
