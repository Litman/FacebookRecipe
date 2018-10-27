package ghostl.com.facebookrecipesexample.recipemain.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ghostl.com.facebookrecipesexample.api.RecipeClient;
import ghostl.com.facebookrecipesexample.api.RecipeService;
import ghostl.com.facebookrecipesexample.libs.base.EventBus;
import ghostl.com.facebookrecipesexample.recipemain.GetNextRecipeInteractor;
import ghostl.com.facebookrecipesexample.recipemain.GetNextRecipeInteractorImpl;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainPresenter;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainPresenterImpl;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainRepository;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainRepositoryImpl;
import ghostl.com.facebookrecipesexample.recipemain.SaveRecipeInteractor;
import ghostl.com.facebookrecipesexample.recipemain.SaveRecipeInteractorImpl;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainView;

@Module
public class RecipeMainModule {

    RecipeMainView recipeMainView;

    public RecipeMainModule(RecipeMainView recipeMainView) {
        this.recipeMainView = recipeMainView;
    }

    @Provides @Singleton
    RecipeMainView providesRecipeMainView(){
        return this.recipeMainView;
    }

    @Provides @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus, RecipeMainView recipeMainView, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeInteractor getNextRecipeInteractor){
        return new RecipeMainPresenterImpl(eventBus, recipeMainView, saveRecipeInteractor, getNextRecipeInteractor);
    }

    @Provides @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository){
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus eventBus, RecipeService service){
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }

}
