package ghostl.com.facebookrecipesexample.recipemain.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ghostl.com.facebookrecipesexample.FacebookRecipesApp;
import ghostl.com.facebookrecipesexample.R;
import ghostl.com.facebookrecipesexample.RecipeListActivity;
import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;
import ghostl.com.facebookrecipesexample.recipemain.RecipeMainPresenter;
import ghostl.com.facebookrecipesexample.recipemain.di.RecipeMainComponent;
import ghostl.com.facebookrecipesexample.recipemain.events.RecipeMainEvent;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView{

    @Bind(R.id.ivPhotos)
    ImageView ivPhotos;
    @Bind(R.id.ibDelete)
    ImageButton ibDelete;
    @Bind(R.id.ibFavorite)
    ImageButton ibFavorite;
    @Bind(R.id.pbRecipe)
    ProgressBar pbRecipe;
    @Bind(R.id.rlContentRecipe)
    RelativeLayout rlContentRecipe;

    private RecipeMainPresenter recipeMainPresenter;
    private Recipe currentRecipe;
    private ImageLoader imageLoader;
    private RecipeMainComponent recipeMainComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);
        setupInjection();
        setupGestureDetection();
        setupImageLoader();
        recipeMainPresenter.onCreate();
        recipeMainPresenter.getNextRecipe();
    }

    private void setupGestureDetection() {
        
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                recipeMainPresenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                recipeMainPresenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    @Override
    protected void onDestroy() {
        recipeMainPresenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        recipeMainComponent = app.getRecipeMainComponent(this, this);
        imageLoader = getImageLoader();
        recipeMainPresenter = getPresenter();
    }

    private RecipeMainPresenter getPresenter() {
        return recipeMainComponent.getPresenter();
    }

    private ImageLoader getImageLoader() {
        return recipeMainComponent.getImageLoader();
    }

    @Override
    public void showProgressBar() {
        pbRecipe.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbRecipe.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        ibFavorite.setVisibility(View.VISIBLE);
        ibDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        ibFavorite.setVisibility(View.GONE);
        ibDelete.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {

    }

    @Override
    public void dismissAnimation() {

    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(rlContentRecipe, R.string.recipemain_notice_saved, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        imageLoader.load(ivPhotos, recipe.getImageURL());
    }

    @Override
    public void onGetRecipeError(String error) {
        String msgError = String.format(getString(R.string.recipemain_error));
        Snackbar.make(rlContentRecipe, msgError, Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.ibFavorite)
    public void onKeep(){
        if(currentRecipe != null){
            recipeMainPresenter.saveRecipe(currentRecipe);
        }
    }
    @OnClick(R.id.ibDelete)
    public void onDelete(){
        if(currentRecipe != null){
            recipeMainPresenter.dismissRecipe();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_list){
            navigateToListScreen();
        }else if(id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, RecipeListActivity.class));
    }
}
