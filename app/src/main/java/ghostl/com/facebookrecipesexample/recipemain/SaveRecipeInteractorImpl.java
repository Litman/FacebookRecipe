package ghostl.com.facebookrecipesexample.recipemain;

import ghostl.com.facebookrecipesexample.entities.Recipe;

public class SaveRecipeInteractorImpl implements SaveRecipeInteractor {
    RecipeMainRepository repository;

    public SaveRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Recipe recipe) {
        repository.saveRecipe(recipe);
    }
}
