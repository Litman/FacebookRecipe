package ghostl.com.facebookrecipesexample.recipelist.ui;

import java.util.List;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public interface RecipeListView {

    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDeleted(Recipe recipe);

}
