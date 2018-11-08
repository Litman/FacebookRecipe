package ghostl.com.facebookrecipesexample;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

import dagger.Module;
import ghostl.com.facebookrecipesexample.libs.di.LibsModule;
import ghostl.com.facebookrecipesexample.login.MainActivity;
import ghostl.com.facebookrecipesexample.recipelist.di.DaggerRecipeListComponent;
import ghostl.com.facebookrecipesexample.recipelist.di.RecipeListComponent;
import ghostl.com.facebookrecipesexample.recipelist.di.RecipeListModule;
import ghostl.com.facebookrecipesexample.recipelist.ui.RecipeListView;
import ghostl.com.facebookrecipesexample.recipelist.ui.adapters.OnItemClickListener;
import ghostl.com.facebookrecipesexample.recipemain.di.DaggerRecipeMainComponent;
import ghostl.com.facebookrecipesexample.recipemain.di.RecipeMainComponent;
import ghostl.com.facebookrecipesexample.recipemain.di.RecipeMainModule;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainActivity;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainView;

@Module
public class FacebookRecipesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initFacebook(){
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView recipeMainView){
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(recipeMainView))
                .build();
    }

    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView recipeMainView, OnItemClickListener listener){
        return DaggerRecipeListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(recipeMainView, listener))
                .build();

    }

}
