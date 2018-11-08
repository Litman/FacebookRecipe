package ghostl.com.facebookrecipesexample.recipelist.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.EventBus;
import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListInteractor;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListInteractorImpl;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListPresenter;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListPresenterImpl;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListRepository;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListRepositoryImpl;
import ghostl.com.facebookrecipesexample.recipelist.StoredRecipesInteractor;
import ghostl.com.facebookrecipesexample.recipelist.StoredRecipesInteractorImpl;
import ghostl.com.facebookrecipesexample.recipelist.ui.RecipeListView;
import ghostl.com.facebookrecipesexample.recipelist.ui.adapters.OnItemClickListener;
import ghostl.com.facebookrecipesexample.recipelist.ui.adapters.RecipesAdapter;
import ghostl.com.facebookrecipesexample.recipemain.GetNextRecipeInteractor;
import ghostl.com.facebookrecipesexample.recipemain.GetNextRecipeInteractorImpl;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainPresenterImpl;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainRepository;
import ghostl.com.facebookrecipesexample.recipemain.SaveRecipeInteractor;
import ghostl.com.facebookrecipesexample.recipemain.SaveRecipeInteractorImpl;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainView;

@Module
public class RecipeListModule {
    RecipeListView recipeListView;
    OnItemClickListener clickListener;

    /*public RecipeListModule(RecipeListView recipeListView) {
        this.recipeListView = recipeListView;
    }*/

    public RecipeListModule(RecipeListView recipeListView, OnItemClickListener clickListener) {
        this.recipeListView = recipeListView;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    RecipeListView providesRecipeListView(){
        return this.recipeListView;
    }

    @Provides
    @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView recipeListView, RecipeListInteractor recipeListInteractor, StoredRecipesInteractor storedRecipesInteractor){
        return new RecipeListPresenterImpl(eventBus, recipeListView, recipeListInteractor, storedRecipesInteractor);
    }

    @Provides
    @Singleton
    StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repository){
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository){
        return new RecipeListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListRepository providesRecipeListRepository(EventBus eventBus){
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    RecipesAdapter providesRecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new RecipesAdapter(recipeList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Recipe> providesEmptyList(){
        return new ArrayList<Recipe>();
    }


}
