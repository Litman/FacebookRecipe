package ghostl.com.facebookrecipesexample.recipelist;

import org.greenrobot.eventbus.Subscribe;

import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.EventBus;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListInteractor;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListPresenter;
import ghostl.com.facebookrecipesexample.recipelist.StoredRecipesInteractor;
import ghostl.com.facebookrecipesexample.recipelist.events.RecipeListEvent;
import ghostl.com.facebookrecipesexample.recipelist.ui.RecipeListView;

public class RecipeListPresenterImpl implements RecipeListPresenter {
    private EventBus eventBus;
    private RecipeListView recipeListView;
    private RecipeListInteractor recipeListInteractor;
    private StoredRecipesInteractor storedRecipesInteractor;

    public RecipeListPresenterImpl(EventBus eventBus, RecipeListView recipeListView, RecipeListInteractor recipeListInteractor, StoredRecipesInteractor storedRecipesInteractor) {
        this.eventBus = eventBus;
        this.recipeListView = recipeListView;
        this.recipeListInteractor = recipeListInteractor;
        this.storedRecipesInteractor = storedRecipesInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        recipeListView = null;
    }

    @Override
    public void getRecipes() {
        recipeListInteractor.execute();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        storedRecipesInteractor.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav = recipe.isFavorite();
        recipe.setFavorite(!fav);
        storedRecipesInteractor.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if(recipeListView != null){
            switch (event.getType()){
                case RecipeListEvent.READ_EVENT:
                    recipeListView.setRecipes(event.getRecipeList());
                    break;
                case RecipeListEvent.UPDATE_EVENT:
                    recipeListView.recipeUpdated();
                    break;
                case RecipeListEvent.DELETE_EVENT:
                    Recipe recipe = event.getRecipeList().get(0);
                    recipeListView.recipeDeleted(recipe);
                    break;

            }
        }
    }

    @Override
    public RecipeListView getView() {
        return this.recipeListView;
    }
}
