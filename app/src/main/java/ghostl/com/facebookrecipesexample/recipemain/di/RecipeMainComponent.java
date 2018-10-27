package ghostl.com.facebookrecipesexample.recipemain.di;

import javax.inject.Singleton;

import dagger.Component;
import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;
import ghostl.com.facebookrecipesexample.libs.di.LibsModule;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainPresenter;
import ghostl.com.facebookrecipesexample.recipemain.events.RecipeMainEvent;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainActivity;

@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {

    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();

}
