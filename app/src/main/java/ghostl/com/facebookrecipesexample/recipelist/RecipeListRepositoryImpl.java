package ghostl.com.facebookrecipesexample.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.Arrays;
import java.util.List;

import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.EventBus;
import ghostl.com.facebookrecipesexample.recipelist.events.RecipeListEvent;

public class RecipeListRepositoryImpl implements RecipeListRepository{

    private EventBus eventBus;

    @Override
    public void getSaveRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(false, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipeList){
        RecipeListEvent event = new RecipeListEvent();
        event.setType(type);
        event.setRecipeList(recipeList);
        eventBus.post(event);
    }

    private void post(){
        post(RecipeListEvent.UPDATE_EVENT, null);
    }
}
