package ghostl.com.facebookrecipesexample.recipemain;

import java.util.Random;

public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor{
    RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }




    @Override
    public void execute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();

    }
}
