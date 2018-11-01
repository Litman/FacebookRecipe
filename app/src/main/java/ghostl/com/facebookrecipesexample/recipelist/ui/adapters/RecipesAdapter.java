package ghostl.com.facebookrecipesexample.recipelist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import ghostl.com.facebookrecipesexample.R;
import ghostl.com.facebookrecipesexample.entities.Recipe;
import ghostl.com.facebookrecipesexample.libs.base.ImageLoader;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public RecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.recipeList = recipeList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_recipes, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currenteRecipe = recipeList.get(position);
        //imageLoader.load(holder.);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
    private void setRecipes(List<Recipe> recipes){
        this.recipeList = recipes;
        notifyDataSetChanged();
    }

    private void removeRecipe(Recipe recipe){
        recipeList.remove(recipe);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
