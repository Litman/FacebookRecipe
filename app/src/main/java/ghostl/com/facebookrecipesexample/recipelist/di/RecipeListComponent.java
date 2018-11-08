package ghostl.com.facebookrecipesexample.recipelist.di;

import javax.inject.Singleton;

import dagger.Component;
import ghostl.com.facebookrecipesexample.libs.di.LibsModule;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListPresenter;
import ghostl.com.facebookrecipesexample.recipelist.ui.adapters.RecipesAdapter;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainPresenter;

@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    RecipesAdapter getAdapter();
    RecipeListPresenter getPresenter();
}
