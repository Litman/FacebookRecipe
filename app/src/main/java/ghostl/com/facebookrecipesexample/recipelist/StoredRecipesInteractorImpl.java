package ghostl.com.facebookrecipesexample.recipelist;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public class StoredRecipesInteractorImpl implements StoredRecipesInteractor{

    private RecipeListRepository recipeListRepository;

    public StoredRecipesInteractorImpl(RecipeListRepository recipeListRepository) {
        this.recipeListRepository = recipeListRepository;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        recipeListRepository.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        recipeListRepository.removeRecipe(recipe);
    }
}
