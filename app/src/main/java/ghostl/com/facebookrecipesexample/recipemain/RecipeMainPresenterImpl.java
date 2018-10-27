package ghostl.com.facebookrecipesexample.recipemain;

import org.greenrobot.eventbus.Subscribe;

import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.EventBus;
import ghostl.com.facebookrecipesexample.recipemain.events.RecipeMainEvent;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainView;

public class RecipeMainPresenterImpl implements RecipeMainPresenter {
    private EventBus eventBus;
    private RecipeMainView recipeMainView;
    SaveRecipeInteractor saveRecipeInteractor;
    GetNextRecipeInteractor getNextRecipeInteractor;

    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView recipeMainView, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor) {
        this.eventBus = eventBus;
        this.recipeMainView = recipeMainView;
        this.saveRecipeInteractor = saveRecipeInteractor;
        this.getNextRecipeInteractor = getNextRecipeInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
        recipeMainView = null;
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void dismissRecipe() {
        if(this.recipeMainView != null){
            recipeMainView.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if(this.recipeMainView != null){
            recipeMainView.hideUIElements();
            recipeMainView.showProgressBar();
        }
        getNextRecipeInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if(this.recipeMainView != null){
            recipeMainView.saveAnimation();
            recipeMainView.showProgressBar();
        }
        saveRecipeInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if(this.recipeMainView != null) {
            String error = event.getError();
            if(error != null){
                recipeMainView.hideProgressBar();
                recipeMainView.onGetRecipeError(error);
            }else{
                if(event.getType() == RecipeMainEvent.NEXT_EVENT){
                    recipeMainView.setRecipe(event.getRecipe());

                }else if(event.getType() == RecipeMainEvent.SAVE_EVENT){
                    recipeMainView.onRecipeSaved();
                    getNextRecipeInteractor.execute();
                }
            }
        }
    }

    @Override
    public void imageReady() {
        if(this.recipeMainView != null){

            recipeMainView.hideProgressBar();
            recipeMainView.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if(this.recipeMainView != null) {
            recipeMainView.onGetRecipeError(error);
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.recipeMainView;
    }
}
