package ghostl.com.facebookrecipesexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.GlideImageLoader;
import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;
import ghostl.com.facebookrecipesexample.recipelist.RecipeListPresenter;
import ghostl.com.facebookrecipesexample.recipelist.events.RecipeListEvent;
import ghostl.com.facebookrecipesexample.recipelist.ui.RecipeListView;
import ghostl.com.facebookrecipesexample.recipelist.ui.adapters.OnItemClickListener;
import ghostl.com.facebookrecipesexample.recipelist.ui.adapters.RecipesAdapter;
import ghostl.com.facebookrecipesexample.recipemain.ui.RecipeMainActivity;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener{

    @Bind(R.id.tbListRecipe)
    Toolbar tbListRecipe;
    @Bind(R.id.rvListRecipe)
    RecyclerView rvListRecipe;

    RecipesAdapter adapter;
    RecipeListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        setupToolbar();
        setupInjection();
        setupRecyclerView();
        listPresenter.onCreate();
        listPresenter.getRecipes();

    }

    @Override
    protected void onDestroy() {
        listPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_main){
            navigateToMainScreen();
        }else if(id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }

    private void setupInjection() {
        /*ImageLoader loader = new GlideImageLoader(Glide.with(this));
        Recipe recipe = new Recipe();
        recipe.setFavorite(false);
        recipe.setTitle("Prueba");
        recipe.setSourceURL("http://static.food2fork.com/icedcoffee5766.jpg");
        recipe.setImageURL("http://static.food2fork.com/icedcoffee5766.jpg");
        adapter = new RecipesAdapter(Arrays.asList(recipe), loader, this);
        listPresenter = new RecipeListPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void getRecipes() {

            }

            @Override
            public void removeRecipe(Recipe recipe) {

            }

            @Override
            public void toggleFavorite(Recipe recipe) {

            }

            @Override
            public void onEventMainThread(RecipeListEvent event) {

            }

            @Override
            public RecipeListView getView() {
                return null;
            }
        };*/


    }

    private void setupRecyclerView() {
        rvListRecipe.setLayoutManager(new GridLayoutManager(this, 2));
        rvListRecipe.setAdapter(adapter);
    }

    private void setupToolbar() {
        setSupportActionBar(tbListRecipe);
    }

    @OnClick(R.id.tbListRecipe)
    public void onToolbarClick(){
        rvListRecipe.smoothScrollToPosition(0);
    }


    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        listPresenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        listPresenter.removeRecipe(recipe);
    }
}
