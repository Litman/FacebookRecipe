package ghostl.com.facebookrecipesexample.recipemain;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public interface RecipeMainRepository {

    public final static int COUNT = 1;
    public final static String RECENT_SORT = "r";
    public final static int RECIPE_RANGE = 100000;


    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePagie(int recipePage);
}
