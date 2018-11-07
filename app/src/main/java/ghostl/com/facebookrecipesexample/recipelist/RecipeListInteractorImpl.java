package ghostl.com.facebookrecipesexample.recipelist;

public class RecipeListInteractorImpl implements RecipeListInteractor {

    private RecipeListRepository recipeListRepository;

    public RecipeListInteractorImpl(RecipeListRepository recipeListRepository) {
        this.recipeListRepository = recipeListRepository;
    }

    @Override
    public void execute() {
        recipeListRepository.getSaveRecipes();
    }
}
